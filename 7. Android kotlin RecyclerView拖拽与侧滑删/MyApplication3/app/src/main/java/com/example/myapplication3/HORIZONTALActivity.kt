package com.example.myapplication3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_grid.recycleView

class HORIZONTALActivity : AppCompatActivity(), OnItemClickListener, OnItemDragListener{
    var list = mutableListOf<MutableMap<Int, String>>()

    private val mAdapter by lazy { RvAdapter().apply { setOnItemClickListener(this@HORIZONTALActivity) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        initView()
        initData()
    }

    private fun initView() {
        mAdapter.draggableModule.isDragEnabled = true
        mAdapter.draggableModule.setOnItemDragListener(this@HORIZONTALActivity)
        //列表拖拽只合适上下，左右上下自己喜欢就即可
        mAdapter.draggableModule.itemTouchHelperCallback.setDragMoveFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        //设置列表
        val layoutManager = LinearLayoutManager(this)
        //设置方向为横向，不设置默认为纵向(上下方向)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycleView.layoutManager = layoutManager
        recycleView.adapter = mAdapter
    }

    private fun initData() {
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test1)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test2)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test3)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test4)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test5)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test6)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test7)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test8)))
        list.add(mutableMapOf<Int, String>(R.mipmap.ic_c to getString(R.string.test9)))
        mAdapter.setList(list)
    }

    override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        val holder = viewHolder as BaseViewHolder

        // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
//        val startColor = Color.WHITE
//        val endColor = Color.rgb(245,245,245)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val v = ValueAnimator.ofArgb(startColor, endColor)
//            v.addUpdateListener(AnimatorUpdateListener { animation ->
//                holder.itemView.setBackgroundColor(
//                    animation.animatedValue as Int
//                )
//            })
//            v.duration = 300
//            v.start()
//        }
    }

    override fun onItemDragMoving(source: RecyclerView.ViewHolder?, from: Int, target: RecyclerView.ViewHolder?, to: Int) {

    }

    override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        val holder = viewHolder as BaseViewHolder

        // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
//        val startColor = Color.rgb(245, 245, 245)
//        val endColor = Color.WHITE
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val v = ValueAnimator.ofArgb(startColor, endColor)
//            v.addUpdateListener(AnimatorUpdateListener { animation ->
//                holder.itemView.setBackgroundColor(
//                    animation.animatedValue as Int
//                )
//            })
//            v.duration = 300
//            v.start()
//        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}

