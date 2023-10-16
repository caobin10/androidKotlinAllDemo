package com.example.myapplication3.entity.node.section

import com.chad.library.adapter.base.entity.node.BaseNode

class ItemNode(var img: Int, var name: String) : BaseNode() {

    override val childNode: MutableList<BaseNode>?
        get() = null
}