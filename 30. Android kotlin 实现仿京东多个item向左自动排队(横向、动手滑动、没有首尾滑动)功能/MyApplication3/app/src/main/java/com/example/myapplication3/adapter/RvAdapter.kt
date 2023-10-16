package com.example.myapplication3.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R

class RvAdapter(layoutResId: Int = R.layout.rv_item) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val itemTv = holder.getView<TextView>(R.id.item_tv)
        itemTv.text = item
    }
}
