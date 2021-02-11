package com.example.assignment2_musicplayer.second;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment2_musicplayer.R;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class SecondFragment extends Fragment {
    
    Button btn_connectionCheck;
    Button btn_downloadMusic;

    Button btn_playDownloaded;

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
        
        btn_downloadMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using download manager to download a file from webserver

                Uri uri = Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

//                this function will store the file in public directory
//                 request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Music_file"+"");

//                This function will store the file in app local directory
                request.setDestinationInExternalFilesDir(getContext(),Environment.DIRECTORY_DOWNLOADS,"Music_file");
//

                Toast.makeText(getContext()," Connected to the webserver",Toast.LENGTH_SHORT).show();
                downloadManager.enqueue(request);
                Toast.makeText(getContext()," Downloading Completed",Toast.LENGTH_SHORT).show();



//                play download button is visible once download is complete
                btn_playDownloaded.setVisibility(View.VISIBLE);
//
            }
        });

        btn_playDownloaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                get the downloaded music
//                play the downloaded music
//                String path = getContext().getFilesDir() +"/"+"Music_file.mp3";
//
//                MediaPlayer player = new MediaPlayer();
//
//                try {
//                    player.setDataSource(path);
//                    player.prepare();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    System.out.println("Exception of type : " + e.toString());
//                    e.printStackTrace();
//                }
//
//                player.start();

            }
        });

        return view;
    }
}