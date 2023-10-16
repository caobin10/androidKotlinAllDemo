package com.example.myapplication3.adapter.section.node

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

class RootNode(
    var code: String,
    var name: String,
    var isHaveChild: Boolean = false,
    override var childNode: MutableList<BaseNode>? = mutableListOf()
) :
    BaseExpandNode() {

    init {
        isExpanded = false
    }
}
