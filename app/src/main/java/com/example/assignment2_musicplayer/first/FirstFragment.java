package com.example.assignment2_musicplayer.first;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignment2_musicplayer.MusicService;
import com.example.assignment2_musicplayer.R;
import com.example.assignment2_musicplayer.second.SecondActivity;

import static android.content.Context.DOWNLOAD_SERVICE;

public class FirstFragment extends Fragment {

    Button btn_start;
    Button btn_stop;
    Button btn_nextActivity;
    Button btn_pause;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

//      ListView Handling
        listView=view.findViewById(R.id.lv_musiclist);

        String[] music = new String[] { "alexander", "arkansas", "hawayein",
                "inspire" ,"mirzapur", "sauda", "warriyo"};
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getContext(), music);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "clicked : " + music[position], Toast.LENGTH_SHORT).show();

//                On click on the item, playing that music.
                Intent intent=new Intent(getContext(), MusicService.class);
                intent.putExtra("song",music[position]);
                getActivity().startService(intent);

            }
        });



        btn_start=view.findViewById(R.id.btn_checkConnection);
        btn_stop=view.findViewById(R.id.btn_downloadMusic);
        btn_nextActivity=view.findViewById(R.id.btn_nextActivity);


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

        return view;
    }









}