package com.example.assignment4_sensors.database.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "temperature_table")
public class Temperature_data {

    @PrimaryKey(autoGenerate = true)
    String time;
    double temperature;  // temp in celcius

    public Temperature_data(String time, double temperature) {
        this.time = time;
        this.temperature = temperature;
    }


}
