package com.example.myapplication3

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_grid.*

class LinearLayoutActivity : AppCompatActivity(),OnItemClickListener,OnItemDragListener, OnItemSwipeListener {

    var list = mutableListOf<MutableMap<Int, String>>()

    private val mAdapter by lazy { RvAdapter().apply { setOnItemClickListener(this@LinearLayoutActivity) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        initView()
        initData()
    }

    private fun initView() {
        mAdapter.draggableModule.isDragEnabled = true
        mAdapter.draggableModule.isSwipeEnabled = true
        mAdapter.draggableModule.setOnItemDragListener(this@LinearLayoutActivity)
        mAdapter.draggableModule.setOnItemSwipeListener(this@LinearLayoutActivity)
        //列表拖拽只合适上下，左右上下自己喜欢就即可
        mAdapter.draggableModule.itemTouchHelperCallback.setDragMoveFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN)
        //列表侧滑删除只合适左右
        mAdapter.draggableModule.itemTouchHelperCallback.setSwipeMoveFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        //设置列表
        recycleView.layoutManager = LinearLayoutManager(this)
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

    override fun onItemDragMoving(
        source: RecyclerView.ViewHolder?,
        from: Int,
        target: RecyclerView.ViewHolder?,
        to: Int
    ) {

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



    override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        Toast.makeText(this@LinearLayoutActivity,"继续向左滑动即可删除第" + pos + "个位置的item", Toast.LENGTH_SHORT).show()
        val holder = viewHolder as BaseViewHolder
    }

    override fun clearView(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        val holder = viewHolder as BaseViewHolder
    }

    override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        Toast.makeText(this@LinearLayoutActivity,"删除了第" + pos + "个位置的item哦",Toast.LENGTH_SHORT).show()
    }

    override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {
        canvas!!.drawColor(ContextCompat.getColor(this@LinearLayoutActivity,R.color.teal_200))
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}