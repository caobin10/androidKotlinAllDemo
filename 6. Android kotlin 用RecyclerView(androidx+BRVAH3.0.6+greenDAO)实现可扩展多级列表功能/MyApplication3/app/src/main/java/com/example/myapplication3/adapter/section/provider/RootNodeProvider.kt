package com.example.myapplication3.adapter.section.provider

import android.graphics.Color
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isGone
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.AppConfig.EXPAND_COLLAPSE_PAYLOAD
import com.example.myapplication3.R
import com.example.myapplication3.adapter.section.node.RootNode
import com.example.myapplication3.ext.rotation
import com.example.myapplication3.util.getCodeNode
import kotlinx.android.synthetic.main.item_expandable_group.view.*


class RootNodeProvider : BaseNodeProvider(){

    override val itemViewType: Int
        get() = 0

    override val layoutId: Int
        get() = R.layout.item_expandable_group

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity: RootNode = item as RootNode

        setLeftMargin(helper, entity)

        setSerValue(helper,entity)

        // 增量刷新，使用动画变化箭头
        setArrowSpin(helper, item, false)
    }

    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        for (payload in payloads) {
            if (payload is Int && payload == EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, item, true)
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {

        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        getAdapter()!!.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)

        val entity = data as RootNode

        if(entity.isExpanded){
            var child = entity.childNode
            //字节的没有数据 就添加
            if(child?.size?:0 == 0 ){
                var child = addChildNodes(entity)
                //添加子节点
                getAdapter()!!.nodeAddData(entity, 0, child)
            }
        }
    }

    /**
     * 添加子节点数据
     */
    private fun addChildNodes(item: RootNode): MutableList<BaseNode> {
        val list = getCodeNode(item.code)
        val child = mutableListOf<BaseNode>()
        list.forEach {
            var isHaveChild = getCodeNode(it.code).size > 0
            var childItem = RootNode(it.code,it.name,isHaveChild)
            child.add(childItem)
        }
        return child
    }

    //设置leftMargin
    private fun setLeftMargin(helper: BaseViewHolder, entity: RootNode) {
        helper.itemView.run {
            val layoutParams = tvTitle.layoutParams as LinearLayout.LayoutParams
            layoutParams.leftMargin = (entity.code.length - 2 )* 35 + 25
            tvTitle.layoutParams = layoutParams
            ivIcon.isGone = !entity.isHaveChild
        }
    }

    private fun setSerValue(helper: BaseViewHolder,entity: RootNode) {
        helper.itemView.run {
            var name = entity.name
            if(!entity.isHaveChild){
                tvTitle.textSize = 14f
                tvTitle.setTextColor(Color.RED)
            }else{
                tvTitle.textSize = 16f
                tvTitle.setTextColor(resources.getColor(R.color.black))
            }
            tvTitle.text = Html.fromHtml(name)
        }
    }
    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {

        val entity: RootNode = data as RootNode
        val imageView = helper.getView<ImageView>(R.id.ivIcon)
        if (entity.isExpanded) {
            imageView.rotation(0f,isAnimate)
        } else {
            imageView.rotation(90f,isAnimate)
        }
    }
}