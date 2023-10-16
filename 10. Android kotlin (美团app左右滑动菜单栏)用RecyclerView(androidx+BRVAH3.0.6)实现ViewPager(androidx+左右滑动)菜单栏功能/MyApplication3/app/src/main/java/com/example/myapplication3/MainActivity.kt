package com.example.myapplication3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.adapter.ViewPagerAdapter
import com.example.myapplication3.bean.MenuItem
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    private val titles = arrayOf(
        "外卖",
        "美餐",
        "酒店/民宿",
        "休闲/玩乐",
        "电影/演出",
        "打车",
        "医疗/牙科",
        "超市/便利店",
        "买药",
        "火车票/机票",
        "美团优选",
        "兔费领水果",
        "天天领现金",
        "手机充值",
        "跑腿",
        "按摩/足疗",
        "洗浴/汗蒸",
        "KTV",
        "周边游/旅游",
        "结婚/摄影",
        "生活服务",
        "宠物",
        "免费领福利",
        "养车/用车",
        "学习培训",
        "医学美容",
        "理发/男士",
        "健身/运动",
        "红包签到",
        "逛街/商场",
        "骑车",
        "景点/门票",
        "送菜上门",
        "汽车票",
        "酒吧",
        "电玩游戏厅",
        "密室",
        "借钱",
        "家居/装修",
        "点我就有钱",
        "玩一玩",
        "生活缴费",
        "新奇体验",
        "母婴服务",
        "充电宝"
    )
    private var mPager: ViewPager? = null
    private var mPagerList: MutableList<View>? = null
    private var mDatas: MutableList<MenuItem>? = null
    private var mLlDot: LinearLayout? = null
    private var inflater: LayoutInflater? = null

    /**
     * 总的页数
     */
    private var pageCount = 0

    /**
     * 每一页显示的个数
     */
    private val pageSize = 15

    /**
     * 当前显示的是第几页
     */
    private var curIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPager = findViewById<View>(R.id.viewpager) as ViewPager
        mLlDot = findViewById<View>(R.id.ll_dot) as LinearLayout

        //初始化数据源
        initDatas()
        inflater = LayoutInflater.from(this@MainActivity)

        //总的页数=总数/每页数量，并取整
        pageCount = ceil(mDatas!!.size * 1.0 / pageSize).toInt()

        mPagerList = ArrayList()
        for (i in 0 until pageCount) {

            // 每个页面都是inflate出一个新实例
            val mRecyclerView = inflater!!.inflate(R.layout.recyclerview, mPager, false) as RecyclerView

            val layoutManager = StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            mRecyclerView.layoutManager = layoutManager

            val mAdapter = RvAdapter(mDatas!!,i, pageSize)

            mRecyclerView.adapter = mAdapter

            mAdapter.setList(mDatas)

            mPagerList!!.add(mRecyclerView)

            mAdapter.setOnItemClickListener { adapter, view, position ->
                Toast.makeText(this@MainActivity, "$position", Toast.LENGTH_SHORT).show()
            }
        }

        //设置适配器
        mPager!!.adapter = ViewPagerAdapter(mPagerList)

        //设置圆点
        setDotLayout()
    }

    /**
     * 初始化数据源
     */
    private fun initDatas() {
        mDatas = ArrayList()
        for (i in titles.indices) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
//            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            val imageId = resources.getIdentifier("ic_c", "mipmap", packageName)
            mDatas!!.add(MenuItem(titles[i], imageId))
        }
    }

    /**
     * 设置圆点
     */
    private fun setDotLayout() {
        for (i in 0 until pageCount) {
            mLlDot!!.addView(inflater!!.inflate(R.layout.dotview, null))
        }
        // 默认显示第一页
        mLlDot!!.getChildAt(0).isSelected = true
        mPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // 取消原点选中
                mLlDot!!.getChildAt(curIndex).isSelected = false
                // 原点选中
                mLlDot!!.getChildAt(position).isSelected = true
                curIndex = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}

