package com.example.subway_application.domain_model;

import java.util.ArrayList;

public class Station  extends HelpComparator{
    private final double longitude;
    private final double latitude;
    public Station(String name,double latitude,double longitude){
        super(name);
        routes=new ArrayList<>();
        this.longitude=longitude;
        this.latitude=latitude;
    }
    public double getLongitude(){
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String Info(){
        return "The station's name is "+super.getName()+" the station's latitude is "+getLatitude()+
                " the station's longitude is "+getLongitude();
    }
}