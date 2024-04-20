package com.example.subway_application;

import android.content.Intent;
import android.os.Bundle;

import com.example.subway_application.domain_model.Person;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class UserRouteActivity extends AppCompatActivity {


    //@SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_route);

        TextView textView=findViewById(R.id.textView3);
        ListView listView=findViewById(R.id.message);
        if(Person.getMessage().equals("There are no buses")){
            textView.setText("Sorry this route cannot be succeed with these buses");
            View view = findViewById(R.id.message);
            view.setVisibility(View.GONE);
        }else{
            textView.setText("Your route is below");
            ArrayList<String> message=new ArrayList<>(Arrays.asList(Person.getMessage().split("\\r?\\n")));
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item
                    ,message);
            listView.setAdapter(arrayAdapter);
        }

    }
}