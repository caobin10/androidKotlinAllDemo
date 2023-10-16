package com.example.myapplication3.util

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.blog.www.guideview.Component
import com.example.myapplication3.R

class MutiComponent(private val s: String) : Component {
    override fun getView(inflater: LayoutInflater): View {
        val ll = LinearLayout(inflater.context)
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.orientation = LinearLayout.HORIZONTAL
        ll.layoutParams = param
        val textView = TextView(inflater.context)
        textView.setTextColor(inflater.context.resources.getColor(R.color.white))
        textView.text = s
        textView.textSize = 20f
        val imageView = ImageView(inflater.context)
        imageView.setImageResource(R.mipmap.arrow)
        ll.removeAllViews()
        ll.addView(imageView)
        ll.addView(textView)
        return ll
    }

    override fun getAnchor(): Int {
        return Component.ANCHOR_BOTTOM
    }

    override fun getFitPosition(): Int {
        return Component.FIT_CENTER
    }

    override fun getXOffset(): Int {
        return 0
    }

    override fun getYOffset(): Int {
        return 20
    }
}