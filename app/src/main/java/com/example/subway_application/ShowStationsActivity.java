package com.example.subway_application;

import android.content.Intent;
import android.os.Bundle;


import com.example.subway_application.domain_model.Person;
import com.example.subway_application.domain_model.Station;


import androidx.appcompat.app.AppCompatActivity;



import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowStationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stations);
        TextView textView=findViewById(R.id.textView3);
        ListView listView=findViewById(R.id.stations);
        textView.setText("The Stations of subway "+ Person.getChosen_bus()+" are");
        ArrayList<Station> stations=Person.getStations(Person.getChosen_bus().getName());
        ArrayAdapter<Station> arrayAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                ,stations);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l)->{
            Toast toast=Toast.makeText(getApplicationContext(),
                    stations.get(i).Info(),Toast.LENGTH_LONG);
            toast.show();
        });
    }
}