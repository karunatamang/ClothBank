package com.example.clothbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clothbank.Helper.UserSession;
import com.example.clothbank.bll.UserBll;
import com.example.clothbank.model.User;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtname, edtaddress, edtemail, edtcontact, edtpassword;
    UserSession userSession;
    Button btnupdate, btnback;
    private UserBll userBll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        edtname = findViewById(R.id.fullname);
        edtaddress = findViewById(R.id.address);
        edtemail = findViewById(R.id.email);
        edtcontact = findViewById(R.id.contact);
        edtpassword = findViewById(R.id.password);

        btnupdate = findViewById(R.id.update);
        btnback = findViewById(R.id.back);

        userSession = new UserSession(this);
        userBll = new UserBll();

        edtname.setText(userSession.getUser().getFullname());
        edtemail.setText(userSession.getUser().getEmail());
        edtpassword.setText(userSession.getUser().getPassword());
        edtaddress.setText(userSession.getUser().getAddress());
        edtcontact.setText(userSession.getUser().getPhonenumber());
        btnupdate.setOnClickListener(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfileActivity.this, Profile.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
//        updateUser();
    }

    private void updateUser() {
        String n = edtname.getText().toString();
        String e = edtemail.getText().toString();
        String p = edtpassword.getText().toString();
        String a = edtaddress.getText().toString();
        String c = edtcontact.getText().toString();
        String i = userSession.getUser().get_id();

        User user = new User(n, e, p, a, c);

        boolean userUpdated = userBll.updateUser(i, user);
        if (userUpdated) {
            Toast.makeText(this, "UpdateSuccessful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Error updating", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                updateUser();
                startActivity(new Intent(UpdateProfileActivity.this, Profile.class));
                break;
        }
    }
}
