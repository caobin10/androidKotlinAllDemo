package com.example.myapplication3.bean;

/**
 * 列数据的bean
 */
public class MenuData{
    public int id;
    public String name;
    public int flag;

    public MenuData(int id, String name, int flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    public MenuData() {
    }
}
