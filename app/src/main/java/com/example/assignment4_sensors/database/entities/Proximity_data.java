package com.example.assignment4_sensors.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "proximity_table")
public class Proximity_data {

    @PrimaryKey(autoGenerate = true)
    public long time;
    public double distance;

    public Proximity_data(long time, double distance) {
        this.time = time;
        this.distance = distance;
    }


}
