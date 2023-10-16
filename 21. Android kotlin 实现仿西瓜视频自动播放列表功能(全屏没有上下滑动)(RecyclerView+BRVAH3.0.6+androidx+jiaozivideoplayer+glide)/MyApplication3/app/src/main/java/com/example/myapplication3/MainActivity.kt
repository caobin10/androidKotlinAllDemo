package com.example.myapplication3

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.example.myapplication3.adapter.PlayerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        PlayerAdapter().apply {

        }
    }

    //视频
    private val videoUrl = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4"
//    private val videoUrl = "https://vd4.bdstatic.com/mda-jbppbefbbztvws50/sc/mda-jbppbefbbztvws50.mp4"
    //封面图
    private val coverImageUrl =
        "https://img1.baidu.com/it/u=265818744,2982786856&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"

    private val mList: MutableList<MyPlayer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        for (i in 0..19) {
            val video = MyPlayer(videoUrl, coverImageUrl)
            mList.add(video);
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter
        mAdapter.setList(mList)

        recyclerview.addOnScrollListener(AutoPlayScrollListener(this@MainActivity))

    }

    /**
     * 监听recycleView滑动状态，
     * 自动播放可见区域内的第一个视频
     */
    private class AutoPlayScrollListener(private val context: Context) : RecyclerView.OnScrollListener() {
        private var firstVisibleItem = 0
        private var lastVisibleItem = 0
        private var visibleCount = 0

        /**
         * 被处理的视频状态标签
         */
        private enum class VideoTagEnum {
            /**
             * 自动播放视频
             */
            TAG_AUTO_PLAY_VIDEO,

            /**
             * 暂停视频
             */
            TAG_PAUSE_VIDEO
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> autoPlayVideo(
                    recyclerView,
                    VideoTagEnum.TAG_AUTO_PLAY_VIDEO
                )
                RecyclerView.SCROLL_STATE_DRAGGING, RecyclerView.SCROLL_STATE_SETTLING -> {}
                else -> {}
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager
            if (layoutManager is LinearLayoutManager) {
                val linearManager = layoutManager
                firstVisibleItem = linearManager.findFirstVisibleItemPosition()
                lastVisibleItem = linearManager.findLastVisibleItemPosition()
                visibleCount = lastVisibleItem - firstVisibleItem
            }
        }

        /**
         * 循环遍历可见区域的播放器
         * 然后通过 getLocalVisibleRect(rect)方法计算出哪个播放器完全显示出来
         * @param recyclerView
         * @param handleVideoTag 视频需要进行状态
         */
        private fun autoPlayVideo(recyclerView: RecyclerView?, handleVideoTag: VideoTagEnum) {
            for (i in 0 until visibleCount) {
                if (recyclerView != null && recyclerView.getChildAt(i) != null && recyclerView.getChildAt(i).findViewById<MyJzvdStd>(R.id.videoplayer) != null) {
                    val homeGSYVideoPlayer = recyclerView.getChildAt(i).findViewById<MyJzvdStd>(R.id.videoplayer) as MyJzvdStd
                    val rect = Rect()
                    homeGSYVideoPlayer.getLocalVisibleRect(rect)
                    val videoheight = homeGSYVideoPlayer.height
                    if (rect.top == 0 && rect.bottom == videoheight) {
                        handleVideo(handleVideoTag, homeGSYVideoPlayer)
                        // 跳出循环，只处理可见区域内的第一个播放器
                        break
                    }
                }
            }
        }

        /**
         * 视频状态处理
         *
         * @param handleVideoTag     视频需要进行状态
         * @param homeGSYVideoPlayer JZVideoPlayer播放器
         */
        private fun handleVideo(handleVideoTag: VideoTagEnum, homeGSYVideoPlayer: MyJzvdStd) {
            when (handleVideoTag) {
                VideoTagEnum.TAG_AUTO_PLAY_VIDEO -> if (homeGSYVideoPlayer.state !== Jzvd.STATE_PLAYING) {
                    // 进行播放
                    homeGSYVideoPlayer.startVideo()
                }
                VideoTagEnum.TAG_PAUSE_VIDEO -> if (homeGSYVideoPlayer.state !== Jzvd.STATE_PAUSE) {
                    // 模拟点击,暂停视频
                    homeGSYVideoPlayer.startButton.performClick()
                }
                else -> {}
            }
        }
    }

    /**
     * 拦截返回键 返回不退出程序
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (Jzvd.backPress()) {
            return true
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                moveTaskToBack(true)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}