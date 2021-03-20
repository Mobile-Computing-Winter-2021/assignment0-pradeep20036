package com.example.assignment4_sensors.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lightdata_table")
public class Light_data {

    @PrimaryKey(autoGenerate = true)
    String time;
    double light_lux;  // light in lux

    public Light_data(String time, double light_lux) {
        this.time = time;
        this.light_lux = light_lux;
    }

}
