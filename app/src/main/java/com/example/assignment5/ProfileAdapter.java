package com.example.assignment5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    static ArrayList<ProfileModel> profiles;
    Context context;
    static boolean flag=false;
    public ProfileAdapter(Context context,ArrayList<ProfileModel> profiles) {
        this.context = context;
        this.profiles=profiles;
    }



    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_recycleview,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {

        ProfileModel profile=profiles.get(position);
        holder.image.setImageResource(R.drawable.wifi);
        holder.wifi_strength.setText(profile.getRssi()+"");
        holder.wifiname.setText(profile.getWifi_name());

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView wifiname;
        TextView wifi_strength;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.iv_image);
            wifiname=itemView.findViewById(R.id.tv_wifiname);
            wifi_strength=itemView.findViewById(R.id.tv_wifiStength);

        }
    }
}
