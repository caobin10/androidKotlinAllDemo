package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //轮播图 - 样式
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, BannerActivity1::class.java)
            startActivity(intent)
        }

        //轮播图 - 动画
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, BannerActivity2::class.java)
            startActivity(intent)
        }
    }
}