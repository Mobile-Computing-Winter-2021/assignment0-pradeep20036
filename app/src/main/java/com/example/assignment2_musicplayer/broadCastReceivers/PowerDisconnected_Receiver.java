package com.example.assignment2_musicplayer.broadCastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerDisconnected_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Power Disconnected Warning",Toast.LENGTH_SHORT).show();



    }
}
