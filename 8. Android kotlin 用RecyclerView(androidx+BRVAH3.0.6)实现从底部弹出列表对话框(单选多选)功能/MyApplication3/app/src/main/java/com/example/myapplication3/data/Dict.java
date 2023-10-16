package com.example.myapplication3.data;

public class Dict {
    private String dataName;
    private String dataValue;

    public Dict(String dataName, String dataValue) {
        this.dataName = dataName;
        this.dataValue = dataValue;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
