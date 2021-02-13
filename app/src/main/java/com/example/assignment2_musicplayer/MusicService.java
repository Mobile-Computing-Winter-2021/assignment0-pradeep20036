package com.example.assignment2_musicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.assignment2_musicplayer.first.FirstFragment;

import java.io.File;
import java.io.IOException;

public class MusicService extends Service {

    String downloaded="/storage/emulated/0/Android/data/com.example.assignment2_musicplayer/files/Download/pradeep.mp3";

    MediaPlayer musicPlayer;
    public static final String CHANNEL_ID = "service_channel";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        adding the music file to the player
        NotificationChannel();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        Notification for Foreground Service
        Intent notificationIntent=new Intent(getApplicationContext(), FirstFragment.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);

//        Buidling a notification using builder
        Notification notification=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText("Playing Music")
                .setContentTitle("Foreground Service Running")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.musicalnote)
                .build();
//        starting a foreground service
        startForeground(1,notification);

//        checking if the service is already running or not??

        if(musicPlayer!=null)
        {
            musicPlayer.stop();
        }
//
            String songName =intent.getStringExtra("song");
            if(songName==null)
                songName="alexander";

//        selecting the clicked music file to play in the player
            int myUri=0;
            switch (songName)
            {
                case "alexander":
                    myUri =R.raw.alexander;
                    break;
                case "hawayein":
                    myUri =R.raw.hawayein;
                    break;
                case "mirzapur":
                    myUri =R.raw.mirzapur;
                    break;
                case "arkansas":
                    myUri =R.raw.arkansas;
                    break;
                case "inspire":
                    myUri =R.raw.inspire;
                    break;
                case "sauda":
                    myUri =R.raw.sauda;
                    break;
                case "warriyo":
                    myUri =R.raw.warriyo;
                    break;
                case "downloaded":
                {
                     musicPlayer=new MediaPlayer();
                    try {
                        musicPlayer.setDataSource(downloaded);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        musicPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    musicPlayer.start();

                    return START_STICKY;

                }
            }

            musicPlayer=MediaPlayer.create(this,myUri);
            musicPlayer.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        Toast.makeText(this, "Music Service Ended", Toast.LENGTH_SHORT).show();
    }

    private void NotificationChannel() {
//        function
//        boilder plate code for notification Source: Developers Android Documentation
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel foregroundService_channel=new NotificationChannel(CHANNEL_ID,"Playing", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(foregroundService_channel);
        }
    }

}
