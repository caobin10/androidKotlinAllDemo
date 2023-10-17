package com.example.mytesttree.adapter.node.tree

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.mytesttree.adapter.node.tree.provider.FirstProvider
import com.example.mytesttree.adapter.node.tree.provider.SecondProvider
import com.example.mytesttree.adapter.node.tree.provider.ThirdProvider
import com.example.mytesttree.entity.node.tree.FirstNode
import com.example.mytesttree.entity.node.tree.SecondNode
import com.example.mytesttree.entity.node.tree.ThirdNode

class NodeTreeAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(FirstProvider())
        addFullSpanNodeProvider(SecondProvider())
        addFullSpanNodeProvider(ThirdProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is FirstNode) {
            return 1
        } else if (node is SecondNode) {
            return 2
        } else if (node is ThirdNode) {
            return 3
        }
        return -1
    }

    val EXPAND_COLLAPSE_PAYLOAD = 110
}
