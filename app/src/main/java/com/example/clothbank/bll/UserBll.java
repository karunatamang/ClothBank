package com.example.clothbank.bll;

import com.example.clothbank.api.DonationApi;
import com.example.clothbank.api.UserApi;
import com.example.clothbank.model.Donation;
import com.example.clothbank.model.User;
import com.example.clothbank.response.ClothResponse;
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

    public User userdetail(String id) {
        User userdetail = null;

        Call<User> clothcall = userApi.updateUser(id);
        try {
            Response<User> userResponse = clothcall.execute();
            if (!userResponse.isSuccessful()) {
                return userdetail;
            }
            if (userResponse.body() != null) {
                userdetail = userResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userdetail;
    }
    public ClothResponse getNoofCloth(String donor) {
        ClothResponse cloth = null;

        Call<ClothResponse> userCall = userApi.getNoofCloth(donor);
        try {
            Response<ClothResponse> clothResponse = userCall.execute();
            if (!clothResponse.isSuccessful()) {
                return cloth;
            }
            if (clothResponse.body() != null) {
                cloth = clothResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cloth;
    }
}
