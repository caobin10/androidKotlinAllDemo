package com.example.myapplication3.activity.node

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.myapplication3.R
import com.example.myapplication3.adapter.node.section.NodeSectionAdapter
import com.example.myapplication3.entity.node.section.ItemNode
import com.example.myapplication3.entity.node.section.RootNode
import kotlinx.android.synthetic.main.activity_type_list.*

class MyGridActivity : AppCompatActivity()
{

    //listType = 2，2表示item子菜单类似于GridView，
    private var gridType:Int = 2

    private var list = mutableListOf<BaseNode>()

    private val mAdapter by lazy {
        NodeSectionAdapter(gridType)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_list)
        initView()
        initData()
    }

    private fun initView() {
        rv_list.layoutManager = GridLayoutManager(this@MyGridActivity,5)
        rv_list.adapter = mAdapter
    }

    private fun initData() {
        setResources()
        mAdapter.setList(list)
    }

    private fun setResources() {
        disableGroup()
    }

    private fun disableGroup() {
        for (i in 0 until 8){

            //Item Node
            val itemEntity1 = ItemNode(R.mipmap.ic_c, getString(R.string.test1))//<string name="test1">测试1</string>
            val itemEntity2 = ItemNode(R.mipmap.ic_c, getString(R.string.test2))//<string name="test2">测试2</string>
            val itemEntity3 = ItemNode(R.mipmap.ic_c, getString(R.string.test3))//<string name="test3">测试3</string>
            val itemEntity4 = ItemNode(R.mipmap.ic_c, getString(R.string.test4))//<string name="test4">测试4</string>
            val itemEntity5 = ItemNode(R.mipmap.ic_c, getString(R.string.test5))//<string name="test5">测试5</string>
            val itemEntity6 = ItemNode(R.mipmap.ic_c, getString(R.string.test6))//<string name="test6">测试6</string>
            val itemEntity7 = ItemNode(R.mipmap.ic_c, getString(R.string.test7))//<string name="test7">测试7</string>
            val itemEntity8 = ItemNode(R.mipmap.ic_c, getString(R.string.test8))//<string name="test8">测试8</string>

            val items = mutableListOf<BaseNode>()
            items.add(itemEntity1)
            items.add(itemEntity2)
            items.add(itemEntity3)
            items.add(itemEntity4)
            items.add(itemEntity5)
            items.add(itemEntity6)
            items.add(itemEntity7)
            items.add(itemEntity8)

            // Root Node
            val entity = RootNode(items,"一级标题$i")
            if (i == 0){
                // 第1号数据默认不展开
                entity.isExpanded = true
            }else{
                entity.isExpanded = false
            }
            list.add(entity)
        }
    }

    fun goBack(v: View){
        finish()
    }
}

