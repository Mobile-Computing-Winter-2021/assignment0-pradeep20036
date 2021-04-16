package com.example.assignment5.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Conversion {
    @TypeConverter
    public static ArrayList<Integer> convertToArrayList(String str) {
        Type tp;
        tp= new TypeToken<ArrayList<Integer>>() {}.getType();
        return new Gson().fromJson(str, tp);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> arrayList) {
        Gson obj;
        obj=new Gson();
        String finalvalue;
        finalvalue= obj.toJson(arrayList);
        return finalvalue;
    }
}
