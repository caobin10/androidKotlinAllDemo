package com.example.myapplication3.view

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

/**
 * 主页面左右滑动
 */
class MyViewPager : ViewPager {
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?) : super(context!!)
    //判断menu在x,y的位置
    override fun scrollTo(x: Int, y: Int) {
        if (adapter == null || x > width * (adapter!!.count - 2)) {
            return
        }
        super.scrollTo(x, y)
    }
}