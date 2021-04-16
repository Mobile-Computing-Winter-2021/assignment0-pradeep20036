package com.example.assignment5.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.assignment5.database.entities.Room1;
import com.example.assignment5.database.entities.Room2;
import com.example.assignment5.database.entities.Room3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={Room1.class, Room2.class, Room3.class },version = 2,exportSchema = false)
@TypeConverters({Conversion.class})
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
