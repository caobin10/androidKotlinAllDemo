package com.example.myapplication3.adapter.node.section.provider

import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.node.section.ItemNode
import kotlinx.android.synthetic.main.item_section_grid_content.view.*
import kotlinx.android.synthetic.main.item_section_list_content.view.*

class SecondNodeProvider(private val type: Int = 0) : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = when (type) {

            //布局list
            1 -> R.layout.item_section_list_content

            //布局grid
            else -> R.layout.item_section_grid_content
        }

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        if (item == null) return
        val entity: ItemNode = item as ItemNode

        when(type){

            //布局list
            1->{
                helper.itemView.run {
                    header.text = entity.name
                }
            }

            //布局grid
            2->{
                helper.itemView.run {
                    iv.setImageResource(entity.img)
                    tv.text = entity.name
                }
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }
}