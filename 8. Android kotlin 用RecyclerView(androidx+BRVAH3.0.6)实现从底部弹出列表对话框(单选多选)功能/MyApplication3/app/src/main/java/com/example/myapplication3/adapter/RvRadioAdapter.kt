package com.example.myapplication3.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.data.Dict

//单选
class RvRadioAdapter(var selValue: String, layoutResId: Int = R.layout.item_rad) :
    BaseQuickAdapter<Dict, BaseViewHolder>(layoutResId) {


    override fun convert(helper: BaseViewHolder, item: Dict) {

        var tvName = helper.getView<TextView>(R.id.tvName)
        var tvSelected = helper.getView<TextView>(R.id.tvSelected)

        tvName.text = item.dataName
        // 根据选中状态更新UI
        tvName.setTextColor(context.resources.getColor(R.color.v666666))
        tvSelected.visibility = View.GONE

        if(item.dataValue.equals(selValue)){
            tvName.setTextColor(context.resources.getColor(R.color.red))
            tvSelected.visibility = View.VISIBLE
        }
    }

    fun getSelectIndex(): Int {
        var index = 0
        data.forEachIndexed { i, dict ->
            if(dict.dataValue == selValue){
                index = i
                return@forEachIndexed
            }
        }
        return index
    }
}