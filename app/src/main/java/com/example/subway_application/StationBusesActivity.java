package com.example.subway_application;

import android.content.Intent;
import android.os.Bundle;


import com.example.subway_application.domain_model.DAO.StationDAOAndroid;
import com.example.subway_application.domain_model.HelpComparator;
import com.example.subway_application.domain_model.Person;
import com.example.subway_application.domain_model.Station;



import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class StationBusesActivity extends KeyboardActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_buses);
        EditText station_name=findViewById(R.id.station_name);
        ListView listView=findViewById(R.id.stations);
        ArrayList<Station> stationsList= StationDAOAndroid.ListStations();
        Button show_buses=findViewById(R.id.button);
        Collections.sort(stationsList,new HelpComparator());
        ArrayAdapter<Station> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                ,stationsList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> station_name.setText(stationsList.get(i).getName()));
        show_buses.setOnClickListener(view -> {
            String station_name_=station_name.getText().toString();
            Toast toast;
            if(station_name_.equals("")) {
                toast = makeText("You forgot to add station");
                toast.show();
                return;
            }

            Station chosen_station=StationDAOAndroid.getStations().get(station_name_.toUpperCase());
            if(chosen_station==null){
                 toast=Toast.makeText(getApplicationContext(),
                        "The station "+station_name.getText().toString()+" is not on the list,See the list",Toast.LENGTH_LONG);
                toast.show();
                station_name.getText().clear();
            }else{
                Person.setChosen_station(chosen_station);
                Intent activityChangeIntent=new Intent(StationBusesActivity.this,ShowBusesActivity.class);
                startActivity(activityChangeIntent);
            }
        });
    }

    }
