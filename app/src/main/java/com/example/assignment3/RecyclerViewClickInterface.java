package com.example.assignment3;

import java.util.ArrayList;

import Model.ProfileModel;

public interface RecyclerViewClickInterface {
    void onItemClick(int position, ArrayList<ProfileModel> profiles);
}
