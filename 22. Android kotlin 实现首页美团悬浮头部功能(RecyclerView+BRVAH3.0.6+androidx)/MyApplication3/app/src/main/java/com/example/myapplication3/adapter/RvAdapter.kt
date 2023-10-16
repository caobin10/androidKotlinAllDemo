package com.example.myapplication3.adapter

import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.DelegateMultiEntity
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class RvAdapter : BaseDelegateMultiAdapter<DelegateMultiEntity, BaseViewHolder>() {

    init {
        setMultiTypeDelegate(MyMultiTypeDelegate())
    }

    override fun convert(holder: BaseViewHolder, item: DelegateMultiEntity) {
        when (holder.itemViewType) {
            //头部内容,会隐藏的部分
            DelegateMultiEntity.TEXT1 -> {}
            //头部内容,一直显示的部分
            DelegateMultiEntity.TEXT2 -> {}
            //正常item
            else -> {
                holder.itemView.run { tv_content.text = item.name }
            }
        }
    }

    internal class MyMultiTypeDelegate : BaseMultiTypeDelegate<DelegateMultiEntity>() {

        init {
            //下面设置悬浮和头顶部分内容
            addItemType(DelegateMultiEntity.TEXT1, R.layout.see_or_not) //添加头部(头部内容,会隐藏的部分)
            addItemType(DelegateMultiEntity.TEXT2, R.layout.see_along) //添加头部(头部内容,一直显示的部分)
            addItemType(DelegateMultiEntity.ITEM_TEXT3, R.layout.item_recyclerview) //正常item
        }

        override fun getItemType(data: List<DelegateMultiEntity>, position: Int): Int {

            return when (position) {
                0 -> DelegateMultiEntity.TEXT1
                1 -> DelegateMultiEntity.TEXT2
                else -> {
                    DelegateMultiEntity.ITEM_TEXT3
                }
            }
            return 0
        }
    }
}