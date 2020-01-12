package com.example.clothbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothbank.api.UserApi;
import com.example.clothbank.model.User;
import com.example.clothbank.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText etname, etemail, etpassword, etaddress, etcontact;
    TextView Logo, txtlogin;
    Button btnsignup;
    String fullname, email, password, address, contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etname=findViewById(R.id.fullname);
        etemail=findViewById(R.id.email);
        etpassword=findViewById(R.id.password);
        etaddress=findViewById(R.id.address);
        etcontact=findViewById(R.id.contact);
        txtlogin=findViewById(R.id.login);
        btnsignup=findViewById(R.id.signup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname=etname.getText().toString();
                email=etemail.getText().toString();
                password=etpassword.getText().toString();
                address=etaddress.getText().toString();
                contact=etcontact.getText().toString();
                if(validate()){
                    User user = new User(fullname, email, password, address, contact);
                    addUser(user);
                    Intent intent= new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
    private void addUser(User user) {
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<Void> userAdd = userApi.addUser(user);
        userAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SignupActivity.this, "Account created sucessfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(fullname)){
            etname.setError("enter your fullname");
            etname.requestFocus();
            return  false;
        }
        if (TextUtils.isEmpty(email)){
            etemail.setError("enter your email");
            etemail.requestFocus();
            return  false;
        }
        if (TextUtils.isEmpty(password)){
            etpassword.setError("enter your password");
            etpassword.requestFocus();
            return  false;
        }
        if (TextUtils.isEmpty(address)){
            etaddress.setError("enter your address");
            etaddress.requestFocus();
            return  false;
        }
        if (TextUtils.isEmpty(contact)){
            etcontact.setError("enter your contact number");
            etcontact.requestFocus();
            return  false;
        }
        return true;

    }
}
