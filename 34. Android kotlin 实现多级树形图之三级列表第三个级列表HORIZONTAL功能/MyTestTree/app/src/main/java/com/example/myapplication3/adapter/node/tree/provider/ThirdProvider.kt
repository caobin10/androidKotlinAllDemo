package com.example.myapplication3.adapter.node.tree.provider

import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.node.tree.ThirdNode
import kotlinx.android.synthetic.main.item_node_third.view.title

class ThirdProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 3

    override val layoutId: Int
        get() = R.layout.item_node_third

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity: ThirdNode = data as ThirdNode
        helper.itemView.run { title.text = entity.title }
    }
}