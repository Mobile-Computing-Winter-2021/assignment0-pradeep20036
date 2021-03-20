package com.example.assignment4_sensors.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accelerometer_table")
public class Accelerometer_data {

    @PrimaryKey(autoGenerate = true)
    String time;
    double x_dir;
    double y_dir;
    double z_dir;

    public Accelerometer_data(String time,double x_dir, double y_dir, double z_dir) {
        this.x_dir = x_dir;
        this.y_dir = y_dir;
        this.z_dir = z_dir;
        this.time=time;
    }

    public double getX_dir() {
        return x_dir;
    }

    public double getY_dir() {
        return y_dir;
    }

    public double getZ_dir() {
        return z_dir;
    }

    public String getTime() {
        return time;
    }


}
