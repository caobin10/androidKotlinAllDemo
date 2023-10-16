package com.example.myapplication3.util

import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.MyList
import com.example.myapplication3.R
import com.example.myapplication3.adapter.ItemDictAdapter
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.Layer
import per.goweii.anylayer.dialog.DialogLayer

/**
 * 默认的输入框对话框
 */
fun showInpDefaultDialog(
//    title: String?,
//    content: String?,
//    textWatcher: TextWatcher,
//    callback: OnResultCallback<String>
): Layer {
    val dialog = AnyLayer.dialog().contentView(R.layout.dialog_inp_default)
    //使用默认的背景
    dialog.backgroundDimDefault()
    dialog.interceptKeyEvent(true)
//    dialog.onClickToDismiss(R.id.cancel)
    dialog.show()

//    dialog.getView<TextView>(R.id.title)?.text = title

//    val input = dialog.getView<EditText>(R.id.selName)
//    if(!content.isNullOrEmpty())input?.setText(content)
//    input?.addTextChangedListener(textWatcher)

//    dialog.getView<TextView>(R.id.confirm)?.setOnClickListener {
//        dialog.dismiss()
//        callback.onResult(input?.text?.trim().toString())
//    }

    return dialog
}

/**
 * 底部弹出的列表对话框（单选）
 */
fun showRadListDialog(
    textView: TextView, dictList: List<MyList>,
    direction_top:Int,
    animStyle_top: DialogLayer.AnimStyle
) {
    val dialog = AnyLayer.popup(textView)
        .contentView(R.layout.dialog_list)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(direction_top)
        .animStyle(animStyle_top)
    dialog.show()
    val mAdapter = ItemDictAdapter()
    dialog.getView<RecyclerView>(R.id.mRecyclerView)?.apply {
        adapter = mAdapter
        mAdapter.setList(dictList)
    }
}

fun showRadListDialog2(
    dictList: List<MyList>,
    direction_top:Int,
    animStyle_top: DialogLayer.AnimStyle
) {
    val dialog = AnyLayer.dialog()
        .contentView(R.layout.dialog_list)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(direction_top)
        .animStyle(animStyle_top)
    dialog.show()
    val mAdapter = ItemDictAdapter()
    dialog.getView<RecyclerView>(R.id.mRecyclerView)?.apply {
        adapter = mAdapter
        mAdapter.setList(dictList)
    }
}