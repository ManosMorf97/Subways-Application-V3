package com.example.subway_application;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.subway_application.domain_model.DAO.StationDAOAndroid;
import com.example.subway_application.domain_model.HelpComparator;
import com.example.subway_application.domain_model.Person;
import com.example.subway_application.domain_model.Station;

import java.util.ArrayList;
import java.util.Collections;

public class RouteActivity extends KeyboardActivity {
    private EditText [] editTexts=new EditText[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        editTexts[0]=findViewById(R.id.begining_station);
        editTexts[1]=findViewById(R.id.ending_station);
        ListView listView=findViewById(R.id.stations);
        ArrayList<Station> stationsList= StationDAOAndroid.ListStations();
        Button makeRoute=findViewById(R.id.button);
        Collections.sort(stationsList,new HelpComparator());
        ArrayAdapter<Station> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                ,stationsList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) ->
        {
            for(EditText editText:editTexts){
                if(editText.getText().toString().equals("")){
                    editText.setText(stationsList.get(i).getName());
                    break;
                }
            }
        });
        makeRoute.setOnClickListener(view -> {
            boolean no_such_station=false;
            String station_name1=editTexts[0].getText().toString().toUpperCase().trim();
            String station_name2=editTexts[1].getText().toString().toUpperCase().trim();
            Toast toast;
            if(station_name1.equals("")&&station_name2.equals("")){
                toast=makeText("You did not add the fields");
                toast.show();
                return;
            }
            if(StationsAreSAme(station_name1,station_name2))
                return;
            int [] colors={Color.WHITE,Color.BLACK};
            int i=0;
            for(EditText editText:editTexts){
                String station_name=editText.getText().toString().toUpperCase().trim();
                if(StationDAOAndroid.getStations().get(station_name)==null){
                    if(station_name.equals(("")))
                        toast=makeText("You forgot to add a field");
                    else
                        toast=makeText("The station "+station_name+" is not on the list,See the list");
                    View view2=toast.getView();
                    view2.getBackground().setColorFilter(colors[i], PorterDuff.Mode.SRC_IN);
                    TextView v = toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(colors[(i+1)%2]);
                    toast.show();
                    editText.getText().clear();
                    no_such_station=true;
                    i++;
                }

            }
            if(!no_such_station) {
                Person.checkRoutes(station_name1,station_name2);
                Intent intent = new Intent(RouteActivity.this, UserRouteActivity.class);
                startActivity(intent);
            }
        });


    }
    private  boolean StationsAreSAme(String station_name1,String station_name2){
        if(station_name1.equals(station_name2)){
            Toast toast=Toast.makeText(getApplicationContext(),
                    "Embarking and Disembarking Stations are the same",Toast.LENGTH_LONG);
            toast.show();
            for(EditText editText:editTexts)
                editText.getText().clear();
            return true;
        }
        return false;
    }


}