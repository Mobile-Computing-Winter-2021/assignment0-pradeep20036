package com.example.assignment5;

public class ProfileModel {
    String wifi_name;
    int rssi;

    public ProfileModel(String wifi_name,int rssi) {
        this.wifi_name=wifi_name;
        this.rssi=rssi;
    }

    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
