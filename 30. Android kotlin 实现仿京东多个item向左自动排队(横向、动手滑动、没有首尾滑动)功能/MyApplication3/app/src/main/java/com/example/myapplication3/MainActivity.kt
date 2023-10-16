package com.example.myapplication3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        RvAdapter()
    }

    //用于recyclerView滑动到指定的位置
    private var canScroll = false
    private var scrollToPosition = 0

    private var mLayoutManager: LinearLayoutManager? = null
    private var mSnapHelper: PagerSnapHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
    }

    private fun initRv() {
        val itemList: MutableList<String> = ArrayList()
        for (i in 0 until 10) {
            itemList.add("position $i")
        }
        mLayoutManager = LinearLayoutManager(this@MainActivity)
//        mLayoutManager = object :LinearLayoutManager(this){
//            override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
//                super.smoothScrollToPosition(recyclerView, state, position)
//            }
//        }
        mLayoutManager?.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = mLayoutManager
//        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
//        mSnapHelper = PagerSnapHelper() //类似于ViewPager，用LinearSnapHelper也可以
//        mSnapHelper?.attachToRecyclerView(recyclerView)
        val ses = Executors.newSingleThreadScheduledExecutor()
        ses.scheduleAtFixedRate(Runnable {

//            recyclerView.smoothScrollToPosition(mLayoutManager!!.findLastVisibleItemPosition())

            // 第一个可见的view的位置
            var firstItem = mLayoutManager!!.findFirstVisibleItemPosition() + 1
            // 最后一个可见的view的位置
            val lastItem = mLayoutManager!!.findLastVisibleItemPosition()

            val firstCompletelyItem = mLayoutManager!!.findFirstCompletelyVisibleItemPosition()
            val lastCompletelyItem = mLayoutManager!!.findLastCompletelyVisibleItemPosition()

            Log.i("Tag", "firstCompletelyItem = $firstCompletelyItem")
            Log.i("Tag", "lastCompletelyItem = $lastCompletelyItem")

            Log.i("Tag", "firstItem = $firstItem")
            Log.i("Tag", "lastItem = $lastItem")

            //***关闭
            if (itemList.size -1 == lastCompletelyItem) {
                firstItem = 0
                moveToPosition(mLayoutManager!!, recyclerView, firstItem)
            } else {
                moveToPosition(mLayoutManager!!, recyclerView, firstItem)
            }
            //***关闭

        }, 2500, 2500, TimeUnit.MILLISECONDS)
    }

    private fun moveToPosition(
        layoutManager: LinearLayoutManager,
        mRecyclerView: RecyclerView?,
        position: Int
    ) {
        // 第一个可见的view的位置
        val firstItem = layoutManager.findFirstVisibleItemPosition()
        // 最后一个可见的view的位置
        val lastItem = layoutManager.findLastVisibleItemPosition()
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView!!.smoothScrollToPosition(position) //向下滑到下面
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            val left = mRecyclerView!!.layoutManager!!.findViewByPosition(position)!!.left
            mRecyclerView.smoothScrollBy(left, 0) //向左滑到后面
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView!!.smoothScrollToPosition(position)
            scrollToPosition = position
            canScroll = true
        }
    }
}