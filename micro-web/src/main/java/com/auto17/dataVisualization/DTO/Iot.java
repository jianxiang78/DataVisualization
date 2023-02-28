package com.auto17.dataVisualization.DTO;

import com.toshiba.mwcloud.gs.RowKey;

import java.util.Date;

public class Iot {

    @RowKey Date timestamp;
    String name;
    int value;

    public Iot(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
