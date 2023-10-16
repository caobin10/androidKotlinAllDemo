package com.example.myapplication3.adapter.node.tree.provider

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.adapter.node.tree.NodeTreeAdapter
import com.example.myapplication3.entity.node.tree.FirstNode
import com.example.myapplication3.ext.rotation

class FirstProvider : BaseNodeProvider(){

    override val itemViewType: Int
        get() = 1

    override val layoutId: Int
        get() = R.layout.item_node_first

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity: FirstNode = data as FirstNode
        helper.setText(R.id.header, entity.title)
        // 增量刷新，使用动画变化箭头
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
        getAdapter()!!.expandOrCollapse(position,true,true,NodeTreeAdapter().EXPAND_COLLAPSE_PAYLOAD)
    }

//    override fun onLongClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int): Boolean {
//        val entity: FirstNode = data as FirstNode
//        if (entity.isExpanded) {
//            view.postDelayed(Runnable {
//                getAdapter()!!.collapse(position)
//            },300)
//        }
////        else {
////            getAdapter()!!.expandAndCollapseOther(position)
////        }
//        return true //只执行长按事件
////        return false
//    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity: FirstNode = data as FirstNode
        val imageView = helper.getView<ImageView>(R.id.ivIcon)
        if (entity.isExpanded) {
            imageView.rotation(90f, isAnimate)
        } else {
            imageView.rotation(0f, isAnimate)
        }
    }
}
