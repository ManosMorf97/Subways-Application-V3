package com.example.subway_application.domain_model_test;
import com.example.subway_application.domain_model.Route;
import com.example.subway_application.domain_model.Station;

import org.junit.Assert;
import org.junit.Test;
public class TestRoute {
    Route route=new Route("A-F");
    @Test
    public void checkStation(){
        route.addStation(new Station("A",0,0));
        Station station=route.getStations().get(0);
        Assert.assertTrue(route.getStations().size()==1&&station.getName().equals("A")&&station.getLongitude()==0
        &&station.getLatitude()==0);
    }
}
