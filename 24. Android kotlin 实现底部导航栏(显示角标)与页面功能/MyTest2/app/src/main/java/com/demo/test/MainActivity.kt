package com.demo.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //非左右滑动页面与底部导航栏
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, FragmentActivity::class.java)
            startActivity(intent)
        }

        //左右滑动页面与底部导航栏
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, FragmentAndPageSlideActivity::class.java)
            startActivity(intent)
        }
    }
}