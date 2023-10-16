package com.example.myapplication3.past.newacticity

import android.content.Context

/**
 * Created by zhangzhihao on 2018/3/6.
 */
fun Context.dip2px(value: Float): Float {
    val scale = this.resources.displayMetrics.density
    return value * scale + 0.5f
}