package com.example.assignment2_musicplayer.second;

import android.app.DownloadManager;
import android.content.Context;
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

import static android.content.Context.DOWNLOAD_SERVICE;

public class SecondFragment extends Fragment {
    
    Button btn_connectionCheck;
    Button btn_downloadMusic;
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
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Music_file"+".mp3");

                Toast.makeText(getContext()," Connected to the webserver",Toast.LENGTH_SHORT).show();
                downloadManager.enqueue(request);
                Toast.makeText(getContext()," Downloading Completed",Toast.LENGTH_SHORT).show();

            }
        });
        
        
        
        return view;
    }
}