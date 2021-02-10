package com.example.assignment2_musicplayer.broadCastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastReceivers  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()))
    {
        Toast.makeText(context,"Power Disconnected Warning",Toast.LENGTH_SHORT).show();
    }
    if(Intent.ACTION_BATTERY_LOW.equals(intent.getAction()))
    {
        Toast.makeText(context,"Battery Low Warning",Toast.LENGTH_SHORT).show();
    }

    if(Intent.ACTION_BATTERY_OKAY.equals(intent.getAction()))
    {
        Toast.makeText(context,"Battery is Okay!!!",Toast.LENGTH_SHORT).show();
    }
    }
}
