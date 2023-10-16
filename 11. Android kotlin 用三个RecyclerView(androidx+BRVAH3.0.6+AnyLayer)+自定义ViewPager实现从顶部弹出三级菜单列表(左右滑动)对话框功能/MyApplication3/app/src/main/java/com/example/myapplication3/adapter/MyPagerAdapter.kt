package com.example.myapplication3.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication3.view.MyViewPager

/**
 * 分页显示
 */
class MyPagerAdapter(private var viewList: MutableList<View?>) : PagerAdapter() {

//    override fun getCount(): Int {
//        return viewList.size?: 0
//    }

    override fun getCount() = viewList.size?:0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    /**
     * 每次滑动pager的position
     * @param container
     * @param position
     * @return
     */
    override fun instantiateItem(container: View, position: Int): Any {
        (container as MyViewPager).addView(viewList[position], 0)
        return viewList[position]!!
    }

    /**
     * 删除item
     * 当滑动到第三个item时，第一个item就会被destroy
     * @param container
     * @param position
     * @param object
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as MyViewPager).removeView(viewList[position]!!)
    }

    /**
     * pager的宽度
     * @param position
     * @return
     */
//    override fun getPageWidth(position: Int): Float {
//        return 0.5f
//    }

    override fun getPageWidth(position: Int) = 0.5f

}