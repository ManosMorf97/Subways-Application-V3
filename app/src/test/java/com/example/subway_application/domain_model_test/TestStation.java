package com.example.subway_application.domain_model_test;
import com.example.subway_application.domain_model.Route;
import com.example.subway_application.domain_model.Station;

import org.junit.Assert;
import org.junit.Test;



public class TestStation {
    Station station=new Station("A",0,0);

    @Test
    public void checkRoutes(){
        station.addRoute(new Route("A-F"));
        Assert.assertTrue(station.getRoutes().get(0).getName().equals("A-F")&&station.getRoutes().size()==1);
    }
}
