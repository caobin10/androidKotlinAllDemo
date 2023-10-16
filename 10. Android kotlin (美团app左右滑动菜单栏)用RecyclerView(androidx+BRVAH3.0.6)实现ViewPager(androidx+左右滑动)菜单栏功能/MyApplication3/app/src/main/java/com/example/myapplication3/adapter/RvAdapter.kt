package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.bean.MenuItem
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class RvAdapter(data:MutableList<MenuItem>, curIndex:Int, pageSize:Int, layoutResId: Int = R.layout.item_recyclerview) :
    BaseQuickAdapter<MenuItem, BaseViewHolder>(layoutResId) {

    private var mDatas: MutableList<MenuItem>? = null

    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private var mCurIndex = 0

    /**
     * 每一页显示的个数
     */
    private var mPageSize = 0

    init {
        this.mDatas = data
        this.mCurIndex = curIndex
        this.mPageSize = pageSize
    }

    override fun convert(helper: BaseViewHolder, item: MenuItem) {

        helper.itemView.run {
            imageView.setImageResource(item.iconRes)
            textView.text = item.name
        }
    }

    /**
     * 先判断数据集的大小是否足够显示满本页,如果够，则直接返回每一页显示的最大条目个数pageSize,如果不够，则有几项就返回几,(也就是最后一页的时候就显示剩余item)
     */
    override fun getItemCount(): Int {
        return if (data.size > (mCurIndex + 1) * mPageSize) mPageSize else data.size - mCurIndex * mPageSize
    }

    override fun getItem(position: Int): MenuItem {
        return data[position + mCurIndex * mPageSize]
    }

    override fun getItemId(position: Int): Long {
        return (position + mCurIndex * mPageSize).toLong()
    }
}
