package com.example.subway_application.domain_model_test;
import com.example.subway_application.domain_model.Bus;
import com.example.subway_application.domain_model.DAO.BusDAOAndroid;
import com.example.subway_application.domain_model.DAO.StationDAOAndroid;
import com.example.subway_application.domain_model.Person;
import com.example.subway_application.domain_model.Route;
import com.example.subway_application.domain_model.Station;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.example.subway_application.domain_model.Person.*;

public class TestPerson {
    @BeforeClass
    public static void init(){
        String [] station_names={"A","B","C","D","A2","B2","A3","B3"};
        double [] longitudes={-0.00000001,1,2,3,4,5,6,7,8};
        double [] latitudes= {-0.00000001,1,2,3,4,5,6,7,8};
        for(int i=0; i<8; i++)
            StationDAOAndroid.AddStation(station_names[i],new Station(station_names[i],latitudes[i],longitudes[i]));
        Route[] routes=new Route[6];
        String [] name_routes={"A-D","D-A","A2-A3","A3-A2","D-B3","B3-D"};
        for(int i=0; i<6; i++)
            routes[i]=new Route(name_routes[i]);
        String [][] routeStations={{"A","B","C","D"},{"D","C","B","A"},{"A2","C","B2","A3"},{"A3","B2","C","A2"},
                {"D","A3","B3"},{"B3","A3","D"}};
        for(int i=0; i<6; i++)
            for(int j=0; j<routeStations[i].length; j++)
                Person.connect(routes[i],StationDAOAndroid.getStations().get(routeStations[i][j]));
        String [] bus_names={"Line1","Line2","Line3"};
        String [] bus_codes={"0","1","2"};
        int j=0;
        for(int i=0; i<3; i++){
            BusDAOAndroid.AddBus(bus_names[i],new Bus(bus_codes[i],bus_names[i]));
            for(int k=0; k<2; k++) {
                Person.connect(BusDAOAndroid.getBuses().get(bus_names[i]), routes[j]);
                j++;
            }
        }
    }
    @Test
    public void checkConnection(){
        Station station=StationDAOAndroid.getStations().get("A");
        Route route=station.getRoutes().get(0);
        Station station2=route.getStations().get(0);
        Assert.assertTrue(station==station2);
        Bus bus=BusDAOAndroid.getBuses().get("Line1");
        route=bus.getRoutes().get(0);
        Bus bus2=route.getBus();
        Assert.assertTrue(bus==bus2);
    }
    @Test
    public void testGetStations(){
        ArrayList<Station> stations=Person.getStations("Line1");
        Assert.assertEquals(stations.size(),4);
        String [] station_names={"A","B","C","D"};
        for(int i=0; i<4; i++)
            Assert.assertTrue(StationDAOAndroid.getStations().get(station_names[i])==stations.get(i));
    }
    @Test
    public void testGetBuses(){
        ArrayList<Bus> buses=Person.getBuses("C");
        Assert.assertEquals(buses.size(),2);
        String [] bus_names={"Line1","Line2"};
        for(int i=0; i<2; i++)
            Assert.assertTrue(BusDAOAndroid.getBuses().get(bus_names[i])==buses.get(i));
    }
    @Test
    public void testNearestStations(){
        ArrayList<Station> stations=getNearestStations(1,1);
        Assert.assertEquals(stations.size(),3);
        String [] station_names={"B","C","A"};
        for(int i=0; i<3; i++) {
            Assert.assertTrue(StationDAOAndroid.getStations().get(station_names[i]) == stations.get(i));
        }

    }
    @Test
    public void testCheckRoutes(){
        Person.checkRoutes("A","D");
        String message="Go to The bus: " + "Line1"+ " with route: " +"A-D"+
                " embark at station: " +"A"+ " and disembark at station: " + "D" + "\n";
        Assert.assertTrue(Person.getMessage().equals(message));

        Person.checkRoutes("B","A3");
        message="Go to The bus: " + "Line1"+ " with route: " +"A-D"+
                " embark at station: " +"B"+ " and disembark at station: " + "C" + "\n"+
                "Go to The bus: " + "Line2"+ " with route: " +"A2-A3"+
                " embark at station: " +"C"+ " and disembark at station: " + "A3" + "\n";
        Assert.assertTrue(Person.getMessage().equals(message));
        Person.checkRoutes("A","B3");
        message="Go to The bus: " + "Line1"+ " with route: " +"A-D"+
                " embark at station: " +"A"+ " and disembark at station: " + "D" + "\n"+
                "Go to The bus: " + "Line3"+ " with route: " +"D-B3"+
                " embark at station: " +"D"+ " and disembark at station: " + "B3" + "\n";
        Assert.assertTrue(Person.getMessage().equals(message));

        Person.checkRoutes("A2","A3");
        message= "Go to The bus: " + "Line2"+ " with route: " +"A2-A3"+
                " embark at station: " +"A2"+ " and disembark at station: " + "A3" + "\n";
        Assert.assertTrue(Person.getMessage().equals(message));

        Person.checkRoutes("A","A2");
        message="Go to The bus: " + "Line1"+ " with route: " +"A-D"+
                " embark at station: " +"A"+ " and disembark at station: " + "C" + "\n"+
                "Go to The bus: " + "Line2"+ " with route: " +"A3-A2"+
                " embark at station: " +"C"+ " and disembark at station: " + "A2" + "\n";
        Assert.assertTrue(Person.getMessage().equals(message));

        Station[] stations= {new Station("Ghost",999,999),
                new Station("Ghost2",1000,1000)};

        StationDAOAndroid.AddStation("Ghost",stations[0]);
        StationDAOAndroid.AddStation("Ghost2",stations[1]);
        Route[] routes={new Route("Ghost-Ghost2"),new Route("Ghost2-Ghost")};
        Bus bus=new Bus("000","UNKNOWN");
        for (Station station:stations){
            for(Route route:routes){
                Person.connect(route,station);
            }
        }
        for(Route route:routes)
            Person.connect(bus,route);
        Person.checkRoutes("A","Ghost");
        message="There are no buses";
        Assert.assertTrue(Person.getMessage().equals(message));



    }

}
