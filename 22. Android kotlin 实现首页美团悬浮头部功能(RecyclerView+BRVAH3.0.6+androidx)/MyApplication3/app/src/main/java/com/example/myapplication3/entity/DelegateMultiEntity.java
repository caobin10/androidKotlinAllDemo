package com.example.myapplication3.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DelegateMultiEntity implements MultiItemEntity {

    private String name; //正常item的内容

    public static final int TEXT1 = 0;//头部内容,会隐藏的部分
    public static final int TEXT2 = 1;//头部内容,一直显示的部分
    public static final int ITEM_TEXT3 = 2;//正常item

    private int itemType;

    public DelegateMultiEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DelegateMultiEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
