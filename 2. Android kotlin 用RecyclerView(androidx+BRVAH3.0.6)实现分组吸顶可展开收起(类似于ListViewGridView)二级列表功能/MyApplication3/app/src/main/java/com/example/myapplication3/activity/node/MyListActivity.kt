package com.example.myapplication3.activity.node

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.myapplication3.R
import com.example.myapplication3.adapter.node.section.NodeSectionAdapter
import com.example.myapplication3.entity.node.section.ItemNode
import com.example.myapplication3.entity.node.section.RootNode
import kotlinx.android.synthetic.main.activity_type_list.*

class MyListActivity :AppCompatActivity(){

    //listType = 1，1表示item子菜单类似于ListView
    private var listType:Int = 1

    private var list = mutableListOf<BaseNode>()

    private val mAdapter by lazy {
        NodeSectionAdapter(listType)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_list)
        initView()
        initData()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this@MyListActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_list.layoutManager = layoutManager
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
            val itemEntity1 = ItemNode(0, "二级菜单1")
            val itemEntity2 = ItemNode(0, "二级菜单2")
            val itemEntity3 = ItemNode(0, "二级菜单3")
            val itemEntity4 = ItemNode(0, "二级菜单4")
            val itemEntity5 = ItemNode(0, "二级菜单5")
            val itemEntity6 = ItemNode(0, "二级菜单6")
            val itemEntity7 = ItemNode(0, "二级菜单7")
            val itemEntity8 = ItemNode(0, "二级菜单8")

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