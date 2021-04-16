package com.example.assignment5.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "Room3")

public class Room3 {
    @PrimaryKey
    public int id;
    public String data;

    public Room3(int id, String data) {
        this.id = id;
        this.data = data;
    }
}