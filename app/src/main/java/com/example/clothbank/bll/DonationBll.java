package com.example.clothbank.bll;

import com.example.clothbank.api.DonationApi;
import com.example.clothbank.model.Donation;
import com.example.clothbank.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class DonationBll {
    private DonationApi donationApi;

    public DonationBll() {
        donationApi = Url.getInstance().create(DonationApi.class);
    }

    public Donation donate(Donation donation) {
        Donation donations = null;
        Call<Donation> donationCall = donationApi.donateCloth(donation);
        try {
            Response<Donation> donationResponse = donationCall.execute();
            if (!donationResponse.isSuccessful()) {
                return donations;
            }
            if (donationResponse.body() != null) {
                donations = donationResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return donations;
    }

    public boolean updateDonation(String id,Donation donation) {
        boolean isDonationUpdated = false;

        Call<String> donationCall = donationApi.updateDonation(id,donation);
        try {
            Response<String> donationResponse = donationCall.execute();
            if (!donationResponse.isSuccessful()) {
                return isDonationUpdated;
            }
            if (donationResponse.body() != null) {
                isDonationUpdated = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isDonationUpdated;
    }

}
