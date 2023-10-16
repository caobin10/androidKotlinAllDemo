package com.example.myapplication3.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.data.MyList
import kotlinx.android.synthetic.main.item.view.*

class RadioAdapter(layoutResId: Int = R.layout.item) :
    BaseQuickAdapter<MyList, BaseViewHolder>(layoutResId), LoadMoreModule {

    private val MYLIVE_MODE_CHECK = 0
    var mEditMode = MYLIVE_MODE_CHECK

    override fun convert(holder: BaseViewHolder, item: MyList) {

        holder.itemView.run {
            tv_title.text = item.title.toString()
            tv_source.text = item.source.toString()

            if (mEditMode == MYLIVE_MODE_CHECK) {
                check_box.visibility = View.GONE

                if (item.isSelect) {
                    check_box.setImageResource(R.drawable.baseline_radio_button_unchecked_24)
                    item.isSelect = false
                }

            } else {
                check_box.visibility = View.VISIBLE
                if (item.isSelect) {
                    check_box.setImageResource(R.drawable.baseline_check_circle_24)
                } else {
                    check_box.setImageResource(R.drawable.baseline_radio_button_unchecked_24)
                }
            }
        }
    }

    fun setEditMode(editMode: Int) {
        mEditMode = editMode
        notifyDataSetChanged()
    }
}