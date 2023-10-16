package com.example.myapplication3.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.data.FirstClassItem
import kotlinx.android.synthetic.main.left_rv_item.view.*

//一级分类
class FirstClassAdapter(layoutResId: Int = R.layout.left_rv_item) :
    BaseQuickAdapter<FirstClassItem, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: FirstClassItem) {

        //选中和没选中时，设置不同的颜色
        if (holder.adapterPosition == selectedPosition) {
            holder.itemView.setBackgroundResource(R.color.white)
        } else {
            holder.itemView.setBackgroundResource(R.color.bgColorThird)//<color name="bgColorThird">#E8E8E8</color>
        }
        holder.itemView.run {
            left_item_name.text = item.name
            if (item.secondList != null && item.secondList.size > 0) {
                first_iv.visibility = View.VISIBLE
            } else {
                first_iv.visibility = View.GONE
            }
        }
    }

    private var selectedPosition: Int = 0

    fun setSelectedPosition(selectedPosition: Int) {
        this.selectedPosition = selectedPosition
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }
}
