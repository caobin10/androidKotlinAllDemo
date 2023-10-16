package com.example.myapplication3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.util.GuideViewUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import java.util.*


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val mAdapter by lazy {
        RvAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }
    }

    private val list: MutableList<String> = ArrayList()

    private var s: String? = null
    val item_position = "点击位置了"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        for (i in 0..29) {
            list.add("菜单$i")
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        recyclerView.isNestedScrollingEnabled = true
        mAdapter.setList(list)

        mAdapter.setItemSelectedCallBack(object : RvAdapter.ItemSelectedCallBack {

            override fun convert(holder: BaseViewHolder?, position: Int) {

                holder!!.itemView.run {
                    tv_content.text = mAdapter.getItem(position)
                }

                if (s != null && s.equals(mAdapter.getItem(position))) {

                    val finalView = holder.itemView
                    finalView.post(Runnable {
                        //先等待页面选中后，在执行 避免高亮View 不显示
                        val task: TimerTask = object : TimerTask() {
                            override fun run() {
                                //需运行主线程中
                                runOnUiThread(Runnable {
                                    GuideViewUtil.showGuideView(this@MainActivity,
                                        finalView,
                                        "$item_position:$position",
                                        object : GuideViewUtil.OnDismissCallback {
                                            override fun onDismiss() {
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    mAdapter.getItem(position),
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                val layoutManager: LinearLayoutManager = object :
                                                    LinearLayoutManager(this@MainActivity) {
                                                    override fun canScrollVertically(): Boolean {
                                                        return true
                                                    }
                                                }
                                                recyclerView.layoutManager = layoutManager
                                            }
                                        })
                                })
                            }
                        }
                        val timer = Timer()
                        timer.schedule(task, 300)
                        s = null
                    })
                }
            }
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = mAdapter.getItem(position)
        s = item
        mAdapter.notifyDataSetChanged()
    }
}