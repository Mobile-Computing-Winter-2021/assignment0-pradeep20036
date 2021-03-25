package com.example.assignment4_sensors.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gps_table")
public class Gps_data {
@PrimaryKey
    public long time;
    public float latitude;
    public float longitude;

    public Gps_data(long time, float latitude, float longitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
