package com.example.myapplication3.recyclerviewbanner.adapter

import android.widget.ImageView
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.DataBean

class RvBannerAdapter :BaseMultiItemQuickAdapter<DataBean,BaseViewHolder>(){

    init {
        addItemType(1, R.layout.banner_video)
        addItemType(2,R.layout.banner_image)
    }

    override fun convert(holder: BaseViewHolder, item: DataBean) {
        when(item.viewType){
            1 -> {
                //视频
                val jzvdStd : JzvdStd = holder.getView(R.id.player)
                //去掉
                jzvdStd.setUp(item.videoUrl,"")
                //设置封面
                Glide.with(context).load(item.imageUrl)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .centerCrop()
                    .into(jzvdStd.posterImageView)
//                jzvdStd.setVideoImageDisplayType(jzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);//去掉黑框
                jzvdStd.startVideo() //自动播放
            }
            2 -> {
                val imageView : ImageView = holder.getView(R.id.image)
                Glide.with(context)
                    .load(item.imageUrl)
//                    .centerCrop()
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView)
                imageView.setOnClickListener {  }
            }
        }
    }
}