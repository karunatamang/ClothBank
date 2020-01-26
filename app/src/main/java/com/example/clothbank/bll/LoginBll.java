package com.example.clothbank.bll;

import com.example.clothbank.api.UserApi;
import com.example.clothbank.model.User;
import com.example.clothbank.response.LoginResponse;
import com.example.clothbank.strictmode.StrictModeClass;
import com.example.clothbank.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class LoginBll {
    private UserApi userApi = Url.getInstance().create(UserApi.class);


    public LoginResponse userlogin(User user) {
        LoginResponse loginResponse = null;
        Call<LoginResponse> userCall = userApi.userlogin(user);
        StrictModeClass.StrictMode();
        try {
            Response<LoginResponse> response = userCall.execute();
            if (!response.isSuccessful()) {
                return loginResponse;
//                isloggedIn = true;
//                Url.token += loginResponse.body().getToken();
            }
            if (response.body() != null) {
                loginResponse = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginResponse;
    }


}
