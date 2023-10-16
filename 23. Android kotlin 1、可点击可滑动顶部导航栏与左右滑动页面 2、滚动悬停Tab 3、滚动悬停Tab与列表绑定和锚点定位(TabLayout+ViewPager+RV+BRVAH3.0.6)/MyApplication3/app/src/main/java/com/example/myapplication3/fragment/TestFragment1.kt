package com.example.myapplication3.fragment

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.R
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.testfragment1.*

class TestFragment1 : BaseFragment(){

    private val mAdapter2 by lazy {
        RvAdapter().apply {
//            setOnItemLongClickListener(activity)
        }
    }

    override val layoutId: Int = R.layout.testfragment1
    override fun init(view: View?) {
        val itemList: MutableList<String> = ArrayList()
        for (i in 0..19) {
            itemList.add("菜单$i")
        }
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter2
        mAdapter2.setList(itemList)
        mAdapter2.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(activity,"$position",Toast.LENGTH_SHORT).show()
        }
    }
}
