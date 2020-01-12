package com.example.clothbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothbank.Helper.UserSession;
import com.example.clothbank.bll.LoginBll;
import com.example.clothbank.model.User;
import com.example.clothbank.response.LoginResponse;
import com.example.clothbank.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    EditText editemail, editpassword;
    Button Login;
    TextView register;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editemail = findViewById(R.id.editEmail);
        editpassword = findViewById(R.id.editPassword);
        Login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.register);

        final UserSession userSession=new UserSession(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    email = editemail.getText().toString();
                    password = editpassword.getText().toString();

                    User user = new User(email, password);
                    LoginBll loginBll = new LoginBll();
                    StrictModeClass.StrictMode();

                    LoginResponse loginResponse=loginBll.userlogin(user);
                    if (loginResponse==null){
                        Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    }else{
                        User loggedInUser=loginResponse.getUser();
                        userSession.startSession(loggedInUser,loginResponse.getToken());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(editemail.getText().toString())) {
            editemail.setError("invalid email address or phone number");
            editemail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editpassword.getText().toString())) {
            editpassword.setError("Invalid password");
            editpassword.requestFocus();
            return false;
        }
        return true;
    }
}


