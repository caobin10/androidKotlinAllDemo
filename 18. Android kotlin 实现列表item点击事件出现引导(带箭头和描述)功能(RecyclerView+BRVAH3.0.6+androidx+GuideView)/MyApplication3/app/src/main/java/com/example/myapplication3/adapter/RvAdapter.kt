package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item.view.*

class RvAdapter(layoutResId: Int = R.layout.item) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: String) {

        if (mCallBack != null) {
            mCallBack!!.convert(holder, holder.layoutPosition);
        }
    }

    //回调
    private var mCallBack: ItemSelectedCallBack? = null

    fun setItemSelectedCallBack(CallBack: ItemSelectedCallBack?) {
        mCallBack = CallBack
    }

    interface ItemSelectedCallBack {
        fun convert(holder: BaseViewHolder?, position: Int)
    }
}
