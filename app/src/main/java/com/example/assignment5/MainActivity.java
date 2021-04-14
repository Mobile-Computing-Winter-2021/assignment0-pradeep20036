package com.example.assignment5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private WifiReceiver wifiReceiver;
    private WifiInfo wifiInfo;
    ArrayList<String> namesConnection;
    ArrayList<Integer> signalStrength;
    //    private WifiReceiver receiver;
    private ListView networksList;
    private List<ScanResult> results;
    TextView tv;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tv=findViewById(R.id.textView);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReceiver=new WifiReceiver();
        wifiInfo=wifiManager.getConnectionInfo();
        if(wifiInfo==null){
            Toast.makeText(getApplicationContext(),"Wifi Info is Null",Toast.LENGTH_SHORT).show();
        }

        registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }

    }

    private void scanWifi() {
        Toast.makeText(getApplicationContext(),"inside scanwifi",Toast.LENGTH_SHORT).show();
        wifiManager.startScan();
        results=wifiManager.getScanResults();

        namesConnection=new ArrayList<>();
        signalStrength=new ArrayList<>();

        int count =3;
        for(ScanResult wifi: results){
            namesConnection.add(wifi.SSID);
            signalStrength.add(wifi.level);

            if(count--<0){
                break;
            }
        }

        Log.i("List", "scanWifi: "+namesConnection);
        Log.i("List", "Strength: "+signalStrength);



    }

    public void plot(){
        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<namesConnection.size();i++){
            entries.add(new BarEntry(signalStrength.get(i), i));
        }

        BarDataSet bardataset = new BarDataSet(entries, "Connections");
        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0;i<namesConnection.size();i++){
            labels.add(namesConnection.get(i));
        }

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("Set Bar Chart Description Here");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);
        BarData.generateXVals(0,2);

    }

    public void checkNearby(View view) {
        scanWifi();
        plot();
    }

    public void dataCollection(View view) {

        Intent i = new Intent(getApplicationContext(),ActivityDataCollection.class);
        startActivity(i);
    }

    class WifiReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

}