package com.example.myapplication3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_design_scroll.*
import kotlinx.android.synthetic.main.rv_item.view.*

//1、TabLayout与RecyclerView绑定和锚点定位，2、滚动Tab悬停
class AnchorAndTabScrollActivity : AppCompatActivity() {

    private val mAdapter2 by lazy {
        RvAdapter().apply {
//            setOnItemClickListener(this@AnchorActivity)
        }
    }

    //用于recyclerView滑动到指定的位置
    private var canScroll = false
    private var scrollToPosition = 0

    private val tabTxt = arrayOf("测试0", "测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7")

    //判读是否是recyclerView主动引起的滑动，true- 是，false- 否，由tablayout引起的
    private var isRecyclerScroll = false

    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private var lastPos = 0
    private val mList: MutableList<String> = ArrayList()

    private var statuBarHeight = 0 //状态栏高度

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_scroll)
        init()
    }

    private fun init() {
//        tv_header.visibility = View.GONE
        nestedScrollView.visibility = View.GONE
        for (element in tabTxt) {
            //tablayout设置标签
            tablayout.addTab(tablayout.newTab().setText(element))
            //
            mList.add(element)
        }
        val layoutManager = LinearLayoutManager(this@AnchorAndTabScrollActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter2
        mAdapter2.setList(mList)

        tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                Log.i("tablayout", "pos => ${tab!!.position}")

                //点击标签，使recyclerView滑动，isRecyclerScroll置false
                val pos = tab!!.position
                moveToPosition(layoutManager, recyclerview, pos)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        recyclerview.setOnTouchListener(OnTouchListener { v, event ->
            //当滑动由recyclerView触发时，isRecyclerScroll 置true
            if (event!!.action == MotionEvent.ACTION_MOVE) {
                isRecyclerScroll = true
            }
            false //默认左右滑动，设置为true禁止左右滑动
        })

        mAdapter2.setLastItemViewCallBack(object : RvAdapter.LastItemViewCallBack {
            override fun convert(holder: BaseViewHolder?) {
                holder!!.itemView.run {

                    //判断最后一个item的view
                    if (holder!!.adapterPosition == tabTxt.size - 1) {
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        //屏幕高度
                        val screenH: Int = getScreenHeight(this@AnchorAndTabScrollActivity)
                        //状态栏
                        window.decorView.setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener { v: View?, insets: WindowInsets ->
//                            val statuBarHeight = insets.stableInsetTop
                            statuBarHeight = insets.stableInsetTop
                            insets
                        })
                        //滚动悬停Tab高度
                        val tabH: Int = tablayout.height
                        val lastH = screenH - statuBarHeight - tabH

                        params.height = lastH
                        ll_layout.layoutParams = params
                        ll_layout.setBackgroundColor(resources.getColor(R.color.purple_200))
                    }
                }
            }
        })

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (canScroll) {
                    canScroll = false
                    moveToPosition(layoutManager, recyclerView, scrollToPosition)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    val position = layoutManager.findFirstVisibleItemPosition()
                    if (lastPos != position) {
                        tablayout.setScrollPosition(position, 0F, true)
                    }
                    lastPos = position
                }
            }
        })
    }

    private fun moveToPosition(layoutManager: LinearLayoutManager, mRecyclerView: RecyclerView?, position: Int) {
        // 第一个可见的view的位置
        val firstItem = layoutManager.findFirstVisibleItemPosition()
        // 最后一个可见的view的位置
        val lastItem = layoutManager.findLastVisibleItemPosition()
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView!!.smoothScrollToPosition(position) //向下滑到下面
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置

//            val top = mRecyclerView!!.getChildAt(position - firstItem).top
            val top = mRecyclerView!!.layoutManager!!.findViewByPosition(position)!!.top

            mRecyclerView.smoothScrollBy(0, top) //向上滑到下面
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView!!.smoothScrollToPosition(position)
            scrollToPosition = position
            canScroll = true
        }
    }

    private fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }
}