package com.example.myapplication3.util

import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.FirstClassAdapter
import com.example.myapplication3.adapter.SecondClassAdapter
import com.example.myapplication3.data.FirstClassItem
import com.example.myapplication3.data.SecondClassItem
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.dialog.DialogLayer
import per.goweii.anylayer.widget.SwipeLayout

//右侧二级分类的数据
var secondList: MutableList<SecondClassItem>? = null

fun showRadListDialog(
    textView: TextView,
    firstList: List<FirstClassItem>,
    callback: OnResultCallback<String>
) {

    //使用AnyLayer显示一级分类和二级分类
    val dialog = AnyLayer.popup(textView)
        .contentView(R.layout.popup)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(SwipeLayout.Direction.TOP)
        .animStyle(DialogLayer.AnimStyle.TOP)
    dialog.show()

    val mAdapter = FirstClassAdapter()
    val mAdapter2 = SecondClassAdapter()

    //左侧RecyclerView
    dialog.getView<RecyclerView>(R.id.pop_listview_left)?.apply {

        //加载一级分类
        adapter = mAdapter
        mAdapter.setList(firstList)
    }

    //右侧RecyclerView
    dialog.getView<RecyclerView>(R.id.pop_listview_right)?.apply {

        //加载二级分类
        adapter = mAdapter2

        //默认加载左侧第一行对应右侧二级分类
        secondList = ArrayList()
        secondList!!.addAll(firstList!![0].secondList)
        mAdapter2.setList(secondList)
    }

    //左侧RecyclerView点击事件
    mAdapter.setOnItemClickListener { adapter, view, position ->
        val list2 = firstList!![position].secondList

        //如果没有二级类，则直接跳转
        if (list2 == null || list2.size == 0) {
            dialog.dismiss()
            val selectedName = firstList!![position].name
            callback.onResult(selectedName)
            return@setOnItemClickListener
        }
        val adapter = adapter as FirstClassAdapter

        //如果上次点击的就是这一个item，则不进行任何操作
        if (adapter.getSelectedPosition() == position) {
            return@setOnItemClickListener
        }

        //根据左侧一级分类选中情况，更新背景色
        adapter.setSelectedPosition(position)
        adapter.notifyDataSetChanged()

        //显示右侧二级分类
        updateSecondListView(list2, mAdapter2)
    }

    //右侧RecyclerView点击事件
    mAdapter2.setOnItemClickListener { adapter, view, position ->

        //关闭dialog，显示用户选择的分类
        dialog.dismiss()
        val firstPosition = mAdapter.getSelectedPosition()
        val selectedName = firstList!![firstPosition].secondList[position].name
        callback.onResult(selectedName)
    }
}

//刷新右侧RecyclerView
private fun updateSecondListView(
    list2: List<SecondClassItem>,
    secondAdapter: SecondClassAdapter
) {
    secondList!!.clear()
    secondList!!.addAll(list2)
    secondAdapter.setList(secondList)
    secondAdapter.notifyDataSetChanged()
}

interface OnResultCallback<T> {
    fun onResult(t: T)
}