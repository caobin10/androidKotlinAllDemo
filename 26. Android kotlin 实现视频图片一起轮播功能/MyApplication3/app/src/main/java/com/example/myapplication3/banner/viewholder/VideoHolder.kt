package com.example.myapplication3.banner.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.example.myapplication3.R

class VideoHolder(view: View) : RecyclerView.ViewHolder(view) {
    var player: JzvdStd

    init {
        player = view.findViewById(R.id.player)
    }
}