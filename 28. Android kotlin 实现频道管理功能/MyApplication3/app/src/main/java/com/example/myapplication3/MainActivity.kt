package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.channel.ChannelActivity
import com.example.myapplication3.past.NewChannelActivity
import com.example.myapplication3.past.newacticity.MainActivity2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, ChannelActivity::class.java)
            startActivity(intent)
        }
//        button3.setOnClickListener {
//            val intent = Intent(this@MainActivity, NewChannelActivity::class.java)
//            startActivity(intent)
//        }
//        button4.setOnClickListener {
//            val intent = Intent(this@MainActivity, MainActivity2::class.java)
//            startActivity(intent)
//        }
    }
}