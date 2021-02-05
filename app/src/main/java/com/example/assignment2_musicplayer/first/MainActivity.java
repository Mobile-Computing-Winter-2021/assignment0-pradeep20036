package com.example.assignment2_musicplayer.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.assignment2_musicplayer.R;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    Button btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        loading the fragment in the activity on start
        FirstFragment firstFragment=new FirstFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.linearLayout,firstFragment);
        transaction.commit();



    }




}