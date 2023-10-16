package com.example.myapplication3.banner.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class TopLineAdapter(mData: MutableList<DataBean>) : BannerAdapter<DataBean, TopLineAdapter.TopLineHolder>(mData) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): TopLineHolder {
        return TopLineHolder(BannerUtils.getView(parent, R.layout.top_line_item))
    }

    override fun onBindView(holder: TopLineHolder, data: DataBean, position: Int, size: Int) {
        holder!!.message.text = data!!.title
        if (data!!.viewType == 1) {
            holder.source.text = "无限轮播"
        } else if (data.viewType == 2) {
            holder.source.text = "自动轮播"
        } else if (data.viewType == 3) {
            holder.source.text = "动手滑动"
        }
    }

    inner class TopLineHolder(view: View) : RecyclerView.ViewHolder(view) {
        var message: TextView
        var source: TextView

        init {
            message = view.findViewById<TextView>(R.id.message)
            source = view.findViewById<TextView>(R.id.source)
        }
    }
}