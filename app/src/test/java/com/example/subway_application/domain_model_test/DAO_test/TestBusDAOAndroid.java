package com.example.subway_application.domain_model_test.DAO_test;

import com.example.subway_application.domain_model.Bus;
import com.example.subway_application.domain_model.DAO.BusDAOAndroid;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class TestBusDAOAndroid {
    private static Bus bus0;
    private static Bus bus1;
    private static HashMap<String,Bus> buses;
    @BeforeClass
    public static void init(){
        bus0=new Bus("XJ","Line1");
        bus1=new Bus("XJ1","Line2");
        BusDAOAndroid.AddBus("Line1",bus0);
        BusDAOAndroid.AddBus("Line2",bus1);
        buses=BusDAOAndroid.getBuses();
    }
    @Test
    public void TestgetBuses(){
        Assert.assertEquals(2, buses.size());
        Bus bus0=buses.get("Line1");
        Bus bus1=buses.get("Line2");
        assert bus0 != null;
        Assert.assertTrue(bus0.getCode().equals("XJ")&&bus0.getName().equals("Line1"));
        assert bus1 != null;
        Assert.assertTrue(bus1.getCode().equals("XJ1")&&bus1.getName().equals("Line2"));
    }
    @Test
    public void TestListBuses(){
        ArrayList<Bus> busesList=BusDAOAndroid.ListBuses();
        Assert.assertEquals(2, busesList.size());
        Assert.assertTrue(busesList.get(0)==bus0&&busesList.get(1)==bus1);

    }
    @Test
    public void Testclear(){
        BusDAOAndroid.clear();
        Assert.assertEquals(0, BusDAOAndroid.getBuses().size());
    }
}
