package com.example.subway_application;

import android.content.Intent;
import android.os.Bundle;

import com.example.subway_application.domain_model.Bus;
import com.example.subway_application.domain_model.Person;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowBusesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_buses);
        TextView textView=findViewById(R.id.textView3);
        ListView listView=findViewById(R.id.buses);
        textView.setText("The subways of station "+ Person.getChosen_station()+" are");
        ArrayList<Bus> buses=Person.getBuses(Person.getChosen_station().getName());
        ArrayAdapter<Bus> arrayAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                ,buses);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l)->{
            Toast toast=Toast.makeText(getApplicationContext(),
                    buses.get(i).Info(),Toast.LENGTH_LONG);
            toast.show();
        });


    }
}