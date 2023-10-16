package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //顶部导航栏与左右滑动页面
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, FragmentActivity::class.java)
            startActivity(intent)
        }

        //顶部导航栏、左右滑动页面和滚动Tab悬停
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, FragmentAndTabScrollActivity::class.java)
            startActivity(intent)
        }

        //1、TabLayout与RecyclerView绑定和锚点定位，2、滚动Tab悬停
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, AnchorAndTabScrollActivity::class.java)
            startActivity(intent)
        }
    }
}