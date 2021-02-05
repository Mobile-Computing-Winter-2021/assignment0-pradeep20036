package com.example.assignment2_musicplayer.first;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.assignment2_musicplayer.MusicService;
import com.example.assignment2_musicplayer.R;
import com.example.assignment2_musicplayer.second.SecondActivity;


public class FirstFragment extends Fragment {

    Button btn_start;
    Button btn_stop;
    Button btn_nextActivity;
    Button btn_pause;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);
        btn_start=view.findViewById(R.id.btn_start);
        btn_stop=view.findViewById(R.id.btn_stop);
        btn_nextActivity=view.findViewById(R.id.btn_nextActivity);
        btn_pause=view.findViewById(R.id.btn_pause);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("State","startService");
                getActivity().startService(new Intent(getActivity(), MusicService.class));
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("State","stopService");
                getActivity().stopService(new Intent(getActivity(), MusicService.class));
            }
        });

        btn_nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MusicService.class);
                intent.putExtra("pause",true);
                getActivity().startService(intent);
            }
        });



        return view;
    }







}