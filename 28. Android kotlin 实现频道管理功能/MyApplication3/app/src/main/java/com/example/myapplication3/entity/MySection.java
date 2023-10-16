package com.example.myapplication3.entity;

import com.chad.library.adapter.base.entity.JSectionEntity;

public class MySection extends JSectionEntity {

    private boolean isHeader;
    private int headerNum;
    private Object object;

    private boolean isRecommend;

    public MySection(boolean isHeader, int headerNum, Object object,boolean isRecommend) {
        this.isHeader = isHeader;
        this.headerNum = headerNum;
        this.object = object;
        this.isRecommend = isRecommend;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }

    public int getHeaderNum() {
        return headerNum;
    }

    public boolean isRecommend() {
        return isRecommend;
    }

    public void setRecommend(boolean recommend) {
        isRecommend = recommend;
    }
}
