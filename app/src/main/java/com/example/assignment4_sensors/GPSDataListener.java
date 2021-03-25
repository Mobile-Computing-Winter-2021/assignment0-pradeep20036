package com.example.assignment4_sensors;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

import com.example.assignment4_sensors.database.SensorsDao;
import com.example.assignment4_sensors.database.SensorsDatabase;
import com.example.assignment4_sensors.database.entities.Gps_data;

import static android.content.Context.LOCATION_SERVICE;

public class GPSDataListener extends AndroidViewModel implements LocationListener {

    private final Context mContext;
    Location location;
    //    double latitude;
//    double longitude;
//    boolean isGPSEnabled;
    private SensorsDao sensorDao;
    private SensorsDatabase sensorDatabase;
    protected LocationManager locationManager;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 2000;

    public GPSDataListener(@NonNull Application application) {
        super(application);
        this.mContext = application;
        sensorDatabase = SensorsDatabase.getDatabase(application);
        sensorDatabase.getDatabase(application);
        getLocation();

    }


    @SuppressLint("MissingPermission")
    public void getLocation() {
        try {
            locationManager = (LocationManager) mContext.getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,GPSDataListener.this);
        }
        catch(Exception e){
            Log.e("LOCATION",e.getCause()+"..."+e.getMessage());
        }

    }

    public void stopGettingLocationUpdates(){
        locationManager.removeUpdates(GPSDataListener.this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.e("LOCATION UPDATES","LATITUDE: "+location.getLatitude()+" Longitude: "+location.getLongitude());
        Long tsLong = System.nanoTime();
        String ts = tsLong.toString();
        Gps_data obj = new Gps_data(tsLong,(float)location.getLatitude(),(float)location.getLongitude());
        System.out.println(location.getLatitude()+" : "+location.getLongitude());
        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sensorDatabase.sensorsDao().insertGps(obj);
            }
        });

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

}
