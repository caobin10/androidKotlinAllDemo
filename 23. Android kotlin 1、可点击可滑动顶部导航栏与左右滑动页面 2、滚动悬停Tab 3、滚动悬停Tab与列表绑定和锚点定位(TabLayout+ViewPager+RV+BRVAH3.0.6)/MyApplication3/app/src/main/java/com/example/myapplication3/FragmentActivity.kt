package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication3.bean.PageInfo
import com.example.myapplication3.fragment.*
import kotlinx.android.synthetic.main.activity_design_scroll.*

//顶部导航栏与左右滑动页面
class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_scroll)
        viewPager.adapter = MainAdapter(supportFragmentManager)
        tv_header.visibility = View.GONE
        recyclerview.visibility = View.GONE
        tablayout.setupWithViewPager(viewPager)

//        viewPager.setOnTouchListener (View.OnTouchListener{ v, event ->
//            true //设置为true禁止左右滑动,false默认左右滑动
//        })

    }

    class MainAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {

        val pageInfos = arrayOf(
            PageInfo("测试", TestFragment1()),
            PageInfo("测试测试", TestFragment2()),
            PageInfo("测试测", TestFragment3()),
            PageInfo(
                "测试测试测试测试",
                TestFragment4()
            ),
            PageInfo("测试", TestFragment5()),
            PageInfo("测试测试测试", TestFragment6())
        )

        override fun getItem(i: Int): Fragment {
            return pageInfos[i].fragment
        }

        override fun getCount(): Int {
            return pageInfos.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pageInfos[position].title
        }
    }
}