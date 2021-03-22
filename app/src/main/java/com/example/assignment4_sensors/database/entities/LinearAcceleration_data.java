package com.example.assignment4_sensors.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "linearacceleration_table")
public class LinearAcceleration_data {

    @PrimaryKey(autoGenerate = true)
   public long time;
    public double x_dir;
    public double y_dir;
    public double z_dir;

    public LinearAcceleration_data(long time,double x_dir, double y_dir, double z_dir) {
        this.x_dir = x_dir;
        this.y_dir = y_dir;
        this.z_dir = z_dir;
        this.time=time;
    }

}
