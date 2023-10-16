package com.example.myapplication3.adapter

import android.graphics.Color
import android.view.Gravity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.TestModel
import kotlinx.android.synthetic.main.item_text_view.view.*

class RvAdapter(layoutResId: Int = R.layout.item_text_view) :
    BaseQuickAdapter<TestModel, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: TestModel) {
        helper.itemView.run {
            item_info.text = item.info
            if (item.isTitle){
                item_info.setBackgroundResource(R.color.red)
                item_info.setTextColor(Color.BLACK)
                item_info.gravity = Gravity.CENTER
                item_info.setPadding(0,item_info.paddingTop,0,item_info.paddingBottom)
            }else{
                item_info.setBackgroundColor(Color.WHITE)
                item_info.setTextColor(Color.BLACK)
                item_info.gravity = Gravity.LEFT
                item_info.setPadding(40,item_info.paddingTop,0,item_info.paddingBottom)
            }
        }
    }
}