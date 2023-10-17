package com.example.mytesttree.adapter.node.tree.provider

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mytesttree.R
import com.example.mytesttree.adapter.node.tree.NodeTreeAdapter
import com.example.mytesttree.entity.node.tree.SecondNode
import com.example.mytesttree.ext.rotation
import kotlinx.android.synthetic.main.item_node_second.view.title

class SecondProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_node_second

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity: SecondNode = data as SecondNode
        helper.itemView.run {
            title.text = entity.title
        }
        setArrowSpin(helper, data, false)
    }

    override fun convert(helper: BaseViewHolder, data: BaseNode, payloads: List<Any>) {
        for (payload in payloads) {
            if (payload is Int && payload == NodeTreeAdapter().EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, data, true)
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()!!.expandOrCollapse(
            position,
            true,
            true,
            NodeTreeAdapter().EXPAND_COLLAPSE_PAYLOAD
        )
    }

//    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
//        val entity = data as SecondNode
//        if (entity.isExpanded) {
//            getAdapter()?.collapse(position)
//        } else {
//            getAdapter()?.expandAndCollapseOther(position)
//        }
//    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity: SecondNode = data as SecondNode
        val imageView = helper.getView<ImageView>(R.id.ivIcon)
        if (entity.isExpanded) {
            imageView.rotation(90f, isAnimate)
        } else {
            imageView.rotation(0f, isAnimate)
        }
    }
}

