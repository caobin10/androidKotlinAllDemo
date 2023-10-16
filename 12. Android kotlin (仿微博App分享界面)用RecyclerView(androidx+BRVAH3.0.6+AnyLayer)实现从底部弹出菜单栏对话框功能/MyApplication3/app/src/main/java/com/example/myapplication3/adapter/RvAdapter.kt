package com.example.myapplication3.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter(layoutResId: Int = R.layout.rv_item) :
    BaseQuickAdapter<MutableMap<Int, String>, BaseViewHolder>(layoutResId){
    override fun convert(helper: BaseViewHolder, item: MutableMap<Int, String>) {
        helper.itemView.run {
            item.entries.forEach {

                iv_item.load(it.key)
                tv_item.text = it.value
            }
        }
    }
}