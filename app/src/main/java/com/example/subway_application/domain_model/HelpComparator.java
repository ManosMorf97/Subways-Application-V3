package com.example.subway_application.domain_model;

import java.util.ArrayList;
import java.util.Comparator;

public class HelpComparator implements Comparator<HelpComparator> {
    private  String name;
    protected ArrayList<Route> routes;

    public HelpComparator(String name) {
        this.name = name;
    }
    public HelpComparator(){}
    public String getName() {
        return name;
    }
    public void addRoute(Route route){
        routes.add(route);
    }
    public ArrayList<Route> getRoutes(){
        return routes;
    }
    public String toString(){
        return name;
    }


    @Override
    public int compare(HelpComparator helpComparator, HelpComparator helpComparator2) {
        return helpComparator.getName().compareTo(helpComparator2.getName());
    }
}
