package com.example.clothbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothbank.R;
import com.example.clothbank.model.Donation;
import com.example.clothbank.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.MyHolder> {
    List<Donation> donationList;
    private Context context;

    public DonationAdapter(Context context, List<Donation> donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_donate, parent, false);
        DonationAdapter.MyHolder myHolder = new MyHolder(view);
        return myHolder; 
    }

    @Override
    public void onBindViewHolder(@NonNull DonationAdapter.MyHolder holder, int position) {
        final Donation donation = donationList.get(position);
        holder.textView.setText(donation.getDonor());
        Picasso.get().load(Url.IMAGE_PATH + donation.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView;
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circleimage);
            textView = itemView.findViewById(R.id.textprofile);
            imageView = itemView.findViewById(R.id.uppic);
        }
    }
}
