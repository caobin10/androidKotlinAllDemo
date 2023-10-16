package com.example.myapplication3.data;

import java.util.List;

public class FirstClassItem {
    private int id;
    private String name;
    private List<SecondClassItem> secondList;

    public FirstClassItem() {

    }

    public FirstClassItem(int id, String name, List<SecondClassItem> secondList) {
        this.id = id;
        this.name = name;
        this.secondList = secondList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondClassItem> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<SecondClassItem> secondList) {
        this.secondList = secondList;
    }

}
