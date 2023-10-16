package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter(layoutResId: Int = R.layout.rv_item) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.run {
            tv_content.text = item
        }
    }
}
