package com.example.clothbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothbank.Helper.UserSession;
import com.example.clothbank.api.UserApi;
import com.example.clothbank.bll.UserBll;
import com.example.clothbank.model.User;
import com.example.clothbank.response.ClothResponse;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private UserSession userSession;
    CircleImageView proimage;
    TextView proname;
    ImageView updateprofile, imagemap;
    TextView txtaddress, txtphone, noofcloth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userSession = new UserSession(this);
        proimage = findViewById(R.id.profile_image);
        noofcloth = findViewById(R.id.noofcloth);
        proname = findViewById(R.id.textprofile_name);
        updateprofile = findViewById(R.id.update_profile);
        txtaddress = findViewById(R.id.location);
        txtphone = findViewById(R.id.textphn);
        imagemap= findViewById(R.id.imgmap);

        imagemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });


        UserBll userBll = new UserBll();
        UserSession userSession = new UserSession(this);
        String id = userSession.getUser().get_id();
        User user = userBll.userdetail(id);
        ClothResponse clothResponse = userBll.getNoofCloth(id);
        if (clothResponse != null) {
            noofcloth.setText(clothResponse.getTotalDonations());

        }
        if (user != null) {
            proname.setText(user.getFullname());
            txtaddress.setText(user.getAddress());
            txtphone.setText(user.getPhonenumber());
        }
        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, UpdateProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
