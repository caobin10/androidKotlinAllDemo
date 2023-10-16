package com.example.myapplication3

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication3.bean.PageInfo
import com.example.myapplication3.fragment.TestFragment1
import com.example.myapplication3.fragment.TestFragment2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_main.indicator
import kotlinx.android.synthetic.main.activity_main.tablayout
import kotlinx.android.synthetic.main.activity_main.viewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        //TabLayout添加自定义分割线并且可以修改分割线高度
        val linearLayout = tablayout.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical)
//        linearLayout.dividerPadding = 50
        linearLayout.dividerPadding = 23

        viewPager.adapter = MainAdapter(supportFragmentManager)
        viewPager.currentItem = 0 //当前值设置为1才能仅显示一个默认视图
        tablayout.setupWithViewPager(viewPager)

        //****新代码**
        tablayout.getTabAt(0)?.setCustomView(R.layout.text)
        val toMyTextView1 = tablayout.getTabAt(0)?.customView?.findViewById<TextView>(R.id.textView)
        toMyTextView1?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        toMyTextView1?.textSize = 15F
        toMyTextView1?.setTextColor(resources.getColor(R.color.black))
        toMyTextView1?.text = "热销好货"

        tablayout.getTabAt(1)?.setCustomView(R.layout.text)
        val toMyTextView2 = tablayout.getTabAt(1)?.customView?.findViewById<TextView>(R.id.textView)
        toMyTextView2?.textSize = 12F
        toMyTextView2?.setTextColor(resources.getColor(R.color.gray))
        toMyTextView2?.text = "图文直播"
        //****

        indicator.setWithViewPager(viewPager)

        tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.textView)?.isSelected  = true
                val tv = tab?.customView?.findViewById<TextView>(R.id.textView)
                tv?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)//加粗
                tv?.textSize = 15F//直接用setTextSize(15F)也一样
                tv?.setTextColor(resources.getColor(R.color.black))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.textView)?.isSelected = false
                val tv = tab?.customView?.findViewById<TextView>(R.id.textView)
                tv?.textSize = 12F
                tv?.setTextColor(resources.getColor(R.color.gray))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.i("Tag","onTabReselected = ${tab?.position}")
            }
        })
    }

    class MainAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {

        private val pageInfos = arrayOf(
            PageInfo(TestFragment1()),
            PageInfo(TestFragment2()),
        )

        override fun getItem(i: Int): Fragment {
            return pageInfos[i].fragment
        }

        override fun getCount(): Int {
            return pageInfos.size
        }

//        override fun getPageTitle(position: Int): CharSequence? {
//            return pageInfos[position].title
//        }
    }
}