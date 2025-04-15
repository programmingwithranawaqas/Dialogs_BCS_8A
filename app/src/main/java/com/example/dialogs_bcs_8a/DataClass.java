package com.example.dialogs_bcs_8a;

import android.app.Application;

import java.util.ArrayList;

public class DataClass extends Application {
    public static ArrayList<Passenger> passengers;

    @Override
    public void onCreate() {
        super.onCreate();
        passengers = new ArrayList<>();
    }
}
