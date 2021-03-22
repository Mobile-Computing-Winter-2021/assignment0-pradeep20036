package com.example.assignment4_sensors.database;

import android.telephony.CarrierConfigManager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.assignment4_sensors.database.entities.Accelerometer_data;
import com.example.assignment4_sensors.database.entities.Gps_data;
import com.example.assignment4_sensors.database.entities.Light_data;
import com.example.assignment4_sensors.database.entities.LinearAcceleration_data;
import com.example.assignment4_sensors.database.entities.Proximity_data;
import com.example.assignment4_sensors.database.entities.Temperature_data;

import java.util.List;

@Dao
public interface SensorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAccelerometer(Accelerometer_data accelerometer_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLight(Light_data light_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTemperature(Temperature_data temperature_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertProximity(Proximity_data proximity_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLinearAcceleration(LinearAcceleration_data linearAcceleration_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGps(Gps_data gps_data);

    @Query("select * from accelerometer_table")
    public List<Accelerometer_data> getAccelerometerData();

    @Query("select * from accelerometer_table where time>:current_time-3600000")
    public List<Accelerometer_data> getOneHourData(long current_time);

    @Query("select * from lightdata_table where time>:current_time-3600000")
    public List<Light_data> getOneHourDataLight(long current_time);

    @Query("select * from lightdata_table")
    public List<Light_data> getLightData();

    @Query("select * from proximity_table")
    public List<Proximity_data> getProximityData();

    @Query("select * from temperature_table")
    public List<Temperature_data> getTemperatureData();

    @Query("select * from linearacceleration_table")
    public List<LinearAcceleration_data> getLinearAccelerationData();

    @Query("select * from gps_table")
    public List<Gps_data> getGpsData();





}
