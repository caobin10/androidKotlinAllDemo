package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1、列表
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, RvActivity::class.java)
            startActivity(intent)
        }
        //2、从底部弹出列表对话框
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, DialogActivity::class.java)
            startActivity(intent)
        }
    }
}