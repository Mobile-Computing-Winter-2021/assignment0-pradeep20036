package com.example.assignment2_musicplayer.second;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment2_musicplayer.R;

public class SecondFragment extends Fragment {
    
    Button btn_connectionCheck;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View view=inflater.inflate(R.layout.fragment_second, container, false);
        
        btn_connectionCheck=view.findViewById(R.id.btn_checkConnection);
        btn_connectionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getContext()," Network is Connected",Toast.LENGTH_SHORT).show();

//                    Downloading the file from the server


                } else {
                    Toast.makeText(getContext()," Network is Disconnected",Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
        
        
        
        return view;
    }
}