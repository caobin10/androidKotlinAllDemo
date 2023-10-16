package com.example.myapplication3.banner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.JzvdStd
import com.example.myapplication3.R
import com.example.myapplication3.banner.rvadapter.RvAdapter1
import kotlinx.android.synthetic.main.activity_rv.*

class ImageAndVideoBannerActivity :AppCompatActivity(){

    private val mAdapter by lazy {
        RvAdapter1().apply {
        }
    }
    private var player: JzvdStd? = null

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

        val layoutManager = LinearLayoutManager(this@ImageAndVideoBannerActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
    }

    override fun onPause() {
        super.onPause()
        JzvdStd.goOnPlayOnPause()
    }

    override fun onDestroy() { //破坏
        super.onDestroy()
        JzvdStd.releaseAllVideos()
    }
}