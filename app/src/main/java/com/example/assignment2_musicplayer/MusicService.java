package com.example.assignment2_musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

//Reference Doc : https://www.tutorialspoint.com/what-is-android-background-music-service

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
        musicPlayer = MediaPlayer.create(this, R.raw.mirzapur);
        musicPlayer.setLooping(false);
        musicPlayer.setVolume(100,100);
        Log.d("State","service Started");

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Music Player Service Started", Toast.LENGTH_SHORT).show();
        musicPlayer.start();
        return START_STICKY;
    }
    public void onPause(){
        musicPlayer.pause();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        Toast.makeText(this, "Music Service Ended", Toast.LENGTH_SHORT).show();
    }

}
