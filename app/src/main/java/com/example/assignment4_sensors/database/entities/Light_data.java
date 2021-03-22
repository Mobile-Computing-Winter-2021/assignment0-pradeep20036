package com.example.assignment4_sensors.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lightdata_table")
public class Light_data {

    @PrimaryKey(autoGenerate = true)
    public long time;
    public double light_lux;  // light in lux

    public Light_data(long time, double light_lux) {
        this.time = time;
        this.light_lux = light_lux;
    }

    public double getLight_lux() {
        return light_lux;
    }
}
