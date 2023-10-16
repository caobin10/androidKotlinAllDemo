package com.example.myapplication3.entity.node.section

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

class RootNode(var childNodes: List<BaseNode>, var title: String) : BaseExpandNode(){

    init {
        this.title = title
    }

    override val childNode: MutableList<BaseNode>?
        get() = childNodes as MutableList<BaseNode>

}