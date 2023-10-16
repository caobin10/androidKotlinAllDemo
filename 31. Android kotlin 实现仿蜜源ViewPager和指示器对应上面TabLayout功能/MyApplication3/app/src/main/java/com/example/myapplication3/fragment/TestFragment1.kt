package com.example.myapplication3.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.R
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.testfragment1.recyclerView

class TestFragment1 : BaseFragment(){

    private val mAdapter by lazy {
        RvAdapter().apply {
//            setOnItemLongClickListener(activity)
        }
    }

    override val layoutId: Int = R.layout.testfragment1
    override fun init(view: View?) {
        val itemList: MutableList<String> = ArrayList()
        for (i in 0..2) {
            itemList.add("position $i")
        }
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
    }
}