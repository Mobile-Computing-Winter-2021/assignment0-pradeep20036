package com.example.assignment2_musicplayer.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import com.example.assignment2_musicplayer.R;
import com.example.assignment2_musicplayer.broadCastReceivers.BroadCastReceivers;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    Button btn_stop;

    BroadCastReceivers broadcastReceivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceivers=new BroadCastReceivers();

        IntentFilter intentFilter=new IntentFilter();

//        adding actions to intent filer
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);


//        registering broadcast receivers
        registerReceiver(broadcastReceivers,intentFilter);



//        loading the fragment in the activity on start
        FirstFragment firstFragment=new FirstFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.linearLayout,firstFragment);
        transaction.commit();



    }




}