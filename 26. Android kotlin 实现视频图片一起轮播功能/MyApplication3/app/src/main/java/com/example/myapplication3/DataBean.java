package com.example.myapplication3;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataBean implements MultiItemEntity {
    public Integer imageRes;
    public String imageUrl;
    public String videoUrl;
    public String title;
    public int viewType;

    public DataBean(String imageUrl, String videoUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public Integer getImageRes() {
        return imageRes;
    }

    public void setImageRes(Integer imageRes) {
        this.imageRes = imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getItemType() {
        return viewType;
    }
}
