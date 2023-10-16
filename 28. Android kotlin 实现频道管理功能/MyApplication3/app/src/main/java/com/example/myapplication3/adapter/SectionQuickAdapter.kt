package com.example.myapplication3.adapter

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.example.myapplication3.entity.MySection
import kotlinx.android.synthetic.main.def_section_head.view.*
import java.util.*

class SectionQuickAdapter(layoutResId: Int, sectionHeadResId: Int, data: MutableList<MySection>) :
    BaseSectionQuickAdapter<MySection, BaseViewHolder>(sectionHeadResId, data) {
    init {
        setNormalLayout(layoutResId)
        addChildClickViewIds(R.id.edit)
    }

    var selectedSize: Int = 0             //已选
    var fixSize: Int = 0                 //固定频道数目
    var isRecommend: Boolean = true      //当前是否显示推荐频道
    var mLeft: Int = -1                  //推荐频道蓝色线条距离屏幕左边的距离
    var mRight: Int = -1                 //城市频道蓝色线条距离屏幕左边的距离
    var mTabY: Int = 0                   //tab距离parent的Y的距离

//    var onItemRangeChangeListener: SectionQuickAdapter.OnItemRangeChangeListener? = null

    override fun convertHeader(helper: BaseViewHolder, item: MySection) {
        helper.itemView.run {
            when (item.headerNum) {
                1 -> {
                    header.text = "我最喜欢 "
//                    enter.text = Html.fromHtml("<font size=2 color='#DCDCDC'>按住拖动可以排序</font>")
                }
                2 -> {
                    header.text = Html.fromHtml("全部频道 <font color=\"#DCDCDC\">点击添加频道</font>")
                    edit.visibility = View.GONE
                }
            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: MySection) {
        setChannel(holder, holder.adapterPosition, item)
    }

    private fun setChannel(holder: BaseViewHolder, position: Int, item: MySection) {
        var name = holder.getView<TextView>(R.id.item_textView)
        var delete = holder.getView<ImageView>(R.id.delete)
        var add = holder.getView<TextView>(R.id.tv_add)
        name.text = item.`object` as String
        name.setOnClickListener {
            if (holder.layoutPosition < selectedSize + 1) {
                //tab上面的 点击移除
                if (holder.layoutPosition > fixSize) {
                    removeFromSelected(holder)
                }
            } else {
                //tab下面的 点击添加到已选频道
                selectedSize++
                itemMove(holder.layoutPosition, selectedSize)
                notifyItemChanged(selectedSize)
                //刷新itemDecoration
//                onItemRangeChangeListener?.let { onItemRangeChangeListener!!.refreshItemDecoration() }
            }
        }
        name.setOnLongClickListener {
            true
        }

        delete.setOnClickListener { removeFromSelected(holder) }
        //tab下面及固定频道不显示删除按钮
        if (position - 1 < fixSize || position > selectedSize) {

            //************************新代码**********************
            //固定频道的文字灰色
            if (fixSize == position) {
                name.setTextColor(Color.GRAY)
            } else {
                add.visibility = View.VISIBLE
            }
            //****************************************************

            delete.visibility = View.GONE
        } else {
            delete.visibility = View.VISIBLE

            //************************新代码**********************
            add.visibility = View.GONE
            //****************************************************
        }
    }

//    private fun setTab(holder: ChannelAdapter.TabHolder) {
//        holder.itemView.viewTreeObserver.addOnPreDrawListener(object :
//            ViewTreeObserver.OnPreDrawListener {
//            override fun onPreDraw(): Boolean {
//                mTabY = holder.itemView.top
//                return true
//            }
//
//        })
//    }

    private fun removeFromSelected(holder: BaseViewHolder) {
        val delete = holder.getView<ImageView>(R.id.delete)

        //************************新代码**********************
        val add = holder.getView<TextView>(R.id.tv_add)
        add.visibility = View.VISIBLE
        //****************************************************

        delete.visibility = View.GONE
        val position = holder.layoutPosition
        val bean = data[position]

        if ((isRecommend && bean.isRecommend) || (!isRecommend && !bean.isRecommend)) {
            //移除的频道属于当前tab显示的频道，直接调用系统方法移除
            itemMove(position, selectedSize + 1)
            notifyItemRangeChanged(selectedSize + 1, 1)
            //刷新itemDecoration
//            onItemRangeChangeListener?.let { onItemRangeChangeListener!!.refreshItemDecoration() }
        } else {
            //不属于当前tab显示的频道
            removeAnimation(
                holder.itemView,
                (if (isRecommend) mRight else mLeft).toFloat(),
                mTabY.toFloat(),
                position
            )
        }
        selectedSize--
    }

    private fun removeAnimation(view: View, x: Float, y: Float, position: Int) {
        val fromX = view.left
        val fromY = view.top
        val animatorX = ObjectAnimator.ofFloat(view, "translationX", 0f, x - fromX)
        val animatorY = ObjectAnimator.ofFloat(view, "translationY", 0f, y - fromY)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val set = AnimatorSet()
        set.playTogether(animatorX, animatorY, alpha)
        set.duration = 350
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                data.add(0, data[position])
                data.removeAt(position)
                notifyItemRemoved(position)
//                onItemRangeChangeListener?.let { onItemRangeChangeListener!!.refreshItemDecoration() }
                //这里需要重置view的属性
                resetView(view, x - fromX, y - fromY)
            }
        })
        set.start()
    }

    private fun resetView(view: View, toX: Float, toY: Float) {
        val animatorX = ObjectAnimator.ofFloat(view, "translationX", -toX, 0f)
        val animatorY = ObjectAnimator.ofFloat(view, "translationY", -toY, 0f)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val set = AnimatorSet()
        set.playTogether(animatorX, animatorY, alpha)
        set.duration = 0
        set.startDelay = 5
        set.start()
    }

    fun itemMove(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
}