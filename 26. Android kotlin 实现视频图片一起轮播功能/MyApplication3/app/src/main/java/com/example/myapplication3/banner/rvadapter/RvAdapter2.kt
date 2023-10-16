package com.example.myapplication3.banner.rvadapter

import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.example.myapplication3.banner.adapter.TopLineAdapter
import com.google.android.material.snackbar.Snackbar
import com.youth.banner.Banner
import com.youth.banner.transformer.AlphaPageTransformer
import com.youth.banner.transformer.DepthPageTransformer
import com.youth.banner.transformer.MZScaleInTransformer
import com.youth.banner.transformer.RotateDownPageTransformer
import com.youth.banner.transformer.RotateUpPageTransformer
import com.youth.banner.transformer.RotateYTransformer
import com.youth.banner.transformer.ScaleInTransformer
import com.youth.banner.transformer.ZoomOutPageTransformer
import com.youth.banner.util.LogUtils
import kotlinx.android.synthetic.main.activity_tou_tiao.view.*


class RvAdapter2(layoutResId: Int = R.layout.activity_tou_tiao) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId){
    override fun convert(holder: BaseViewHolder, item: String) {

        holder.itemView.run {
            val mListx: MutableList<DataBean> = ArrayList()
            mListx.add(DataBean("", "无限轮播", 1))
            mListx.add(DataBean("", "自动轮播，测试1", 2))
            mListx.add(DataBean("", "动手滑动，测试2", 3))
            banner.addBannerLifecycleObserver(context as LifecycleOwner)
                .setAdapter(TopLineAdapter(mListx))
                .setOrientation(Banner.VERTICAL)
//                .setPageTransformer(RotateYTransformer())
                .setOnBannerListener { data, position ->
                    Snackbar.make(banner, (data as DataBean).title, Snackbar.LENGTH_SHORT).show()
                    LogUtils.d("position：$position")
                }
            when(holder.adapterPosition){
                0 -> banner.setPageTransformer(AlphaPageTransformer())
//                1 -> banner.setPageTransformer(BasePageTransformer(0.6F))
                2 -> banner.setPageTransformer(DepthPageTransformer())
                3 -> banner.setPageTransformer(MZScaleInTransformer())
                4 -> banner.setPageTransformer(RotateDownPageTransformer())
                5 -> banner.setPageTransformer(RotateUpPageTransformer())
                6 -> banner.setPageTransformer(RotateYTransformer())
                7 -> banner.setPageTransformer(ScaleInTransformer())
                8 -> banner.setPageTransformer(ZoomOutPageTransformer())
                else -> {}
            }
        }
    }
}