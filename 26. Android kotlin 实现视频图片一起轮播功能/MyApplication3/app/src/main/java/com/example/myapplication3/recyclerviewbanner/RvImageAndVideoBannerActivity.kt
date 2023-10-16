package com.example.myapplication3.recyclerviewbanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.example.myapplication3.recyclerviewbanner.adapter.RvBannerAdapter
import kotlinx.android.synthetic.main.activity_rv_banner.*

class RvImageAndVideoBannerActivity :AppCompatActivity(){

    private val mAdapter by lazy {
        RvBannerAdapter().apply {

        }
    }

//    private var mJzvdStd:JzvdStd?=null

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

    private var mLayoutManager: LinearLayoutManager? = null
    private var mSnapHelper: PagerSnapHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_banner)
        initBanner()
    }

    private fun initBanner() {
        val mList: MutableList<DataBean> = ArrayList()
        //添加一个视频VideoMultyItem中三个参数，第一个参数是视频的地址，第二个参数1 是视频  2  是商品，第三个参数为图片地址（视频类型时用作视频封面）
        mList.add(
            DataBean(
                imageUrl1,
                videoUrl2,
                "",
                1
            )
        )
        mList.add(DataBean(imageUrl2, "", "", 2))
        mList.add(DataBean(imageUrl3, "", "", 2))
//        mList.add(DataBean(imageUrl4, "","",2))

        mLayoutManager = LinearLayoutManager(this@RvImageAndVideoBannerActivity)
        mLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = mLayoutManager

//        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = mAdapter
        mAdapter.setList(mList)
        mSnapHelper = PagerSnapHelper() //类似于ViewPager，用LinearSnapHelper也可以
//        shop_banner.onFlingListener = null
        mSnapHelper!!.attachToRecyclerView(recyclerView)
        banner_num.text = "1/${mList.size}"

//        val scheduledExecutorService = Executors.newScheduledThreadPool(1)
//        scheduledExecutorService.scheduleAtFixedRate(Runnable {
//            recyclerView.smoothScrollToPosition(mLayoutManager!!.findFirstVisibleItemPosition() + 1)
//        }, 2000, 2000, TimeUnit.MILLISECONDS)

//        mAdapter.setItemSelectedCallBack(object : RvBannerAdapter.ItemSelectedCallBack {
//            override fun convert(holder: BaseViewHolder?) {
//                holder!!.itemView.run {
//                    mJzvdStd =  player
//                }
//            }
//        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_IDLE -> autoPlay(recyclerView) //停止滚动
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {} //拖动
//                    RecyclerView.SCROLL_STATE_SETTLING -> {
//                        JzvdStd.releaseAllVideos()
//                    } //惯性滑动
//                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstVisibleItemPosition()
                banner_num.text = "${position + 1}/${mList.size}"
                //第一第三都是视频文件，其他都为图片文件，第一页的时候开始播放，其他页暂停
                if (position == 0) {
                    JzvdStd.goOnPlayOnResume()//重新开始
                } else if (position == 2) {
                    JzvdStd.goOnPlayOnResume()
                } else {
                    JzvdStd.goOnPlayOnPause()//暂停
                }
            }
        })
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