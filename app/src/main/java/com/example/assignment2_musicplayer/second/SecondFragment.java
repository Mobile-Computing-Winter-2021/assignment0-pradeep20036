package com.example.assignment2_musicplayer.second;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment2_musicplayer.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.DOWNLOAD_SERVICE;

public class SecondFragment extends Fragment {

    String url ="http://faculty.iiitd.ac.in/~mukulika/s1.mp3";

    Button btn_connectionCheck;
    Button btn_downloadMusic;
    private int type=1; //1 for internal // 2 for external
    Button btn_playDownloaded;


    DownloadManager.Request t_checker;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_second, container, false);

        btn_connectionCheck=view.findViewById(R.id.btn_checkConnection);


        btn_downloadMusic=view.findViewById(R.id.btn_downloadMusic);


        btn_playDownloaded=view.findViewById(R.id.bt_playDownload);
//        btn_playDownloaded.setVisibility(View.INVISIBLE);

        btn_downloadMusic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {  Async(type);
//             calling exceute function for async task
                new downloadFile().execute(url);
            }
        });

        btn_connectionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
//            checking the connectivity of the internet in the phone

            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getContext()," Network is Connected",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext()," No Internet Is present",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_playDownloaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        return view;
    }


    class downloadFile extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            int count;
            try {
                URL url = new URL(params[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                InputStream input = new BufferedInputStream(url.openStream(),20000);
                OutputStream output = new FileOutputStream(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"MusicFile.mp3");

                if(type==1)
                    t_checker.setDestinationInExternalFilesDir(getContext(),Environment.DIRECTORY_DOWNLOADS,"Music_file");
                if(type==2)
                    t_checker.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Music_file");



                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error Messages: ", e.getMessage());
                System.out.println("Exception Occour in downloading");
            }

            return null;
        }

    }


     void Async(int t){
        Uri uri = Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                this function will store the file in public directory
//                 request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Music_file"+"");
//                This function will store the file in app local directory
         if(t==1)
            request.setDestinationInExternalFilesDir(getContext(),Environment.DIRECTORY_DOWNLOADS,"Music_file");
         if(t==2)
             request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Music_file");

        Toast.makeText(getContext()," Connected to the webserver",Toast.LENGTH_SHORT).show();
        downloadManager.enqueue(request);
        Toast.makeText(getContext()," Downloading Completed",Toast.LENGTH_SHORT).show();
    }









}