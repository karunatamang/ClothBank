package com.example.clothbank.api;

import com.example.clothbank.model.Donation;
import com.example.clothbank.model.User;
import com.example.clothbank.response.ClothResponse;
import com.example.clothbank.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @POST("login")
    Call<LoginResponse> userlogin(@Body User user);

    @GET("user")
    Call<List<User>> showUser();

    @POST("register")
    Call<Void> addUser(@Body User user);

    @PUT("update/{id}")
    Call<String> updateUser(@Path("id") String id, @Body User user);

    @GET("userdetail/{id}")
    Call<User> updateUser(@Path("id") String id);

    @GET("viewdonation/{donor}")
    Call<ClothResponse> getNoofCloth(@Path("donor")String donor);


}
