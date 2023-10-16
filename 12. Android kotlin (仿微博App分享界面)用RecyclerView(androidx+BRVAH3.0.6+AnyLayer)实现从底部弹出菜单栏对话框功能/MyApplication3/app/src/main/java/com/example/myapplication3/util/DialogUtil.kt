package com.example.myapplication3.util

import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.RvAdapter
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.widget.SwipeLayout


/**
 * 底部弹出的菜单栏对话框
 */
fun showRadioListDialog(
    textView: TextView,
    list: MutableList<MutableMap<Int, String>>,
    callback: OnResultCallback<Int>
) {
    val dialog = AnyLayer.dialog()
        .contentView(R.layout.recyclerview)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(SwipeLayout.Direction.BOTTOM)
        .onClickToDismiss(R.id.button)

    dialog.show()

    val mAdapter = RvAdapter().apply {

        setOnItemClickListener { adapter, view, position ->
//            val dict = getItem(position)
            callback.onResult(position)
        }
    }
//    dialog.getView<TextView>(R.id.title)?.text = title?:""
    dialog.getView<RecyclerView>(R.id.mRecyclerView)?.apply {
        adapter = mAdapter
        mAdapter.setList(list)
//        val pos = mAdapter.getSelectIndex()
//        scrollToPosition(pos)
    }
}

interface OnResultCallback<T> {
    fun onResult(t: T)
}