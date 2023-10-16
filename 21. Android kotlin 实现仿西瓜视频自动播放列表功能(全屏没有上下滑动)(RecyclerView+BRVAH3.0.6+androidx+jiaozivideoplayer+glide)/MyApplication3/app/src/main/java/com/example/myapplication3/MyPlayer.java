package com.example.myapplication3;

public class MyPlayer {

    private String videoUrl;
    private String coverImageUrl;

    public MyPlayer(String videoUrl, String coverImageUrl) {
        this.videoUrl = videoUrl;
        this.coverImageUrl = coverImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
