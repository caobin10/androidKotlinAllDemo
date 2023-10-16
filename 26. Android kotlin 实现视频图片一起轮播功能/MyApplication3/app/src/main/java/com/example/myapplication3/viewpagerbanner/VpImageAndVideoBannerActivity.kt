package com.example.myapplication3.viewpagerbanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.example.myapplication3.viewpagerbanner.adapter.VpBannerAdapter
import com.example.myapplication3.viewpagerbanner.viewholder.ViewSwitcherHelper
import kotlinx.android.synthetic.main.activity_viewpager_banner.*

class VpImageAndVideoBannerActivity : AppCompatActivity() {

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

    private var mViewSwitchHelper: ViewSwitcherHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_banner)
        initViewPager()
        initData()
    }

    private fun initViewPager() {
        mViewSwitchHelper = ViewSwitcherHelper(
            this@VpImageAndVideoBannerActivity,
            del,//放底部小圆点控件
            resources.getDrawable(R.drawable.shape_point_select),
            resources.getDrawable(R.drawable.shape_point_unselect)
        )
        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mViewSwitchHelper!!.setCurrent(position)
                //第一第三都是视频文件，其他都为图片文件，第一页的时候开始播放，其他页暂停
                if (position == 0) {
                    JzvdStd.goOnPlayOnResume()//重新开始
                } else if (position == 2) {
                    JzvdStd.goOnPlayOnResume()
                } else {
                    JzvdStd.goOnPlayOnPause()//暂停
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun initData() {
        val mList: MutableList<DataBean> = ArrayList()
        mList.add(
            DataBean(
                imageUrl1,
                videoUrl2,
                "",
                1
            )
        )//1代表视频这张图为视频缩略图
        mList.add(DataBean(imageUrl2, "", 2))//2代表页图片文件
        mList.add(DataBean(imageUrl3, "", "", 2))
//        mList.add(DataBean(imageUrl4,"",2))
        setBannerView(mList)
    }

    /**
     * 顶部轮播图
     */
    private fun setBannerView(datas: MutableList<DataBean>) {
        val mViewList: MutableList<View> = ArrayList()
        //区分图片和视频布局
        for (data in datas) {
            if (data.viewType == 1) {//1 为视频 以下布局中是视频布局
                val view = LayoutInflater.from(this).inflate(R.layout.banner_video, null)
                val videoPlayer = view.findViewById(R.id.player) as JzvdStd
                videoPlayer.setUp(data.videoUrl,"")
                Glide.with(this@VpImageAndVideoBannerActivity).load(data.imageUrl).into(videoPlayer.posterImageView)
                mViewList.add(view)

            } else if (data.viewType == 2){//以下布局中为图片布局
                val view = LayoutInflater.from(this).inflate(R.layout.banner_image, null)
                val imageview = view.findViewById(R.id.image) as ImageView
                Glide.with(this@VpImageAndVideoBannerActivity)
                    .load(data.imageUrl)
//                    .centerCrop()
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageview)
                mViewList.add(view)
            }
        }
        viewPager.adapter = VpBannerAdapter(mViewList);
        mViewSwitchHelper!!.setViewSwitcherTip(mViewList.size, 0)
    }

    override fun onPause() {
        super.onPause()
        JzvdStd.goOnPlayOnPause()
    }

    override fun onDestroy() { //破坏
        super.onDestroy()
        JzvdStd.releaseAllVideos()
    }
}