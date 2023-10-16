package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1、网格(拖拽左右上下)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, GridLayoutActivity::class.java)
            startActivity(intent)
        }
        //2、列表(拖拽上下，侧滑删除左右)
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, LinearLayoutActivity::class.java)
            startActivity(intent)
        }
        //3、列表HORIZONTAL(拖拽左右)
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, HORIZONTALActivity::class.java)
            startActivity(intent)
        }
    }
}