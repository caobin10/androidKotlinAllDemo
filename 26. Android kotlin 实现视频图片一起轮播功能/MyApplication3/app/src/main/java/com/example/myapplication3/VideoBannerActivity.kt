package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
//import cn.jzvd.Jzvd
//import cn.jzvd.JzvdStd

class VideoBannerActivity : AppCompatActivity() {

//    private val mAdapter by lazy {
//        BannerAdapter().apply {
//
//        }
//    }
//
//    val url = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4"
//    val coverUrl =
//        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F70c03ac511079c42b2ecddef0ff4444f846de67ebf58a-ZQ9m0f_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641973590&t=2c23fb6d6b200160666f0ccd81d7368a"
//
//    private var mLayoutManager: LinearLayoutManager? = null
//    private var mSnapHelper: PagerSnapHelper? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_rv_banner)
//        initBanner()
//    }
//
//    private fun initBanner() {
//        val mList: MutableList<VideoMultyItem> = ArrayList()
//
//        //添加一个视频VideoMultyItem中三个参数，第一个参数是视频的地址，第二个参数1 是视频  2  是商品，第三个参数为图片地址（视频类型时用作视频封面）
//        mList.add(
//            VideoMultyItem(
//                null,
//                2,
//                coverUrl
//            )
//        )
//        mList.add(
//            VideoMultyItem(
//                null,
//                2,
//                coverUrl
//            )
//        )
//        mList.add(
//            VideoMultyItem(
//                null,
//                2,
//                coverUrl
//            )
//        )
//
//        mLayoutManager = LinearLayoutManager(this@VideoBannerActivity)
//        mLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
//        recyclerView.layoutManager = mLayoutManager
//
////        recyclerView.setHasFixedSize(true)
//
//        recyclerView.adapter = mAdapter
//        mAdapter.setList(mList)
//
////        recyclerView.scrollToPosition(mList.size * 10)
//
//        mSnapHelper = PagerSnapHelper() //类似于ViewPager，用LinearSnapHelper也可以
////        shop_banner.onFlingListener = null
//        mSnapHelper!!.attachToRecyclerView(recyclerView)
//        banner_num.text = "1/${mList.size}"
//
////        val scheduledExecutorService = Executors.newScheduledThreadPool(1)
////        scheduledExecutorService.scheduleAtFixedRate(Runnable {
////            shop_banner.smoothScrollToPosition(mLayoutManager!!.findFirstVisibleItemPosition() + 1)
////        }, 2000, 2000, TimeUnit.MILLISECONDS)
//
//        recyclerView.addOnScrollListener(object : OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_IDLE -> autoPlay(recyclerView) //停止滚动
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {} //拖动
//                    RecyclerView.SCROLL_STATE_SETTLING -> {
//                        Jzvd.releaseAllVideos()
//                    } //惯性滑动
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val position = layoutManager.findFirstVisibleItemPosition()
//                banner_num.text = "${position + 1}/${mList.size}"
//            }
//        })
//    }
//
//    private fun autoPlay(recyclerView: RecyclerView) {
//        val view = mSnapHelper!!.findSnapView(mLayoutManager)
//        if (view != null) {
//            if (view is RelativeLayout) {
//                Jzvd.releaseAllVideos()
//            } else {
//                val viewHolder = recyclerView.getChildViewHolder(view) as BaseViewHolder
//                if (viewHolder != null) {
//                    val myVideoPlayer: JzvdStd = viewHolder.getView(R.id.playerx)
//                    myVideoPlayer.startVideo()
//                }
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        JzvdStd.releaseAllVideos()
//    }
}