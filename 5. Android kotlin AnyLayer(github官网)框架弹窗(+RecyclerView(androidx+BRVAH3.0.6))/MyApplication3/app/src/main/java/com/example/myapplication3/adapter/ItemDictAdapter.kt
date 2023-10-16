package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.MyList
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item_rad.view.*

class ItemDictAdapter(layoutResId: Int = R.layout.item_rad) :
    BaseQuickAdapter<MyList, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: MyList) {
        helper.itemView.run {
            tvName.text = item.dataName
        }
    }
}