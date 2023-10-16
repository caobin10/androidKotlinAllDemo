package com.example.myapplication3.activity.node

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //分组列表
        btn_group_list.setOnClickListener {
            val intent = Intent(this@MainActivity, MyGroupActivity::class.java)
            startActivity(intent)
        }

        //吸顶列表
        btn_sticky_list.setOnClickListener {
            val intent = Intent(this@MainActivity, StickyActivity::class.java)
            startActivity(intent)
        }

        //列表下的子菜单类似于ListView
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, MyListActivity::class.java)
            startActivity(intent)
        }

        //列表下的子菜单类似于GridView
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, MyGridActivity::class.java)
            startActivity(intent)
        }
    }
}