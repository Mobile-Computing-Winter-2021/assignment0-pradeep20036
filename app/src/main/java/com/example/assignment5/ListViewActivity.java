package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recycler);
        recyclerView=findViewById(R.id.recyclerview);

        ArrayList<String> wifiNames = (ArrayList<String>) getIntent().getSerializableExtra("wifiname");
        ArrayList<Integer> wifiSignal = (ArrayList<Integer>) getIntent().getSerializableExtra("signalStrength");

        ArrayList<ProfileModel> profiles=new ArrayList<>();
        for(int i=0;i<wifiNames.size();i++){
            profiles.add(new ProfileModel(wifiNames.get(i),wifiSignal.get(i)));
        }


        //        passing data to the list
        ProfileAdapter adapter=new ProfileAdapter(getApplicationContext(),profiles);
//setting the adapter
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linear_layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linear_layoutManager);


    }
}