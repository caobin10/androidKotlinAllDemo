package com.example.myapplication3.adapter

import android.util.SparseArray
import android.view.View
import android.view.ViewTreeObserver
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.Bean.ExpandFoldTextBean
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item_expand_fold_text.view.*

class ExpandFoldTextAdapter(layoutResId: Int = R.layout.item_expand_fold_text) :
    BaseQuickAdapter<ExpandFoldTextBean, BaseViewHolder>(layoutResId) {

    private val MAX_LINE_COUNT = 3 //最大显示行数
    private val STATE_UNKNOW = -1 //未知状态
    private val STATE_NOT_OVERFLOW = 1 //文本行数小于最大可显示行数
    private val STATE_COLLAPSED = 2 //折叠状态
    private val STATE_EXPANDED = 3 //展开状态

    /**
     * 注意：保存文本状态集合的key一定要是唯一的，如果用position。
     * 如果使用position作为key，则删除、增加条目的时候会出现显示错乱
     */
    private val mTextStateList = SparseArray<Int>() //保存文本状态集合

    override fun convert(holder: BaseViewHolder, item: ExpandFoldTextBean) {

        val state: Int = mTextStateList!!.get(item.id, STATE_UNKNOW)

        //第一次初始化，未知状态
        if (state == STATE_UNKNOW) {
            holder.itemView.run {
                tv_content.viewTreeObserver.addOnPreDrawListener(object:ViewTreeObserver.OnPreDrawListener{
                    override fun onPreDraw(): Boolean {

                        //这个回掉会调用多次，获取完行数后记得注销监听
                        tv_content.viewTreeObserver.removeOnPreDrawListener(this);

                        //如果内容显示的行数大于最大显示行数
                        if (tv_content.lineCount > MAX_LINE_COUNT) {
                            tv_content.maxLines = MAX_LINE_COUNT//设置最大显示行数
                            tv_expand_or_fold.visibility = View.VISIBLE //显示“全文”
                            tv_expand_or_fold.text = "全文"
                            mTextStateList.put(item.id, STATE_COLLAPSED) //保存状态
                        } else {
                            tv_expand_or_fold.visibility = View.GONE
                            mTextStateList.put(item.id, STATE_NOT_OVERFLOW)
                        }
                        return true
                    }
                })
                tv_content.maxLines = Integer.MAX_VALUE //设置文本的最大行数，为整数的最大数值
                tv_content.text = item.content
            }
        } else {

            //如果之前已经初始化过了，则使用保存的状态。
            holder.itemView.run {
                when (state) {
                    STATE_NOT_OVERFLOW -> tv_expand_or_fold.visibility = View.GONE
                    STATE_COLLAPSED -> {
                        tv_content.maxLines = MAX_LINE_COUNT
                        tv_expand_or_fold.visibility = View.VISIBLE
                        tv_expand_or_fold.text = "全文"
                    }
                    STATE_EXPANDED -> {
                        tv_content.maxLines = Integer.MAX_VALUE
                        tv_expand_or_fold.visibility = View.VISIBLE
                        tv_expand_or_fold.text = "收起"
                    }
                }
                tv_content.text = item.content
            }
        }

        //全文和收起的点击事件
        holder.itemView.run {
            tv_expand_or_fold.setOnClickListener {
                val state: Int = mTextStateList!!.get(item.id, STATE_UNKNOW)
                if (state == STATE_COLLAPSED) {
                    tv_content.maxLines = Integer.MAX_VALUE
                    tv_expand_or_fold.text = "收起"
                    mTextStateList.put(item.id, STATE_EXPANDED)
                } else if (state == STATE_EXPANDED) {
                    tv_content.maxLines = MAX_LINE_COUNT
                    tv_expand_or_fold.text = "全文"
                    mTextStateList.put(item.id, STATE_COLLAPSED)
                }
            }
        }
    }
}