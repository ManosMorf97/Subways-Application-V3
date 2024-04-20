package com.example.subway_application.domain_model_test.DAO_test;

import com.example.subway_application.domain_model.DAO.StationDAOAndroid;
import com.example.subway_application.domain_model.Station;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class TestStationDAOAndroid {
    private static Station station0;
    private static Station station1;
    private static HashMap<String,Station> stations;
    @BeforeClass
    public static void init(){
        station0=new Station("A",0,0);
        station1=new Station("B",1,1);
        StationDAOAndroid.AddStation("A",station0);
        StationDAOAndroid.AddStation("B",station1);
        stations=StationDAOAndroid.getStations();
    }
    @Test
    public void TestgetStations(){
        Assert.assertEquals(2, stations.size());
        Station stationA=stations.get("A");
        Station stationB=stations.get("B");
        assert stationA != null;
        Assert.assertTrue(stationA.getName().equals("A")&&stationA.getLongitude()==0&&stationA.getLatitude()==0);
        assert stationB != null;
        Assert.assertTrue(stationB.getName().equals("B")&&stationB.getLongitude()==1&&stationB.getLatitude()==1);
    }
    @Test
    public void TestListStations(){
        ArrayList<Station> stationsList=StationDAOAndroid.ListStations();
        Assert.assertEquals(2, stationsList.size());
        Assert.assertTrue(stationsList.get(0)==station0&&stationsList.get(1)==station1);

    }
    @Test
        public void Testclear(){
            StationDAOAndroid.clear();
        Assert.assertEquals(0, StationDAOAndroid.getStations().size());
        }
}
