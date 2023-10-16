package com.example.myapplication3.viewpagerbanner.adapter

import android.view.View
import android.view.ViewGroup

import androidx.viewpager.widget.PagerAdapter


class VpBannerAdapter(viewList: List<View>) : PagerAdapter() {
    private val viewList: List<View>
    private val size: Int
    private val cacheCount = 3

    init {
        this.viewList = viewList
        size = viewList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (viewList.size > cacheCount) {
            container.removeView(viewList[position % size])
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val parent = viewList[position % size].parent as? ViewGroup
        parent?.removeView(viewList[position % size])
        container.addView(viewList[position % size])
        return viewList[position % size]
    }

    override fun getCount(): Int {
        return size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
