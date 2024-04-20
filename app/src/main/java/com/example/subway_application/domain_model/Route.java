package com.example.subway_application.domain_model;

import java.util.ArrayList;

public class Route {
    private final String name;
    private Bus bus;
    private ArrayList<Station> stations=new ArrayList<>();
    public Route(String name){
        this.name=name;
    }
    public void setBus(Bus bus){
        this.bus=bus;
    }
    public Bus getBus(){
        return bus;
    }
    public ArrayList<Station> getStations(){
        return stations;
    }
    public void addStation(Station station){
        stations.add(station);
    }
    public String getName(){
        return name;
    }

}
