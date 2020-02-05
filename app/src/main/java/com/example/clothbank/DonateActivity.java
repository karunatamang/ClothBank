package com.example.clothbank;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.clothbank.Helper.Notifications;
import com.example.clothbank.Helper.UserSession;
import com.example.clothbank.Sensors.ShakeDetector;
import com.example.clothbank.api.DonationApi;
import com.example.clothbank.bll.DonationBll;
import com.example.clothbank.model.Donation;
import com.example.clothbank.strictmode.StrictModeClass;
import com.example.clothbank.url.Url;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonateActivity extends AppCompatActivity {
    EditText edittime, editnumofcloth, etAddress, etName, etPhone;
    ImageView photo, donate;
    private DonationBll donationBll;
    private UserSession userSession;
    private String image;
    private Uri uri;
    private MultipartBody.Part mbImage;


    private ShakeDetector mShakeDetector;

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        userSession = new UserSession(this);
        donationBll = new DonationBll();

        edittime = findViewById(R.id.time);
        editnumofcloth = findViewById(R.id.numcloth);

        donate = findViewById(R.id.btndonate);

        photo = findViewById(R.id.uploaddonate);

        etAddress = findViewById(R.id.address);
        etPhone = findViewById(R.id.phnum);
        etName = findViewById(R.id.name);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                edittime.setText("");
                editnumofcloth.setText("");
            }
        });

        etAddress.setText(userSession.getUser().getAddress());
        etName.setText(userSession.getUser().getFullname());
        etPhone.setText(userSession.getUser().getPhonenumber());


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

            }
        });


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donate();
                Intent intent = new Intent(DonateActivity.this, MainActivity.class);
                startActivity(intent);
                Notifications.givenotification(DonateActivity.this, "Donate Succesfully");
                Vibrator V =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] mVibratePattern = new long[]{0, 400, 200, 400};
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    V.vibrate(mVibratePattern, -1);
                }else{
                    V.vibrate(mVibratePattern,-1);
                }
                finish();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getImgReady();
            } else {
                Toast.makeText(DonateActivity.this, "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri = data.getData();
            photo.setImageURI(uri);
            askPermission();
        }
    }

    private void askPermission() {
        if (ContextCompat.checkSelfPermission(DonateActivity.this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DonateActivity.this, new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE},
                    2);
        } else {
            getImgReady();
            uploadImage(mbImage);
        }
    }

    private void getImgReady() {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = DonateActivity.this.getContentResolver().query(uri, filePathColumn,
                null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imgPath = cursor.getString(columnIndex);
        File file = new File(imgPath);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("image/*"), file);
        mbImage = MultipartBody.Part.createFormData("image",
                file.getName(), requestBody);
    }

    private void uploadImage(MultipartBody.Part img) {

        DonationApi donateApi = Url.getInstance().create(DonationApi.class);
        Call<String> imgUpload = donateApi.uploadImage(img);

        imgUpload.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(DonateActivity.this, response.body() + "Uploaded",
                        Toast.LENGTH_SHORT).show();
                image = response.body();
                Toast.makeText(DonateActivity.this, "image name" + image, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error:", t.getMessage());
            }
        });
    }

    private void donate() {
        String donor = userSession.getUser().get_id();
        String pickuptime = edittime.getText().toString();
        String nooflcloth = editnumofcloth.getText().toString();

        Donation donation = new Donation(donor, pickuptime, nooflcloth, image);

        StrictModeClass.StrictMode();
        Donation donationRes = donationBll.donate(donation);
        if (donationRes == null) {
            Toast.makeText(this, "Error adding donation", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Donation added sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

}
