package com.example.myapplication3.adapter

import android.widget.ImageView
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.MyPlayer
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class PlayerAdapter(layoutResId: Int = R.layout.item_recyclerview) :
    BaseQuickAdapter<MyPlayer, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item_url: MyPlayer) {

        holder.itemView.run {
            videoplayer.setUp(item_url.videoUrl,"",JzvdStd.SCREEN_NORMAL)
            videoplayer.thumbImageView.scaleType = ImageView.ScaleType.FIT_XY
//            videoplayer.thumbImageView.scaleType = ImageView.ScaleType.FIT_CENTER

            if ( 0 == holder.adapterPosition){
                videoplayer.startVideo() //自动播放 在recyclerview有bug
            }

            Glide.with(videoplayer.context).load(item_url.coverImageUrl).into(videoplayer.thumbImageView)
        }
    }
}