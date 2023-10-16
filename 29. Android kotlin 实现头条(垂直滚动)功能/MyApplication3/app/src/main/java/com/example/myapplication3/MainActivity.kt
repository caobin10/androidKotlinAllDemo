package com.example.myapplication3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.upView

class MainActivity : AppCompatActivity() {

    var data: MutableList<String> = ArrayList()
    var views: MutableList<View> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    /**
     * 初始化界面程序
     */
    private fun initView() {
        setView()
        upView?.setViews(views)
        upView.setOnClickListener {
            val currentChild = upView.displayedChild
            val currentContent = data[currentChild]
            Toast.makeText(this@MainActivity,currentContent,Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private fun setView() {

        for (i in 0 until data.size){
            //设置滚动的单个布局
            val moreView = LayoutInflater.from(this).inflate(R.layout.item_view, null) as LinearLayout
            //初始化布局的控件
            val tv1 = moreView.findViewById<View>(R.id.tv1) as TextView
//            val tv2 = moreView.findViewById<View>(R.id.tv2) as TextView
            moreView.findViewById<View>(R.id.rl2).visibility = View.GONE
            //进行对控件赋值
            tv1.text = data[i]
            //添加到循环滚动数组里面去
            views.add(moreView)
        }

//        for (i in 0 until data.size step 2){
//            //设置滚动的单个布局
//            val moreView = LayoutInflater.from(this).inflate(R.layout.item_view, null) as LinearLayout
//            //初始化布局的控件
//            val tv1 = moreView.findViewById<View>(R.id.tv1) as TextView
//            val tv2 = moreView.findViewById<View>(R.id.tv2) as TextView
//            //进行对控件赋值
//            tv1.text = data[i]
//            if (data.size > i + 1) {
//                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
//                tv2.text = data[i + 1]
//            } else {
//                moreView.findViewById<View>(R.id.rl2).visibility = View.GONE
//            }
//            //添加到循环滚动数组里面去
//            views.add(moreView)
//        }
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        data = ArrayList()
        data.add("测试1测试1")
        data.add("测试2测试2测试2测试2")
        data.add("测试3测试3测试3测试3测试3测试3测试3测试3")
    }
}