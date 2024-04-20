package com.example.subway_application.domain_model.DAO;

import com.example.subway_application.domain_model.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusDAOAndroid {
    private static HashMap<String,Bus> buses=new HashMap<>();

    public static void AddBus(String name, Bus bus) {
        buses.put(name,bus);
    }


    public static HashMap<String, Bus> getBuses() {
        return buses;
    }


    public static ArrayList<Bus> ListBuses() {
        ArrayList<Bus> busArrayList=new ArrayList<>();
        //https://beginnersbook.com/2013/12/how-to-loop-hashmap-in-java/
        for(Map.Entry me:buses.entrySet())
            busArrayList.add((Bus)me.getValue());
        return busArrayList;
    }
    public static void clear(){
        buses.clear();
    }
}
