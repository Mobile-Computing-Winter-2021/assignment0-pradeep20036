package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import Fragments.DetailsFragment;
import Fragments.RecyclerFragment;
import Model.ProfileModel;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface{

    ArrayList<ProfileModel> profiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Calling ListFragment to display the Fragment

//        System.out.println("Arraylist: mainactivity"+profiles);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new RecyclerFragment(this));
        fragmentTransaction.commit();

    }

    @Override
    public void onItemClick(int position,ArrayList<ProfileModel> profiles) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new DetailsFragment(profiles,position));
        fragmentTransaction.commit();

    }
}