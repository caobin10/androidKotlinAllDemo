package com.example.myapplication3.bean;

import com.example.myapplication3.fragment.BaseFragment;

public class PageInfo {

//    public String title;
    public BaseFragment fragment;

//    public PageInfo(String title, BaseFragment fragment) {
//        this.title = title;
//        this.fragment = fragment;
//    }

    public PageInfo(BaseFragment fragment) {
        this.fragment = fragment;
    }
}
