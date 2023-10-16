package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.data.SecondClassItem
import kotlinx.android.synthetic.main.right_rv_item.view.*

//二级分类
class SecondClassAdapter(layoutResId: Int = R.layout.right_rv_item) :
    BaseQuickAdapter<SecondClassItem, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: SecondClassItem) {

        holder.itemView.run {
            right_item_name.text = item.name
        }
    }
}
