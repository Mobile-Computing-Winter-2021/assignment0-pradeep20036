package com.example.assignment5.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "Room2")

public class Room2 {
    @PrimaryKey
    public int id;
    public String data;

    public Room2(int id, String data) {
        this.id = id;
        this.data = data;
    }
}