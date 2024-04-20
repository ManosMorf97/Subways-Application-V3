package com.example.subway_application;


import android.content.Intent;
import android.os.Bundle;

import com.example.subway_application.domain_model.Bus;
import com.example.subway_application.domain_model.DAO.BusDAOAndroid;
import com.example.subway_application.domain_model.HelpComparator;
import com.example.subway_application.domain_model.Person;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class BusStationsActivity extends KeyboardActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stations);

        EditText bus_name=findViewById(R.id.bus_name);
        ListView listView=findViewById(R.id.buses);
        ArrayList<Bus> busesList= BusDAOAndroid.ListBuses();
        Button show_stations=findViewById(R.id.button);
        Collections.sort(busesList,new HelpComparator());
        ArrayAdapter<Bus> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                ,busesList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> bus_name.setText(busesList.get(i).getName()));
        show_stations.setOnClickListener(view -> {
            String bus_name_=bus_name.getText().toString();
            Toast toast;
            if(bus_name_.equals("")) {
                toast = makeText("You forgot to add subway");
                toast.show();
                return;
            }
            Bus chosen_bus=BusDAOAndroid.getBuses().get(bus_name_);
            if(chosen_bus==null){
                 toast=Toast.makeText(getApplicationContext(),
                        "The bus "+bus_name.getText().toString()+" is not on the list,See the list",Toast.LENGTH_LONG);
                toast.show();
                bus_name.getText().clear();
            }else{
                Person.setChosen_bus(chosen_bus);
                Intent activityChangeIntent=new Intent(BusStationsActivity.this,ShowStationsActivity.class);
                startActivity(activityChangeIntent);
            }
        });
    }
}