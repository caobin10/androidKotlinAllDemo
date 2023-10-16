package com.example.myapplication3.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.dragswipe.DragAndSwipeCallback
import com.chad.library.adapter.base.module.BaseDraggableModule
import com.example.myapplication3.R
import com.example.myapplication3.past.ChannelRvAdapter1
import com.example.myapplication3.past.DragAdapter
import com.example.myapplication3.adapter.SectionQuickAdapter
import com.example.myapplication3.entity.MySection
import java.util.*

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/7/17.
 * Describe :
 */

class ItemDragCallback2(var mAdapter: SectionQuickAdapter, var mPadding: Int) : ItemTouchHelper.Callback()
{
    private val mPaint: Paint = Paint()

    init {
        mPaint.color = Color.GRAY
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 1f
        mPaint.style = Paint.Style.STROKE
        val pathEffect = DashPathEffect(FloatArray(2, { 5f }), 5f)
        mPaint.pathEffect = pathEffect
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        //固定位置及tab下面的channel不能拖动
        val position = viewHolder!!.layoutPosition
        if (position < mAdapter.fixSize + 1 || position > mAdapter.selectedSize) {
            return makeMovementFlags(0, 0)
        }
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPosition = viewHolder!!.layoutPosition    //拖动的位置
        val toPosition = target!!.layoutPosition         //释放的位置
        //固定位置及tab下面的channel不能移动
        if (toPosition < mAdapter.fixSize + 1 || toPosition > mAdapter.selectedSize) {
            return false
        }
        mAdapter.itemMove(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //滑动重写这里
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//        if (dX != 0f && dY != 0f || isCurrentlyActive) {
//            //长按拖拽时底部绘制一个虚线矩形
//            c!!.drawRect(viewHolder!!.itemView.left.toFloat(), (viewHolder.itemView.top - mPadding).toFloat(), viewHolder.itemView.right.toFloat(), viewHolder.itemView.bottom.toFloat(), mPaint)
//        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            //长按时调用 设置颜色 阴影
//            val holder = viewHolder as ChannelAdapter.ChannelHolder
//            holder.name.setBackgroundColor(Color.parseColor("#FDFDFE"))
//            holder.delete.visibility= View.GONE
//            holder.name.elevation=5f
            //*******新代码********
            super.onSelectedChanged(viewHolder, actionState)
            //*******新代码********
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        //重置view
//        val holder=viewHolder as ChannelAdapter.ChannelHolder
//        holder.name.setBackgroundColor(Color.parseColor("#f0f0f0"))
//        holder.delete.visibility= View.VISIBLE
//        holder.name.elevation=0f
        //*******新代码********
        super.clearView(recyclerView, viewHolder)
        //*******新代码********

    }
}

class DragCallBack(adapter: DragAdapter, data: MutableList<MySection>) :
    ItemTouchHelper.Callback() {

    private val TAG = "DragCallBack"

    private var mData = data
    private var mAdapter = adapter

//    fun setData(data: MutableList<String>) {
//        mData = data
//    }
//
//    fun getData(): MutableList<String> {
//        return mData
//    }

    /**
     * 定义拖动方向
     * 1.网格布局：上下左右
     * 2.线性布局：上下/左右
     * 3.return makeMovementFlags
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        var dragFlags = 0
        var swipeFlags = 0
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局
                dragFlags =
                    ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            is LinearLayoutManager -> {
                // 线性布局
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            else -> {
                // 其他情况可自行处理
                return 0
            }
        }
    }

    /**
     * 拖拽时回调
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 起始位置
        val fromPosition = viewHolder.adapterPosition
        // 结束位置
        val toPosition = target.adapterPosition
        // 固定位置
        if (fromPosition == mAdapter.fixedPosition || toPosition == mAdapter.fixedPosition) {
            return false
        }
        // 根据滑动方向 交换数据
        if (fromPosition < toPosition) {
            // 含头不含尾
            for (index in fromPosition until toPosition) {
                Collections.swap(mData, index, index + 1)
            }
        } else {
            // 含头不含尾
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(mData, index, index - 1)
            }
        }
        // 刷新布局
        mAdapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    /**
     * 滑动时回调
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            Log.i(TAG, "START--->向左滑")
        } else {
            Log.i(TAG, "END--->向右滑")
        }
        val position = viewHolder.adapterPosition
        mData.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }

    /**
     * 拖拽或滑动 发生改变时回调
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.let {
                // 因为拿不到recyclerView，无法通过recyclerView.layoutManager来判断是什么布局，所以用item的宽度来判断
                // itemView.width > 500 用这个来判断是否是线性布局，实际取值自己看情况
                if (it.itemView.width > 500) {
                    // 线性布局 设置背景颜色
                    val drawable = it.itemView.background as GradientDrawable
                    drawable.color =
                        ContextCompat.getColorStateList(it.itemView.context, R.color.greenDark)
                } else {
                    // 网格布局 设置选中放大
                    ViewCompat.animate(it.itemView).setDuration(200).scaleX(1.3F).scaleY(1.3F)
                        .start()
                }
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    /**
     * 拖拽或滑动 结束时回调
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        // 恢复显示
        // 这里不能用if判断，因为GridLayoutManager是LinearLayoutManager的子类，改用when，类型推导有区别
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局 设置选中大小
                ViewCompat.animate(viewHolder.itemView).setDuration(200).scaleX(1F).scaleY(1F)
                    .start()
            }
            is LinearLayoutManager -> {
                // 线性布局 设置背景颜色
                val drawable = viewHolder.itemView.background as GradientDrawable
                drawable.color = ContextCompat.getColorStateList(
                    viewHolder.itemView.context,
                    R.color.greenPrimary
                )
            }
        }
        super.clearView(recyclerView, viewHolder)
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的拖拽，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startDrag方法开启
     */
    override fun isLongPressDragEnabled(): Boolean {
//        return super.isLongPressDragEnabled()
        return false
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的滑动，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startSwipe方法开启
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
//        return false
    }
}

class MyDragAndSwipeCallback(
    adapter: ChannelRvAdapter1,
    data: MutableList<String>,
    draggableModule: BaseDraggableModule?) : DragAndSwipeCallback(draggableModule) {
    private val TAG = "DragCallBack"

    private var mData = data
    private var mAdapter = adapter

    fun setData(data: MutableList<String>) {
        mData = data
    }

    fun getData(): MutableList<String> {
        return mData
    }

    /**
     * 定义拖动方向
     * 1.网格布局：上下左右
     * 2.线性布局：上下/左右
     * 3.return makeMovementFlags
     */
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        var dragFlags = 0
        var swipeFlags = 0
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局
                dragFlags =
                    ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            is LinearLayoutManager -> {
                // 线性布局
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            else -> {
                // 其他情况可自行处理
                return 0
            }
        }
    }

    /**
     * 拖拽时回调
     */
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        // 起始位置
        val fromPosition = viewHolder.adapterPosition
        // 结束位置
        val toPosition = target.adapterPosition
        // 固定位置
        if (fromPosition == mAdapter.fixedPosition || toPosition == mAdapter.fixedPosition) {
            return false
        }
        // 根据滑动方向 交换数据
        if (fromPosition < toPosition) {
            // 含头不含尾
            for (index in fromPosition until toPosition) {
                Collections.swap(mData, index, index + 1)
            }
        } else {
            // 含头不含尾
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(mData, index, index - 1)
            }
        }
        // 刷新布局
        mAdapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    /**
     * 滑动时回调
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            Log.i(TAG, "START--->向左滑")
        } else {
            Log.i(TAG, "END--->向右滑")
        }
        val position = viewHolder.adapterPosition
        mData.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }

    /**
     * 拖拽或滑动 发生改变时回调
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.let {
                // 因为拿不到recyclerView，无法通过recyclerView.layoutManager来判断是什么布局，所以用item的宽度来判断
                // itemView.width > 500 用这个来判断是否是线性布局，实际取值自己看情况
                if (it.itemView.width > 500) {
                    // 线性布局 设置背景颜色
                    val drawable = it.itemView.background as GradientDrawable
                    drawable.color =
                        ContextCompat.getColorStateList(it.itemView.context, R.color.greenDark)
                } else {
                    // 网格布局 设置选中放大
                    ViewCompat.animate(it.itemView).setDuration(200).scaleX(1.3F).scaleY(1.3F)
                        .start()
                }
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    /**
     * 拖拽或滑动 结束时回调
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        // 恢复显示
        // 这里不能用if判断，因为GridLayoutManager是LinearLayoutManager的子类，改用when，类型推导有区别
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局 设置选中大小
                ViewCompat.animate(viewHolder.itemView).setDuration(200).scaleX(1F).scaleY(1F)
                    .start()
            }
            is LinearLayoutManager -> {
                // 线性布局 设置背景颜色
                val drawable = viewHolder.itemView.background as GradientDrawable
                drawable.color = ContextCompat.getColorStateList(
                    viewHolder.itemView.context,
                    R.color.greenPrimary
                )
            }
        }
        super.clearView(recyclerView, viewHolder)
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的拖拽，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startDrag方法开启
     */
    override fun isLongPressDragEnabled(): Boolean {
//        return super.isLongPressDragEnabled()
        return false
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的滑动，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startSwipe方法开启
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
//        return false
    }
}