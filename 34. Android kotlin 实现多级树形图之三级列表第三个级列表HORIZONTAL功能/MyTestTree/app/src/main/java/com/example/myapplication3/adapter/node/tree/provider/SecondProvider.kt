package com.example.myapplication3.adapter.node.tree.provider

import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.node.tree.SecondNode
import kotlinx.android.synthetic.main.item_node_second.view.title

class SecondProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_node_second

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity: SecondNode = data as SecondNode
        helper.itemView.run { title.text = entity.title }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
//        val entity = data as SecondNode
//        if (entity.isExpanded) {
//            getAdapter()?.collapse(position)
//        } else {
//            getAdapter()?.expandAndCollapseOther(position)
//        }
    }
}

