package com.example.myapplication3.activity.node

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.entity.TestModel
import kotlinx.android.synthetic.main.activity_sticky_list.*
import java.util.*

class StickyActivity : AppCompatActivity() {

    private var list = ArrayList<TestModel>()
    private var mAdapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_list)

        for (i in 0..7) {
            val model = TestModel()
            model.info = "一级菜单$i"
            model.isTitle = true
            list.add(model)
            for (j in 0..2) {
                val model = TestModel()
                model.info = "二级菜单$j"
                list.add(model)
            }
        }
        mAdapter = RvAdapter()

        val layoutManager = LinearLayoutManager(this@StickyActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listview.layoutManager = layoutManager

        listview.adapter = mAdapter
        title_text.text = list[0].info

        mAdapter!!.setList(list)

//        listview.setOnItemClickListener { parent, view, position, l ->
//            Toast.makeText(this@StickyActivity2, list[position].info, Toast.LENGTH_SHORT).show()
//        }

        listview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                val topPosition = layoutManager.findLastCompletelyVisibleItemPosition()

                val MylayoutManager = recyclerView.layoutManager as LinearLayoutManager


                val firstVisibleItem = MylayoutManager.findFirstVisibleItemPosition()
//                val lastVisible = MylayoutManager.findLastVisibleItemPosition()
//                val visibleItemCount = lastVisible - firstVisibleItem

                val visibleItemCount = recyclerView.childCount

                if (firstVisibleItem < list.size && visibleItemCount > 0) {

                    val params = title_text.layoutParams as RelativeLayout.LayoutParams

                    val itemView: View = recyclerView.getChildAt(1)

                    var top = 0

                    if (list[firstVisibleItem + 1].isTitle) {
                        top = itemView.top - itemView.height
                    }
//                    title_text.text = list[firstVisibleItem].info.substring(0, 1).toUpperCase()

                    if (list[firstVisibleItem].info.subSequence(0, 1) == "一") {
                        title_text.text = list[firstVisibleItem].info
                    }

                    params.setMargins(0, top, 0, 0)
                    title_text.layoutParams = params
                }
            }
        })
    }

    fun goBack(v: View) {
        finish()
    }
}
