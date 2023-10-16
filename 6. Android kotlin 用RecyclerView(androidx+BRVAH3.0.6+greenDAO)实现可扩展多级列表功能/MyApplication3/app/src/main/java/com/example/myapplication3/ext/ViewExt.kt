package com.example.myapplication3.ext

import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.view.ViewCompat

/**
 * 旋转
 * @param value 角度
 * @param isAnimate 动画
 */
fun View.rotation(value:Float, isAnimate: Boolean){
    if (isAnimate) {
        ViewCompat.animate(this).setDuration(200)
            .setInterpolator(DecelerateInterpolator())
            .rotation(value)
            .start()
    } else {
        this.rotation = value
    }
}