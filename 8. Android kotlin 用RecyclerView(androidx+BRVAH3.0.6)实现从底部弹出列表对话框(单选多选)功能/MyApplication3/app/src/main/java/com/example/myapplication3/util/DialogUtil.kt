package com.example.myapplication3.util

import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.RvMCAdapter
import com.example.myapplication3.adapter.RvRadioAdapter
import com.example.myapplication3.data.Dict
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.widget.SwipeLayout

/**
 * 底部弹出的列表对话框（单选）
 */
fun showRadioListDialog(
    title: String?,
    selValue: String?,
    dictList: List<Dict>,
    callback: OnResultCallback<String>
) {
    val dialog = AnyLayer.dialog()
        .contentView(R.layout.dialog_rad_list)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(SwipeLayout.Direction.BOTTOM)
        .onClickToDismiss(R.id.flFork)

    dialog.show()

    val mAdapter = RvRadioAdapter(selValue ?: "").apply{
        setOnItemClickListener { _, _, position ->
            val dict = getItem(position)

            if(dict.dataValue.equals(selValue)){
                dict.dataValue = ""
            }

            callback.onResult(dict.dataName)
            dialog.dismiss()
        }
    }
    dialog.getView<TextView>(R.id.title)?.text = title?:""
    dialog.getView<RecyclerView>(R.id.mRecyclerView)?.apply {
//        setMaxHeight(getScreenPix().heightPixels * 1 / 2)
        adapter = mAdapter
        mAdapter.setList(dictList)
        val pos = mAdapter.getSelectIndex()
        scrollToPosition(pos)
    }
}

/**
 * 底部弹出的列表对话框（多选）
 */
fun showMCListDialog(
    title: String?,
    selname: String,
    dictList: List<Dict>,
    callback: OnResultCallback<String>
) {
    val dialog = AnyLayer.dialog()
        .contentView(R.layout.dialog_rad_list)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(SwipeLayout.Direction.BOTTOM)
        .onClickToDismiss(R.id.flFork)
    dialog.show()

    val sels = mutableSetOf<Dict>()
    val names = selname.split(",")
    dictList.forEach {
        if(names.contains(it.dataName)){
            sels.add(it)
        }
    }

    val mAdapter = RvMCAdapter(sels).apply{
        setOnItemClickListener { parent, view, position ->
            val dict = getItem(position)

            if(sels.contains(dict)){
                sels.remove(dict)
            }else{
                sels.add(dict)
            }
            setCheckSelValue(sels)
        }
    }

    dialog.getView<TextView>(R.id.title)?.text = title?:""
    dialog.getView<RecyclerView>(R.id.mRecyclerView)?.apply {
//        setMaxHeight(getScreenPix().heightPixels * 1 / 2)
        adapter = mAdapter

        mAdapter.setList(dictList)
    }

    dialog.getView<LinearLayout>(R.id.ll_dialog_confirm)?.isGone = false
    dialog.getView<TextView>(R.id.tv_dialog_confirm)?.setOnClickListener {

        var valueBuilder =  StringBuilder()
        sels.forEach {
//            valueBuilder.append("|$" + it.dataValue + "$");
            valueBuilder.append(it.dataName);
        }
//        if(valueBuilder.isNotEmpty()){
//            valueBuilder = valueBuilder.deleteCharAt(0)
//        }

        dialog.dismiss()
        callback.onResult(valueBuilder.toString())
    }
}

interface OnResultCallback<T> {
    fun onResult(t: T)
}