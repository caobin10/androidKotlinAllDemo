package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item.view.*

class RVAdapter(layoutResId: Int = R.layout.item) :
    BaseQuickAdapter<MutableMap<String, String>, BaseViewHolder>(layoutResId), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: MutableMap<String, String>) {
        holder.itemView.run {
            tv_code.text = "人号："+item["Code"]
            tv_name.text = "姓名："+ item["Name"]
            tv_age.text = "年龄：" + item["Age"]
        }
    }
}