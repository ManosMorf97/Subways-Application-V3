package com.example.subway_application.domain_model;

import java.util.ArrayList;

public class Bus extends HelpComparator{
    private final String code;
    public Bus(String code,String name){
        super(name);
        routes=new ArrayList<>(2);
        this.code=code;
    }
    public String getCode(){
        return  code;
    }

    public String Info(){
        return "Subway's name: "+getName();
    }
}
