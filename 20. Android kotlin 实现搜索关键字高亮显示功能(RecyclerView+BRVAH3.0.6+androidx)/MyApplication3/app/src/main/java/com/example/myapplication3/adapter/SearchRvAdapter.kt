package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.Bean.User
import com.example.myapplication3.R

class SearchRvAdapter(layoutResId: Int = R.layout.search_rv_item) : BaseQuickAdapter<User, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: User) {

        if (mCallBack != null) {
            mCallBack!!.convert(holder, item);
        }
    }

    //回调
    private var mCallBack: ItemSelectedCallBack? = null

    fun setItemSelectedCallBack(CallBack: ItemSelectedCallBack?) {
        mCallBack = CallBack
    }

    interface ItemSelectedCallBack {
        fun convert(holder: BaseViewHolder?, item:User)
    }
}
