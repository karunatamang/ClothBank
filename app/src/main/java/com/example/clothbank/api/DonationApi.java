package com.example.clothbank.api;

import com.example.clothbank.model.Donation;
import com.example.clothbank.model.Donations;
import com.example.clothbank.response.ClothResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DonationApi {

    @POST("donate")
    Call<Donation> donateCloth(@Body Donation donation);

    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part MultipartBody.Part img);

    @GET("donations")
    Call<List<Donations>> viewDonation();



    @PUT("update/{id}")
    Call<String> updateDonation(@Path("id") String id, @Body Donation donation);

    @DELETE("delete/{id}")
    Call<String> deleteDonation(@Path("id") String id);


}
