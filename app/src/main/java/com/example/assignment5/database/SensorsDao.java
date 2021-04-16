package com.example.assignment5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.assignment5.database.entities.Room1;
import com.example.assignment5.database.entities.Room2;
import com.example.assignment5.database.entities.Room3;

import java.util.List;

@Dao
public interface SensorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRoom1(Room1 room1_data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRoom2(Room2 room2_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRoom3(Room3 room3_data);


    @Query("select * from Room1")
    public List<Room1> getRoom1();

    @Query("select * from Room2")
    public List<Room2> getRoom2();

    @Query("select * from Room3")
    public List<Room3> getRoom3();

    @Query("DELETE FROM Room1")
    void deleteAllRoom1();


    @Query("DELETE FROM Room2")
    void deleteAllRoom2();

    @Query("DELETE FROM Room3")
    void deleteAllRoom3();





}
