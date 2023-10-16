package com.example.myapplication3.adapter.node.section

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.myapplication3.adapter.node.section.provider.RootNodeProvider
import com.example.myapplication3.adapter.node.section.provider.SecondNodeProvider
import com.example.myapplication3.entity.node.section.RootNode

class NodeSectionAdapter(type: Int = 0) : BaseNodeAdapter() {

    init {

        when (type) {
            //list
            1 -> {
                addFullSpanNodeProvider(RootNodeProvider())
                addNodeProvider(SecondNodeProvider(1))//1表示列表下的子菜单类似于ListView
            }

            //grid
            2 -> {
                addFullSpanNodeProvider(RootNodeProvider())
                addNodeProvider(SecondNodeProvider(2))//2表示列表下的子菜单类似于GridView
            }

            //group,分组列表
            3->{
                addFullSpanNodeProvider(RootNodeProvider(3))//3表示分组列表
                addNodeProvider(SecondNodeProvider(1))//1表示列表下的子菜单类似于ListView
            }
        }
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        return if (node is RootNode) {
            0
        } else {
            1
        }
        return -1
    }
}