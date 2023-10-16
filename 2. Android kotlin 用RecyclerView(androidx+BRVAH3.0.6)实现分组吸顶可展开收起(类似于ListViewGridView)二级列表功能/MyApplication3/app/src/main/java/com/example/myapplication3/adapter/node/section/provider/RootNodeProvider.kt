package com.example.myapplication3.adapter.node.section.provider

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.node.section.RootNode
import com.example.myapplication3.ext.rotation
import kotlinx.android.synthetic.main.def_section_head.view.*

class RootNodeProvider(private val type: Int = 0) : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 0

    override val layoutId: Int
        get() = R.layout.def_section_head

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity: RootNode = item as RootNode
        helper.setText(R.id.header, entity.title)

        if (type == 3) {
            helper.itemView.run {
                node_ll.setBackgroundColor(Color.GREEN)
                ivIcon.visibility = View.GONE
            }
            return
        }
        // 增量刷新，使用动画变化箭头
        setArrowSpin(helper, item, false)
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {

        if (type == 3) return

        getAdapter()!!.expandOrCollapse(position)
    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity: RootNode = data as RootNode
        val imageView = helper.getView<ImageView>(R.id.ivIcon)
        if (entity.isExpanded) {
            imageView.rotation(0f, isAnimate)
        } else {
            imageView.rotation(90f, isAnimate)
        }
    }
}