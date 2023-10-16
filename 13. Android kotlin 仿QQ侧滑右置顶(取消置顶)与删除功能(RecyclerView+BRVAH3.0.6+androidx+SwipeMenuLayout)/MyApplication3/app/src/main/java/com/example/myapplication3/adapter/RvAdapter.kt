package com.example.myapplication3.adapter

import cn.ljp.swipemenu.SwipeMenuLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.SlidingItembean
import kotlinx.android.synthetic.main.item.view.*

class RvAdapter(layoutResId: Int = R.layout.item) :
    BaseQuickAdapter<SlidingItembean, BaseViewHolder>(layoutResId) {

    //侧滑右顶置
    private var mFirstOnItemClickListener: FirstOnItemClickListener? = null

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun convert(holder: BaseViewHolder, item: SlidingItembean) {
        val mSwipe: SwipeMenuLayout = holder.getView(R.id.swipe_menu_layout)

        //侧滑右删
        mSwipe.setEnableRightSwipe(false)

        //侧滑左删
//        mSwipe.setEnableRightSwipe(true)

        holder.itemView.run {

            tv_content.text = item.num

            if (item.setTop.equals("取消置顶")){
                swipe_menu_layout.setBackgroundColor(resources.getColor(R.color.colorRipple))
            }else if (item.setTop.equals("置顶")){
                swipe_menu_layout.setBackgroundColor(resources.getColor(R.color.white))
            }

            tv_toFirst.text = item.setTop

            //侧滑右置顶
            tv_toFirst.setOnClickListener {
                mFirstOnItemClickListener!!.firstOnItemClickListener(holder)
            }

            //侧滑右删
            tv_menu2.setOnClickListener {
                mOnItemClickListener!!.onItemClickListener(holder)
            }
        }
    }

    fun setFirstOnItemClickListener(firstOnItemClickListener: FirstOnItemClickListener) {
        this.mFirstOnItemClickListener = firstOnItemClickListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface FirstOnItemClickListener {
        fun firstOnItemClickListener(holder: BaseViewHolder)
    }

    interface OnItemClickListener {
        fun onItemClickListener(holder: BaseViewHolder)
    }
}
