package com.example.myapplication3.util

import android.app.Activity
import android.view.View
import com.blog.www.guideview.GuideBuilder

object GuideViewUtil {
    fun showGuideView(
        context: Activity?,
        view: View?,
        desc: String?,
        onDismissCallback: OnDismissCallback
    ) {
        val builder = GuideBuilder()
        builder.setTargetView(view)
            .setAlpha(150)
            .setHighTargetCorner(20)
            .setHighTargetPadding(10)
            .setOverlayTarget(false)
            .setOutsideTouchable(false)
        builder.setOnVisibilityChangedListener(object : GuideBuilder.OnVisibilityChangedListener {
            override fun onShown() {}
            override fun onDismiss() {
                onDismissCallback.onDismiss()
            }
        })
        val mutiComponent = desc?.let { MutiComponent(it) }
        builder.addComponent(mutiComponent)
        val guide = builder.createGuide()
        guide.setShouldCheckLocInWindow(true)
        guide.show(context)
    }

    interface OnDismissCallback {
        fun onDismiss()
    }
}