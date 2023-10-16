package com.example.myapplication3.banner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.R
import com.example.myapplication3.banner.rvadapter.RvAdapter2
import kotlinx.android.synthetic.main.activity_rv.*

class TouTiaoBannerActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        RvAdapter2().apply {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)
        init()
    }

    private fun init() {

        val itemList: MutableList<String> = ArrayList()
        for (i in 0 until 9) {
            itemList.add("PageTransformer")
        }

        val layoutManager = LinearLayoutManager(this@TouTiaoBannerActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
    }
}