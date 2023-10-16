package com.demo.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.demo.test.pager.MyPagerAdapter
import com.demo.test.fragment.Fragment2
import com.demo.test.fragment.Fragment3
import com.demo.test.fragment.Fragment4
import com.demo.test.fragment.Fragment1
import kotlinx.android.synthetic.main.fragment.*

//左右滑动页面与底部导航栏
class FragmentAndPageSlideActivity : AppCompatActivity() {

    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment)
        fl.visibility = View.GONE
        initData()
        val adapter = MyPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter
        //页面更改监听
        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigationView.selectedItemId = R.id.fragment1
                    1 -> bottomNavigationView.selectedItemId = R.id.fragment2
                    2 -> bottomNavigationView.selectedItemId = R.id.fragment3
                    3 -> bottomNavigationView.selectedItemId = R.id.fragment4
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        //图标选择监听
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment1 -> viewPager.currentItem = 0
                R.id.fragment2 -> viewPager.currentItem = 1
                R.id.fragment3 -> viewPager.currentItem = 2
                R.id.fragment4 -> viewPager.currentItem = 3
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun initData() {
        val fragment1 = Fragment1.newInstance()
        fragmentList.add(fragment1)
        val fragment2 = Fragment2.newInstance()
        fragmentList.add(fragment2)
        val fragment3 = Fragment3.newInstance()
        fragmentList.add(fragment3)
        val fragment4 = Fragment4.newInstance()
        fragmentList.add(fragment4)
    }
}