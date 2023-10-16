package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<String>()

    private val mAdapter by lazy {
        RvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..100) {
            list.add("这里是第 $i 行")
        }

        //添加RecyclerView的样式和数据更新方法
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        mAdapter.setList(list)

        //规定SearchView的侦听事件
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyWord: String): Boolean {
                //当提交了输入时的操作
                return false
            }

            override fun onQueryTextChange(keyWord: String): Boolean {
                // 当修改了输入时的操作，根据关键字过滤列表，让Adapter填入新列表
                // 如果只是更新部分数据，推荐使用notifyItemRangeChanged()或者notifyItemChanged()
                // notifyItemChanged(int)
                // notifyItemInserted(int)
                // notifyItemRemoved(int)
                // notifyItemRangeChanged(int, int)
                // notifyItemRangeInserted(int, int)
                // notifyItemRangeRemoved(int, int)
                val filterList = filter(keyWord)
                recyclerView.adapter = mAdapter
                mAdapter.setList(filterList)
                return false
            }
        })
    }

    private fun filter(keyWord: String): List<String> {
        // 过滤原本的列表，返回一个新的列表
        val filterList = mutableListOf<String>()

        for (l in list) {
            if (l.contains(keyWord)) filterList.add(l)
        }

        return filterList
    }
}
