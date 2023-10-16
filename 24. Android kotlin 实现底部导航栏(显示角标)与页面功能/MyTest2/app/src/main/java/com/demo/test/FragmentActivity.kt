package com.demo.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.demo.test.common.ScrollToTop
import com.demo.test.fragment.Fragment1
import com.demo.test.fragment.Fragment2
import com.demo.test.fragment.Fragment3
import com.demo.test.fragment.Fragment4
import kotlinx.android.synthetic.main.fragment.bottomNavigationView
import kotlinx.android.synthetic.main.fragment.viewPager


//非左右滑动页面与底部导航栏
class FragmentActivity : AppCompatActivity() {

    private lateinit var fragments: Map<Int, Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment)
        viewPager.visibility = View.GONE
        fragments = mapOf(
            R.id.fragment1 to createFragment(Fragment1::class.java),
            R.id.fragment2 to createFragment(Fragment2::class.java),
            R.id.fragment3 to createFragment(Fragment3::class.java),
            R.id.fragment4 to createFragment(Fragment4::class.java)
        )

        bottomNavigationView.run {
            setOnNavigationItemSelectedListener { menuItem ->
                showFragment(menuItem.itemId)
                true
            }
            setOnNavigationItemReselectedListener { menuItem ->
                val fragment = fragments.entries.find {
                    it.key == menuItem.itemId
                }?.value
                if (fragment is ScrollToTop) {
                    fragment.scrollToTop()
                }
            }
        }

        showBadgeView(2, 99)

        if (savedInstanceState == null) {
            val initialItemId = R.id.fragment1
            bottomNavigationView.selectedItemId = initialItemId
            showFragment(initialItemId)
        }
    }

    private fun createFragment(clazz: Class<out Fragment>): Fragment {
        var fragment = supportFragmentManager.fragments.find { it.javaClass == clazz }
        if (fragment == null) {
            fragment = when (clazz) {
                Fragment1::class.java -> Fragment1.newInstance()
                Fragment2::class.java -> Fragment2.newInstance()
                Fragment3::class.java -> Fragment3.newInstance()
                Fragment4::class.java -> Fragment4.newInstance()
                else ->
                    throw IllegalArgumentException("argument ${clazz.simpleName} is illegal")
            }
        }
        return fragment
    }

    private fun showFragment(menuItemId: Int) {
        val currentFragment = supportFragmentManager.fragments.find {
            it.isVisible && it in fragments.values
        }
        val targetFragment = fragments.entries.find {
            it.key == menuItemId
        }?.value
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let {
                if (it.isVisible)
                    hide(it)
            }
            targetFragment?.let {
                if (it.isAdded)
                    show(it)
                else
                    add(R.id.fl, it)
            }
        }.commit()
    }

    /**
     * BottomNavigationView显示角标
     * @param viewIndex tab索引
     * @param showNumber 显示的数字，小于等于0是将不显示
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        bottomNavigationView.getOrCreateBadge(R.id.fragment3).apply {
//            backgroundColor = ContextCompat.getColor(this@FragmentActivity, R.color.red)
            badgeTextColor = ContextCompat.getColor(this@FragmentActivity, R.color.white)
            number = showNumber
        }

//        val menuItemId = bottomNavigationView.menu.getItem(viewIndex).itemId
//        val badge : BadgeDrawable= bottomNavigationView.getOrCreateBadge(menuItemId)
//        badge.number = showNumber
    }
}