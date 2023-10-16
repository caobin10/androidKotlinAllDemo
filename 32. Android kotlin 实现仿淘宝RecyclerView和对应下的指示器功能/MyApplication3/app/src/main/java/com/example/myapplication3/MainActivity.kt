package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.indicator
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        RvAdapter().apply {
//            setOnItemLongClickListener(activity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        val itemList: MutableList<String> = ArrayList()
        for (i in 0..12) {
            itemList.add("测试$i")
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
        indicator.setWithRecyclerView(recyclerView,LinearLayoutManager.HORIZONTAL)
    }
}