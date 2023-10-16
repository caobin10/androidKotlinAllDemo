package com.example.myapplication3.past

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.item_drag_grid.view.*

class ChannelRvAdapter1 : BaseQuickAdapter<String, BaseViewHolder>, DraggableModule {

    val fixedPosition = 0 // 固定菜单
    private var mReadyToRemove: Int = -1 //标记预备删除的元素序号
    private var mIsUser: Boolean = true
    private var mAnimState = AnimState.IDEL

    constructor(isUser: Boolean) : super(R.layout.item_drag_grid) {
        mIsUser = isUser
    }

    /**
     * 动画状态枚举，用于对不通的动画状态进行处理，当前只支持空闲和移动
     * 目前看也可以用boolean，enum是为了后续扩展
     */
    enum class AnimState {
        IDEL,
        TRANSLATING
    }

    companion object {
        private var mIsEditState: Boolean = false
        fun setEdit(isEdit: Boolean) {
            mIsEditState = isEdit
        }

        fun isEdit(): Boolean {
            return mIsEditState
        }
    }

    fun add(channelName: String) {
        data.add(channelName)
        notifyDataSetChanged()
    }

    /**
     * 添加删除标记
     * @param index Int 待删除的序号
     */
    fun setRemove(index: Int): String {
        mReadyToRemove = index
        notifyDataSetChanged()
        return data[index]
    }

    fun setTranslating(translating: Boolean) {
        mAnimState = if (translating) AnimState.TRANSLATING else AnimState.IDEL
    }

    fun remove() {
        if (mReadyToRemove > 0 && mReadyToRemove < data.size) {
            removeAt(mReadyToRemove)
        }
        mReadyToRemove = -1
        notifyDataSetChanged()
    }

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.itemView.run {

            item_textView.text = item

            //正在编辑，mIsEditState，用两个列表一个一个的item一个一个被执行到“点击进入频道”->“点击添加频道”上
            if (mIsEditState) {
                helper.setVisible(R.id.delete, true)
                if (mIsUser) {
                    helper.setVisible(R.id.tv_add, false)
                } else {
                    helper.setVisible(R.id.tv_add, true)
                }
//                if (helper.adapterPosition == 0) {
//                    helper.setVisible(R.id.iv_icon, false)
//                    drawable.color = ContextCompat.getColorStateList(context, R.color.bg)
//                    item_textView.setTextColor(resources.getColor(R.color.bg2))
//                } else {
//                    helper.setVisible(R.id.iv_icon, true)
//                }
//                helper.setVisible(R.id.tv_add, false)//显示加号
            } else {
                helper.setVisible(R.id.delete, false)
                if (mIsUser) {
                    helper.setVisible(R.id.tv_add, false)
                } else {
                    helper.setVisible(R.id.tv_add, true)
                }
//                    helper.setVisible(R.id.iv_icon, false)
//                    helper.setVisible(R.id.tv_add, false)
                // 第一个固定菜单
//                    drawable = item_textView.background as GradientDrawable
//                    if (helper.adapterPosition == 0) {
//                        drawable.color = ContextCompat.getColorStateList(context, R.color.bg)
//                        item_textView.setTextColor(resources.getColor(R.color.bg2))
//                    } else {
//                        drawable.color = ContextCompat.getColorStateList(context, R.color.bg)
//                    }
//                    item_textView.setOnLongClickListener {
//                        mListener?.onItemLongClick(helper)
//                        return@setOnLongClickListener true
//                    }
            }
        }
    }

    interface OnItemClickListener {
        //        fun onItemClick(position: Int)
        fun onItemLongClick(helper: BaseViewHolder)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}
