package com.example.myapplication3.viewpagerbanner.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.myapplication3.viewpagerbanner.util.DensityUtil
import com.example.myapplication3.viewpagerbanner.MyApplication


class ViewSwitcherHelper(context: Context, layout: ViewGroup, draw_one: Drawable, draw_two: Drawable) {
    private val mPosOff: Drawable
    private val mPosOn: Drawable
    private val viewGroup: ViewGroup
    private val mContext: Context
    private var currentPos = 0

    init {
        mContext = context
        viewGroup = layout
        mPosOn = draw_one
        mPosOff = draw_two
    }

    fun setViewSwitcherTip(count: Int, current: Int) {
        currentPos = current
        viewGroup.removeAllViews()
        if (count > 1) {
            for (i in 0 until count) {
                val mImageView: ImageView = getPositionView(i == current)
                mImageView.tag = i
                viewGroup.addView(mImageView)
            }
        } else {
        }
    }

    private fun getPositionView(isOn: Boolean): ImageView {
        val mImageView = ImageView(mContext)
        mImageView.setImageDrawable(if (isOn) mPosOn else mPosOff)
        val mLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLayoutParams.setMargins(
            DensityUtil.dip2px(MyApplication.instance, 2F),
            DensityUtil.dip2px(MyApplication.instance, 2F),
            DensityUtil.dip2px(MyApplication.instance, 2F),
            DensityUtil.dip2px(MyApplication.instance, 4F)
        )
        mImageView.layoutParams = mLayoutParams
        return mImageView
    }

    fun setCurrent(current: Int) {
        if (current >= viewGroup.childCount) {
            return
        }
        viewGroup.removeViewAt(currentPos)
        viewGroup.addView(getPositionView(false), currentPos)
        currentPos = current
        viewGroup.removeViewAt(current)
        viewGroup.addView(getPositionView(true), current)
    }
}
