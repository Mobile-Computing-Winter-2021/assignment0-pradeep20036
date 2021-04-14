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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityDataCollection extends AppCompatActivity {
    private WifiManager wifiManager;
    private WifiReceiver1 wifiReceiver;
    private WifiInfo wifiInfo;
    ArrayList<String> namesConnection;
    ArrayList<Integer> signalStrength;
    private List<ScanResult> results;
    ArrayList<ArrayList<Integer>> database;
    TextView message;
    TextView result;

    int rooms;
    int totalrooms;
    EditText roomnumber;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);

        roomnumber=findViewById(R.id.et_number);

        message=findViewById(R.id.tv_message);
        result=findViewById(R.id.tv_result);
        result.setVisibility(View.INVISIBLE);

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
        Toast.makeText(getApplicationContext(),"Getting RSSI Values",Toast.LENGTH_SHORT).show();
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



    public void getRssiValues(View view) {

        scanWifi();
//        Toast.makeText(getApplicationContext(),signalStrength.size()+"",Toast.LENGTH_SHORT).show();

        if(rooms>0){
            database.add(signalStrength);
            Toast.makeText(getApplicationContext(),"Database Size: "+database.size()+"",Toast.LENGTH_SHORT).show();
            updateui();
            rooms--;
        }
        else{
            Toast.makeText(getApplicationContext(),"Data taken for all rooms",Toast.LENGTH_SHORT).show();
            updateui();
        }

    }

    private void updateui() {
        Log.i("Info","inside updateUI");
        if((totalrooms-rooms+1)>totalrooms){
            message.setText("Data Collected Successfully");
        }else{
            message.setText("Press Get RSSI to take values for Room Number: "+(totalrooms-rooms+1));
        }
    }

    public void setRooms(View view) {
        rooms=Integer.parseInt(roomnumber.getText().toString());
        totalrooms=rooms;
        Toast.makeText(getApplicationContext(),"Successfully Submitted",Toast.LENGTH_SHORT).show();


    }

    public int distance(ArrayList<Integer>list1,ArrayList<Integer>list2){
        int distance=0;
        for(int i=0;i<list1.size();i++){
            distance+=Math.abs(list1.get(i)-list2.get(i))*Math.abs(list1.get(i)-list2.get(i));
        }
        return (int)Math.pow(distance,0.5);
    }

    public void checkLocation(View view) {
//        Log.i("Database", "checkLocation: "+database);
        Log.i("Info",database+" ");
        Toast.makeText(getApplicationContext(),database+"",Toast.LENGTH_SHORT).show();
        System.out.println(database);
//        getting the current rssi values
//        finding the values closest to the current rssi values
        scanWifi();
        ArrayList<Integer> current_rssi;
        current_rssi=signalStrength;
        System.out.println("Current signal: "+current_rssi);

        int min_distance=Integer.MAX_VALUE;
        int min_index=0;
        String ans="";
        for(ArrayList<Integer> entry:database){

            int tempdistance=distance(current_rssi,entry);
            ans+=tempdistance+" : ";
            System.out.println(tempdistance);
            if(tempdistance<min_distance){
                min_distance=tempdistance;
                min_index=database.indexOf(entry);
            }
        }


        result.setText("Location:"+(min_index+1));
        result.setVisibility(View.VISIBLE);
        message.setText(ans);













    }




    class WifiReceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}