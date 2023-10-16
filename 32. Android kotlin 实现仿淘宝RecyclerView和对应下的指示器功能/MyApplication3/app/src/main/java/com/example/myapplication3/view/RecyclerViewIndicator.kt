package com.example.myapplication3.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R

class RecyclerViewIndicator @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
//    private val rootView: View
    private val mRootView: View
    private val indView: View
    var indViewWidth = 0
    var indViewHeight = 0
    var rate = 0f

    init {
        val root = inflate(context, R.layout.app_viewpager_indicator, this)
        mRootView = root.findViewById<View>(R.id.root)
        indView = root.findViewById<View>(R.id.ind_view)
    }

    /**
     * 绑定recyclerView
     * @param recyclerView
     * @param orientation 排列方向
     *
     * 这里用到的recyclerView的三个方法
     * computeHorizontalScrollExtent/computeVerticalScrollExtent 当前显示在屏幕上的总长度
     * computeHorizontalScrollOffset/computeVerticalScrollOffset 当前滑动的总长度
     * computeHorizontalScrollRange/computeVerticalScrollRange recylerView内部的总长度
     */
    fun setWithRecyclerView(recyclerView: RecyclerView, orientation: Int) {
        val isHorizontal = orientation == RecyclerView.HORIZONTAL
        mRootView.post(Runnable {
            val scrollRange =
                (if (isHorizontal) recyclerView.computeHorizontalScrollRange() else recyclerView.computeVerticalScrollRange()).toFloat()
            val scrollExtent =
                (if (isHorizontal) recyclerView.computeHorizontalScrollExtent() else recyclerView.computeVerticalScrollExtent()).toFloat()
            val layoutParams = indView.layoutParams as LayoutParams
            if (isHorizontal) {
                //算出比例
                rate = width.toFloat() / scrollRange
                //由显示在屏幕上的总长度算出滑块长度
                indViewWidth = (scrollExtent * rate).toInt()
                layoutParams.height = LayoutParams.MATCH_PARENT
                layoutParams.width = indViewWidth
            } else {
                rate = height.toFloat() / scrollRange
                layoutParams.width = LayoutParams.MATCH_PARENT
                indViewHeight = (scrollExtent * rate).toInt()
                layoutParams.height = indViewHeight
            }
            indView.layoutParams = layoutParams
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val scrollOffset =
                    if (isHorizontal) recyclerView.computeHorizontalScrollOffset() else recyclerView.computeVerticalScrollOffset()
                //由recyclerView滑动距离算出滑块移动距咯
                if (isHorizontal) {
                    val left = (scrollOffset * rate).toInt()
                    indView.layout(left, indView.top, left + indViewWidth, indView.bottom)
                } else {
                    val top = (scrollOffset * rate).toInt()
                    indView.layout(indView.left, top, indView.right, top + indViewHeight)
                }
            }
        })
    }
}

