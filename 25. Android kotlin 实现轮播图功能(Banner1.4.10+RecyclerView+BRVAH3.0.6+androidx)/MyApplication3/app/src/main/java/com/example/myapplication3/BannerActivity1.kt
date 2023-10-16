package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_rv_banner.*

class BannerActivity1 : AppCompatActivity() {

    private val mAdapter by lazy {
        RvAdapter().apply {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_banner)
        init()
    }

    private fun init() {
        val itemList: MutableList<String> = ArrayList()
        for (i in 0..9) {
            itemList.add("style")
        }

        val layoutManager = LinearLayoutManager(this@BannerActivity1)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
    }
}