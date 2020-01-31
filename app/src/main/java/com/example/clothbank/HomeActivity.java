package com.example.clothbank;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothbank.adapter.DonationAdapter;
import com.example.clothbank.api.DonationApi;
import com.example.clothbank.model.Donation;
import com.example.clothbank.model.Donations;
import com.example.clothbank.url.Url;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Donations> donationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.donorrecycler);

        donationList = new ArrayList<>();

        getDonation();

        DonationAdapter donationAdapter = new DonationAdapter(HomeActivity.this, donationList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(donationAdapter);


    }


    private void getDonation() {
        DonationApi donateApi = Url.getInstance().create(DonationApi.class);
        Call<List<Donations>> donationListCall = donateApi.viewDonation();

        try {
            Response<List<Donations>> response = donationListCall.execute();
            donationList = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
