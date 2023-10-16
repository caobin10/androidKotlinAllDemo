package com.example.myapplication3.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.data.Dict


//多选
class RvMCAdapter(var sels:MutableSet<Dict>, layoutResId: Int = R.layout.item_rad) :
        BaseQuickAdapter<Dict, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: Dict) {

        var tvName = helper.getView<TextView>(R.id.tvName)
        var tvSelected = helper.getView<TextView>(R.id.tvSelected)

        tvName.text = item.dataName
        // 根据选中状态更新UI
        tvName.setTextColor(context.resources.getColor(R.color.v666666))
        tvSelected.visibility = View.GONE

        if(sels.contains(item)){
            tvName.setTextColor(context.resources.getColor(R.color.red))
            tvSelected.visibility = View.VISIBLE
        }

    }

    fun  setCheckSelValue(sels: MutableSet<Dict>){
        this.sels = sels
        notifyDataSetChanged()
    }
}