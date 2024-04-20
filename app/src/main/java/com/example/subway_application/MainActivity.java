package com.example.subway_application;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import com.example.subway_application.domain_model.Person;

public class MainActivity extends AppCompatActivity {
    boolean data=false;
    private static final int INITIAL_REQUEST = 1337;
    private static final int CONTACTS_REQUEST = INITIAL_REQUEST + 2;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;
    private static final int CAMERA_REQUEST = INITIAL_REQUEST + 1;
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] CAMERA_PERMS = {
            Manifest.permission.CAMERA
    };
    private static final String[] CONTACTS_PERMS = {
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        requestPermissions(CAMERA_PERMS, CAMERA_REQUEST);
        requestPermissions(CONTACTS_PERMS, CONTACTS_REQUEST);
        requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
        if(!data){
            Person.initialize();
            data=true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button[] choices={findViewById(R.id.see_bus_stations),findViewById(R.id.see_station_buses),findViewById(R.id.see_nearest_stations),findViewById(R.id.make_route)};
        Class [] classes={BusStationsActivity.class,StationBusesActivity.class,NearestStationsActivity.class,RouteActivity.class};
        for(int i=0; i<choices.length; i++){
            int finalI = i;
            choices[i].setOnClickListener(view -> {
                Intent activityChangeIntent=new Intent(MainActivity.this,classes[finalI]);
                startActivity(activityChangeIntent);
            });
        }

    }
}