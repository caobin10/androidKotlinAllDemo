package com.noober.floatmenu

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.noober.menu.FloatMenu
import android.widget.Toast
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.example.myapplication3.adapter.RvAdapter
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity(), OnItemLongClickListener {

    private val point = Point()

    private val mAdapter by lazy {
        RvAdapter().apply {
            setOnItemLongClickListener(this@MainActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val itemList: MutableList<String> = ArrayList()
        for (i in 0..19) {
            itemList.add("菜单$i")
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(itemList)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            point.x = ev.rawX.toInt()
            point.y = ev.rawY.toInt()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int): Boolean {
        val floatMenu = FloatMenu(this@MainActivity)
        floatMenu.items("菜单1", "菜单2", "菜单3")
        floatMenu.show(point)
        floatMenu.setOnItemClickListener { v, position ->
            Toast.makeText(this@MainActivity,"$position", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
