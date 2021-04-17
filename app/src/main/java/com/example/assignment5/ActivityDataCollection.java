package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment5.database.Conversion;
import com.example.assignment5.database.SensorsDatabase;
import com.example.assignment5.database.entities.Room1;
import com.example.assignment5.database.entities.Room2;
import com.example.assignment5.database.entities.Room3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class ActivityDataCollection extends AppCompatActivity {
    private WifiManager wifiManager;
    private WifiReceiver1 wifiReceiver;
    private WifiInfo wifiInfo;
    ArrayList<String> namesConnection;
    ArrayList<Integer> signalStrength;
    private List<ScanResult> results;
    ArrayList<ArrayList<Integer>> database;

    SensorsDatabase sensorsDatabase;

    TextView message;
    TextView result;
    ArrayList<Integer> room_readings;
    int rooms;
    int totalrooms;
    EditText roomnumber;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);

        sensorsDatabase = SensorsDatabase.getDatabase(this);

//        message=findViewById(R.id.tv_message);
        result=findViewById(R.id.tv_result);
        wifiReceiver=new WifiReceiver1();
        wifiInfo=wifiManager.getConnectionInfo();
        database=new ArrayList<>();
        if(wifiInfo==null){
            Toast.makeText(getApplicationContext(),"Wifi Info is Null",Toast.LENGTH_SHORT).show();
        }

        registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
    }

    private void scanWifi() {
//        Toast.makeText(getApplicationContext(),"Getting RSSI Values",Toast.LENGTH_SHORT).show();
        wifiManager.startScan();
        results=wifiManager.getScanResults();
        namesConnection=new ArrayList<>();
        signalStrength=new ArrayList<>();
        int count =6;
        for(ScanResult wifi: results){
            namesConnection.add(wifi.SSID);
            signalStrength.add(wifi.level);
            if(count--<0){
                break;
            }
        }
    }

    public void checkLocation(View view) {

//        getting the data and comparing it with the points present in the database.

        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
             List<Room1> table1=   sensorsDatabase.sensorsDao().getRoom1();
             List<Room2> table2=   sensorsDatabase.sensorsDao().getRoom2();
             List<Room3> table3=   sensorsDatabase.sensorsDao().getRoom3();

//             computing the average of table1
                ArrayList<Integer>  ans1=new ArrayList<>();
                ArrayList<Integer>  ans2;
                for(int i=0;i<table1.size();i++){
                    if(i==0){
                        ans1=Conversion.convertToArrayList(table1.get(0).data);
                    }
                    else{
                        ans2=Conversion.convertToArrayList(table1.get(0).data);
                        if(ans1.size()<=ans2.size()){
                            for(int k=0;k<ans1.size();k++){
                                ans1.set(k,ans1.get(k)+ans2.get(k));
                            }
                        }
                    }
                }

                for(int k=0;k<ans1.size();k++){
                    ans1.set(k,ans1.get(k)/table1.size());
                }


//             computing the average of table2
                ArrayList<Integer>  ans3=new ArrayList<>();
                ArrayList<Integer>  ans4;
                for(int i=0;i<table2.size();i++){
                    if(i==0){
                        ans3=Conversion.convertToArrayList(table2.get(0).data);
                    }
                    else{
                        ans4=Conversion.convertToArrayList(table2.get(0).data);
                        if(ans3.size()<=ans4.size()){
                            for(int k=0;k<ans3.size();k++){
                                ans3.set(k,ans1.get(k)+ans4.get(k));
                            }
                        }
                    }
                }

                for(int k=0;k<ans3.size();k++){
                    ans3.set(k,ans3.get(k)/table2.size());
                }

//             computing the average of table3
                ArrayList<Integer>  ans5=new ArrayList<>();
                ArrayList<Integer>  ans6;
                for(int i=0;i<table3.size();i++){
                    if(i==0){
                        ans5=Conversion.convertToArrayList(table3.get(0).data);
                    }
                    else{
                        ans6=Conversion.convertToArrayList(table3.get(0).data);
                        if(ans5.size()<=ans6.size()){
                            for(int k=0;k<ans5.size();k++){
                                ans5.set(k,ans5.get(k)+ans6.get(k));
                            }
                        }
                    }
                }

                for(int k=0;k<ans5.size();k++){
                    ans5.set(k,ans5.get(k)/table3.size());
                }



//                getting the location...

//            getRoomLocation(ans1,ans3,ans5);
            getRoomLocationVersion2(table1,table2,table3);

            }

        });

        updateui();
        }

    private void updateui() {
    }

    public int distance(ArrayList<Integer>list1,ArrayList<Integer>list2){
        int distance=0;
        for(int i=0;i<Math.min(list1.size(),list2.size());i++){
            distance+=Math.abs(list1.get(i)-list2.get(i))*Math.abs(list1.get(i)-list2.get(i));
        }
        return (int)Math.pow(distance,0.5);
    }


    private void getRoomLocationVersion2(List<Room1> table1,List<Room2> table2,List<Room3>table3){

//        getting your current location
        scanWifi();
        ArrayList<Integer> current=signalStrength;

        int min1=Integer.MAX_VALUE;
        for(Room1 entry: table1){
            int dist=distance(Conversion.convertToArrayList(entry.data),current);
            min1=Math.min(min1,dist);
        }

        int min2=Integer.MAX_VALUE;
        for(Room2 entry: table2){
            int dist=distance(Conversion.convertToArrayList(entry.data),current);
            min2=Math.min(min2,dist);
        }

        int min3=Integer.MAX_VALUE;
        for(Room3 entry: table3){
            int dist=distance(Conversion.convertToArrayList(entry.data),current);
            min3=Math.min(min3,dist);
        }

        int room=1;



        int dist1=min1;
        int dist2=min2;
        int dist3=min3;

        if (dist1 < dist2) {
            if(dist1<dist3){
                room=1;
            }else{
                room=3;
            }
        }else{
            if(dist2<dist3){
                room=2;
            }else{
                room=3;
            }
        }

        result.setText("Location Room: "+room);


    }

    private void getRoomLocation(ArrayList<Integer> ans1,ArrayList<Integer> ans2,ArrayList<Integer>ans3) {

        scanWifi();
        ArrayList<Integer> ans=signalStrength;

        int room=1;

        int max=0;

        int dist1=distance(ans,ans1);
        int dist2=distance(ans,ans2);
        int dist3=distance(ans,ans3);

        if (dist1 < dist2) {
            if(dist1<dist3){
                room=1;
            }else{
                room=3;
            }
        }else{
            if(dist2<dist3){
                room=2;
            }else{
                room=3;
            }
        }

        result.setText("Location Room: "+room);
        System.out.println("location Room "+room);
//        computing the similarity

    }

    int room1=0;
    int room2=0;
    int room3=0;

    public void getRssiRoom1(View view) {
        scanWifi();
        ArrayList<Integer> strength=signalStrength;
        Room1 strengthData=new Room1(room1, Conversion.fromArrayList(signalStrength));
        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sensorsDatabase.sensorsDao().insertRoom1(strengthData);
            }
        });

        Toast.makeText(getApplicationContext(),"Room1 data Successfully Inserted: reading: "+(room1+1),Toast.LENGTH_SHORT).show();
        room1++;
    }

    public void getRssiRoom2(View view) {
        scanWifi();
        ArrayList<Integer> strength=signalStrength;
        Room2 strengthData=new Room2(room2,Conversion.fromArrayList(strength));
        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sensorsDatabase.sensorsDao().insertRoom2(strengthData);
            }
        });

        Toast.makeText(getApplicationContext(),"Room2 data Successfully Inserted: reading: "+(room2+1),Toast.LENGTH_SHORT).show();
        room2++;
    }

    public void getRssiRoom3(View view) {
        scanWifi();
        ArrayList<Integer> strength=signalStrength;
        Room3 strengthData=new Room3(room3,Conversion.fromArrayList(strength));
        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sensorsDatabase.sensorsDao().insertRoom3(strengthData);
            }
        });

        Toast.makeText(getApplicationContext(),"Room3 data Successfully Inserted: reading: "+(room3+1),Toast.LENGTH_SHORT).show();
        room3++;
    }

    public void resetDatabase(View view) {

        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sensorsDatabase.sensorsDao().deleteAllRoom1();
                sensorsDatabase.sensorsDao().deleteAllRoom2();
                sensorsDatabase.sensorsDao().deleteAllRoom3();
            }
        });

    }

    class WifiReceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}