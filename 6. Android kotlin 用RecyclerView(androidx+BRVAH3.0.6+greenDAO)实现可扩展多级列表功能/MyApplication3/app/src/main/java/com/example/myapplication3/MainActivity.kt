package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.adapter.section.node.RootNode
import com.example.myapplication3.file.File.copyDbFileFromAsset
import com.example.myapplication3.util.getCodeNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        RvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFile()
        initView()
        initNodeData()
    }

    private fun initFile() {
        val map = mutableMapOf<String, Boolean>()
        map["test.db"] = true
        copyDbFileFromAsset(this@MainActivity, map)
    }

    private fun initView() {
        mRecyclerView.adapter = mAdapter
    }

    private fun initNodeData() {

        val list = mutableListOf<BaseNode>()
        val prent = getCodeNode()
        prent.forEach {
            var item = RootNode(it.code, it.name, true)
            list.add(item)
        }
        mAdapter.setList(list)
    }
}

