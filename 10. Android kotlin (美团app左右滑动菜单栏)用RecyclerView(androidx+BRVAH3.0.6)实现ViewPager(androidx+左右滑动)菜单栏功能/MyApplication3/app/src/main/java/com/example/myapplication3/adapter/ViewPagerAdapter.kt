package com.example.myapplication3.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(mViewList: List<View>?) :
    PagerAdapter() {
    private val mViewList: List<View>?

    init {
        this.mViewList = mViewList
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViewList!![position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mViewList!![position])
        return mViewList[position]
    }

    override fun getCount(): Int {
        return mViewList?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}

