package com.example.myapplication3.banner.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
    var imageView: ImageView

    init {
        imageView = view as ImageView
    }
}