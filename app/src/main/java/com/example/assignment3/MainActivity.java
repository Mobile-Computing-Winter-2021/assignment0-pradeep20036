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

        if(savedInstanceState==null)
        {
            profiles=new ArrayList<>();
            System.out.println("Creating New ArrayList");
            profiles.add(new ProfileModel("pradeep Kumar","pradeep20036@gmail.com","MT20036","CSE"));
            profiles.add(new ProfileModel("Nitika Bansal","Nitika20032@gmail.com","MT20036","CSE"));
            profiles.add(new ProfileModel("Akanksha Pandey","pradeep20031@gmail.com","MT20036","CSE"));
            profiles.add(new ProfileModel("Shivank Agrahari","pradsddeep20030@gmail.com","MT20036","CSE"));
            profiles.add(new ProfileModel("Deepankar Kansal","deep20029@gmail.com","MT20036","CSE"));
        }
//        Calling ListFragment to display the Fragment

        System.out.println("Arraylist: mainactivity"+profiles);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new RecyclerFragment(profiles,this));
        fragmentTransaction.commit();

    }

    @Override
    public void onItemClick(int position) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new DetailsFragment(profiles,position));
        fragmentTransaction.commit();

    }
}