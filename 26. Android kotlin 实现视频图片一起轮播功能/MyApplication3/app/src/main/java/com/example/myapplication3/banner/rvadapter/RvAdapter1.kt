package com.example.myapplication3.banner.rvadapter

import androidx.lifecycle.LifecycleOwner
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.example.myapplication3.banner.adapter.MultipleTypesAdapter
import com.example.myapplication3.banner.viewholder.VideoHolder
import com.youth.banner.indicator.BaseIndicator
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.indicator.RoundLinesIndicator
import com.youth.banner.listener.OnPageChangeListener
import com.youth.banner.transformer.*
import kotlinx.android.synthetic.main.activity_banner.view.banner

class RvAdapter1(layoutResId: Int = R.layout.activity_banner) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {

    //第一个，视频封面图地址、视频地址
    private val imageUrl1 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F70c03ac511079c42b2ecddef0ff4444f846de67ebf58a-ZQ9m0f_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641973590&t=2c23fb6d6b200160666f0ccd81d7368a"
    private val videoUrl1 = "https://vd3.bdstatic.com/mda-pehiqe0dcmd4cry9/sc/cae_h264/1684592473466216903/mda-pehiqe0dcmd4cry9.mp4"

    //第二个，图片地址
    private val imageUrl2 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F5c2fde87-cfa6-4e76-893e-3f032cc41ce5%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1687441411&t=10b8d72201811e966f1404457a5371d2"

    //第三个，视频封面图地址、视频地址
    private val imageUrl3 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F9df09945-fa37-440e-8c02-6f8022453588%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1687673320&t=daae5d3e0df390a663f1b692c4919047"
    private val videoUrl2 = "https://vd4.bdstatic.com/mda-jbppbefbbztvws50/sc/mda-jbppbefbbztvws50.mp4"

    //第四个，图片地址
    private val imageUrl4 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Fa3652914-9074-4c2d-ba91-7677c42a0cdf%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1687673320&t=ace2227ee8b9c0ac0aadaf49cbe25f1e"

    private var player: JzvdStd? = null

    override fun convert(holder: BaseViewHolder, item: String) {

        holder.itemView.run {

            val mList: MutableList<DataBean> = ArrayList()
//        //添加一个视频VideoMultyItem中三个参数，第一个参数是视频的地址，第二个参数2 是视频  1  是图片，第三个参数为图片地址（视频类型时用作视频封面）
            mList.add(DataBean(imageUrl1, videoUrl2, "", 2))
            mList.add(DataBean(imageUrl2, "", "", 1))
            mList.add(DataBean(imageUrl3, "", "", 1))
//            mList.add(DataBean(imageUrl4, "","", 1))

            banner.addBannerLifecycleObserver(context as LifecycleOwner)
                .setAdapter(MultipleTypesAdapter(context, mList))
//                .setIndicator(CircleIndicator(context))
//                .isAutoLoop(true)
//                .setPageTransformer(ScaleInTransformer())
                .addOnPageChangeListener(object : OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                        stopVideo(holder, position)
                    }

                    override fun onPageSelected(position: Int) {
                        stopVideo(holder, position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {

                    }

                })

            when (holder.adapterPosition) {
                0 -> {
                    banner.setPageTransformer(AlphaPageTransformer())
                    banner.setIndicator(BaseIndicator(context))
                }
//                1 -> banner.setPageTransformer(BasePageTransformer(0.6F))
                2 -> {
                    banner.setPageTransformer(DepthPageTransformer())
                    banner.setIndicator(CircleIndicator(context))
                }
                3 -> {
                    banner.setPageTransformer(MZScaleInTransformer())
                    banner.setIndicator(RectangleIndicator(context))
                }
                4 -> {
                    banner.setPageTransformer(RotateDownPageTransformer())
                    banner.setIndicator(RoundLinesIndicator(context))
                }
                5 -> banner.setPageTransformer(RotateUpPageTransformer())
                6 -> banner.setPageTransformer(RotateYTransformer())
                7 -> banner.setPageTransformer(ScaleInTransformer())
                8 -> banner.setPageTransformer(ZoomOutPageTransformer())
                else -> {}
            }
        }
    }

//    override fun getItemCount(): Int {
//        return 1
//    }

    private fun stopVideo(holder: BaseViewHolder, position: Int) {
            holder.itemView.run {
                val viewHolder = banner.adapter.viewHolder
                if (viewHolder is VideoHolder) {
                    val holder: VideoHolder = viewHolder as VideoHolder
                    player = holder.player
                    if (position == 0) {
                        JzvdStd.goOnPlayOnResume()//重新开始
                    } else if (position == 2) {
                        JzvdStd.goOnPlayOnResume()
                    } else {
                        JzvdStd.goOnPlayOnPause()//暂停
                    }
                }
            }
    }
}