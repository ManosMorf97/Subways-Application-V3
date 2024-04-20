package com.example.subway_application.domain_model.DAO;

import com.example.subway_application.domain_model.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationDAOAndroid {
    private static HashMap<String, Station> stations=new HashMap<>();

    public static void AddStation(String name, Station station) {
        stations.put(name,station);
    }

    public static void clear(){
        stations.clear();
    }


    public static HashMap<String, Station> getStations() {
        return stations;
    }


    public static ArrayList<Station> ListStations() {
        ArrayList<Station> stationsArrayList=new ArrayList<>();
        //https://beginnersbook.com/2013/12/how-to-loop-hashmap-in-java/
        for(Map.Entry me:stations.entrySet())
            stationsArrayList.add((Station) me.getValue());
        return stationsArrayList;
    }
}
