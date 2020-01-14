package com.example.clothbank.bll;

import com.example.clothbank.api.UserApi;
import com.example.clothbank.model.Donation;
import com.example.clothbank.model.User;
import com.example.clothbank.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UserBll {

    private UserApi userApi;

    public UserBll() {
        userApi = Url.getInstance().create(UserApi.class);
    }

    public boolean updateUser(String id, User user) {
        boolean isUserUpdated = false;

        Call<String> userCall = userApi.updateUser(id, user);
        try {
            Response<String> userResponse = userCall.execute();
            if (!userResponse.isSuccessful()) {
                return isUserUpdated;
            }
            if (userResponse.body() != null) {
                isUserUpdated = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUserUpdated;
    }
}
