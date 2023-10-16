package com.example.myapplication3.adapter

import android.graphics.Color
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.bean.MenuData
import kotlinx.android.synthetic.main.layout_menu_item.view.*

/**
 * 要显示的adapter
 */

class MenuDialogAdapter(layoutResId: Int = R.layout.layout_menu_item) :
    BaseQuickAdapter<MenuData, BaseViewHolder>(layoutResId) {

    private var selectedPos = -1
    private var mSelectedBackgroundResource = 0//选中item时的背景颜色
    private var mNormalBackgroundResource = 0//为选中的背景颜色
    private var hasDivider = true


    override fun convert(holder: BaseViewHolder, item: MenuData) {
        holder.itemView.run {
            menu_item_textview.text = item.name
        }
        holder.itemView.isSelected = selectedPos == holder.adapterPosition //设置选中时的view
        holder.itemView.run {
            menu_item_textview.isSelected = selectedPos == holder.adapterPosition

            //选中后的标题字体颜色
//            menu_item_textview.setTextColor(if (selectedPos == holder.adapterPosition) -0xff4b37 else -0xcccccd)
            menu_item_textview.setTextColor(if (selectedPos == holder.adapterPosition) Color.RED else Color.BLACK)
        }
        //选中与未选中的背景色
        if (mNormalBackgroundResource == 0) {
            mNormalBackgroundResource = R.color.white
        }
        if (mSelectedBackgroundResource != 0) {
            holder.itemView.menu_item_ly!!.setBackgroundResource(
                if (selectedPos == holder.adapterPosition)
                    mSelectedBackgroundResource else mNormalBackgroundResource
            )
        }

        //隐藏view
        holder.itemView.run {
            menu_item_divider.visibility = if (hasDivider) View.VISIBLE else View.INVISIBLE
        }
    }

    fun setSelectedBackgroundResource(mSelectedBackgroundResource: Int) {
        this.mSelectedBackgroundResource = mSelectedBackgroundResource
    }

    fun setNormalBackgroundResource(mNormalBackgroundResource: Int) {
        this.mNormalBackgroundResource = mNormalBackgroundResource
    }

    fun setHasDivider(hasDivider: Boolean) {
        this.hasDivider = hasDivider
    }

    //选中的position,及时更新数据
    fun setSelectedPos(selectedPos: Int) {
        this.selectedPos = selectedPos
        notifyDataSetChanged()
    }
}