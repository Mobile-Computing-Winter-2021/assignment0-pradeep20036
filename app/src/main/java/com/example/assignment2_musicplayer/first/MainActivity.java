package com.example.assignment2_musicplayer.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import com.example.assignment2_musicplayer.R;
import com.example.assignment2_musicplayer.broadCastReceivers.BatteryLow_Receiver;
import com.example.assignment2_musicplayer.broadCastReceivers.BatteryOK_Receiver;
import com.example.assignment2_musicplayer.broadCastReceivers.PowerDisconnected_Receiver;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    Button btn_stop;

//    declaring variables of broadcast receivers
    BatteryLow_Receiver batteryLow_receiver;
    BatteryOK_Receiver batteryOK_receiver;
    PowerDisconnected_Receiver powerDisconnected_receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        register all the broadcast receivers

        batteryLow_receiver=new BatteryLow_Receiver();
        batteryOK_receiver=new BatteryOK_Receiver();
        powerDisconnected_receiver=new PowerDisconnected_Receiver();

        registerReceiver(batteryLow_receiver,new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(batteryOK_receiver,new IntentFilter(Intent.ACTION_BATTERY_OKAY));
        registerReceiver(powerDisconnected_receiver,new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));


//        loading the fragment in the activity on start
        FirstFragment firstFragment=new FirstFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.linearLayout,firstFragment);
        transaction.commit();



    }




}