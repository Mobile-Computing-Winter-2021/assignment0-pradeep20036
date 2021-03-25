package com.example.assignment4_sensors.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment4_sensors.database.entities.Accelerometer_data;
import com.example.assignment4_sensors.database.entities.Gps_data;
import com.example.assignment4_sensors.database.entities.Light_data;
import com.example.assignment4_sensors.database.entities.LinearAcceleration_data;
import com.example.assignment4_sensors.database.entities.Proximity_data;
import com.example.assignment4_sensors.database.entities.Temperature_data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={Accelerometer_data.class, Gps_data.class, Light_data.class, LinearAcceleration_data.class,
        Proximity_data.class, Temperature_data.class },version = 2,exportSchema = false)
public abstract class SensorsDatabase extends RoomDatabase
{
    public abstract SensorsDao sensorsDao();

    private static volatile SensorsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SensorsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SensorsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SensorsDatabase.class, "sensors_database")
                             .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
