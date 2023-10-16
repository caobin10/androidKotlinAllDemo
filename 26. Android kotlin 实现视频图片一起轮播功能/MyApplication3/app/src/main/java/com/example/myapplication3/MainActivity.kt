package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.banner.ImageAndVideoBannerActivity
import com.example.myapplication3.banner.TouTiaoBannerActivity
import com.example.myapplication3.recyclerviewbanner.RvImageAndVideoBannerActivity
import com.example.myapplication3.viewpagerbanner.VpImageAndVideoBannerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1、用ViewPager实现视频图片混合轮播功能(只能动手滑动)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, VpImageAndVideoBannerActivity::class.java)
            startActivity(intent)
        }
        //2、用RecyclerView实现视频图片混合轮播功能(只能动手滑动)
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, RvImageAndVideoBannerActivity::class.java)
            startActivity(intent)
        }
        //3、用Banner实现视频图片混合轮播功能(无限轮播，自动轮播，动手滑动)
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageAndVideoBannerActivity::class.java)
            startActivity(intent)
        }
        //4、用Banner实现滚动头条(垂直)功能(无限轮播，自动轮播，动手滑动)
        button4.setOnClickListener {
            val intent = Intent(this@MainActivity, TouTiaoBannerActivity::class.java)
            startActivity(intent)
        }
    }
}