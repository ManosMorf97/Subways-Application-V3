package com.example.subway_application.domain_model_test;

import com.example.subway_application.domain_model.Bus;
import com.example.subway_application.domain_model.Route;

import org.junit.Assert;
import org.junit.Test;

public class TestBus {
    public Bus bus=new Bus("XJ","A8");
    @Test
    public void checkBus(){
        bus.addRoute(new Route("A8-1"));
        Assert.assertTrue(bus.getRoutes().size()==1&&bus.getRoutes().get(0).getName().equals("A8-1"));
    }
}
