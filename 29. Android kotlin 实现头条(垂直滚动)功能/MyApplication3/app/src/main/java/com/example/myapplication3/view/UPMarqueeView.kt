package com.example.myapplication3.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import com.example.myapplication3.R


class UPMarqueeView(context: Context, attrs: AttributeSet) : ViewFlipper(context, attrs) {
    private var mContext: Context? = null
    private val isSetAnimDuration = false
    private val interval = 2000

    /**
     * 动画时间
     */
    private val animDuration = 500

    init {
        init(context, attrs, 0)
    }

    private fun init(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        mContext = context
        flipInterval = interval
        val animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in)
        if (isSetAnimDuration) animIn.duration = animDuration.toLong()
        inAnimation = animIn
        val animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out)
        if (isSetAnimDuration) animOut.duration = animDuration.toLong()
        outAnimation = animOut
    }

    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    fun setViews(views: List<View?>?) {
        if (views.isNullOrEmpty()) return
        removeAllViews()
        for (i in views.indices) {
            addView(views[i])
        }
        startFlipping()
    }
}
