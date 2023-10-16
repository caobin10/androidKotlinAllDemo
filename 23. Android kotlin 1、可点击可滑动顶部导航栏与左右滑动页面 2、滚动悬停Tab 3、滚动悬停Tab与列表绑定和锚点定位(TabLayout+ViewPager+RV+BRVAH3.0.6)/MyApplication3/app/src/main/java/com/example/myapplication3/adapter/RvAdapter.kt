package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter (layoutResId: Int = R.layout.rv_item) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.run {
            tv_content.text = item
        }

        if (mCallBack != null) {
            mCallBack!!.convert(holder)
        }
    }
    //回调
    private var mCallBack: LastItemViewCallBack? = null

    fun setLastItemViewCallBack(CallBack: LastItemViewCallBack?) {
        mCallBack = CallBack
    }

    interface LastItemViewCallBack {
        fun convert(holder: BaseViewHolder?)
    }
}