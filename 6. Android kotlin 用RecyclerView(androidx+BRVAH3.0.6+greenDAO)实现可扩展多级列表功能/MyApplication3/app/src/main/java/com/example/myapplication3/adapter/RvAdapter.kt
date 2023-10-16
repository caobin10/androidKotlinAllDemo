package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.myapplication3.adapter.section.provider.RootNodeProvider

class RvAdapter : BaseNodeAdapter() {

    init {
        addNodeProvider(RootNodeProvider())//一级标题
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return 0
    }
}