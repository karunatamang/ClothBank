package com.example.clothbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothbank.R;
import com.example.clothbank.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {
    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_show, parent, false);
       MyHolder myHolder = new MyHolder(view);
       return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final User user= userList.get(position);
        holder.textView.setText(user.getFullname());
        holder.imageView.setImageResource(R.drawable.ic_profile_24dp);

    }

    @Override
    public int getItemCount() {
       return userList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
       TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.name);

        }
    }
}
