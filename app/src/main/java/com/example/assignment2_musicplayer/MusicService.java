package com.example.assignment2_musicplayer;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer musicPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        adding the music file to the player
//
        Log.i("State", "Inside onCreate()");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(musicPlayer!=null)
        {
            musicPlayer.stop();
        }

        String songName =intent.getStringExtra("song");
        if(songName==null)
            songName="alexander";

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

}
