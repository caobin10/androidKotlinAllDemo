package com.example.myapplication3.channel

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.SectionQuickAdapter
import com.example.myapplication3.entity.MySection
import com.example.myapplication3.widget.ItemDragCallback2
import kotlinx.android.synthetic.main.activity_channel.*
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.dialog.DialogLayer
import per.goweii.anylayer.dialog.DialogLayer.OutsideTouchedListener
import per.goweii.anylayer.widget.SwipeLayout

class ChannelActivity : AppCompatActivity()
//    , SectionQuickAdapter.OnItemRangeChangeListener
{

    private var mRecyclerView: RecyclerView? = null

    companion object {
        private var SPAN_COUNT = 4
    }

    private var list: MutableList<MySection> = ArrayList()
    private var itemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)
        button.setOnClickListener {
            showRadListDialog(initData())
        }
    }

    private fun showRadListDialog(mList: MutableList<MySection>) {
        val rvDialog = AnyLayer.dialog()
            .contentView(R.layout.activity_section_uer)
            .backgroundDimDefault()
            .gravity(Gravity.BOTTOM)
            .swipeDismiss(SwipeLayout.Direction.BOTTOM)
            .animStyle(DialogLayer.AnimStyle.BOTTOM)
            .backgroundDimAmount(0.5F)//背景变暗的程度0~1，直接调用到这函数里看文档就即可
        rvDialog.outsideTouched(OutsideTouchedListener {
            mList.clear()
        })
        rvDialog.show()
        mRecyclerView = rvDialog.getView<RecyclerView>(R.id.rv_list)
        mRecyclerView?.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        val mAdapter = SectionQuickAdapter(R.layout.item_drag_grid, R.layout.def_section_head, mList)
//        val animator = DefaultItemAnimator()
//        animator.moveDuration = 400      //设置动画时间
//        animator.removeDuration = 0
//        mRecyclerView?.itemAnimator = animator
        mRecyclerView?.adapter = mAdapter
        mAdapter.fixSize = 1
        mAdapter.selectedSize = 10 //点击进入频道下面的频道数
//        mAdapter.onItemRangeChangeListener = this
        // 设置拖拽
        val dragCallBack = ItemDragCallback2(mAdapter,2)
        itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper!!.attachToRecyclerView(mRecyclerView)
    }

    private fun initData(): MutableList<MySection> {
        list.add(MySection(true, 1, "我最喜欢 点击进入频道", false))
        list.add(MySection(false, 0, "测试1", true))
        list.add(MySection(false, 0, "测试2", true))
        list.add(MySection(false, 0, "测试3", true))
        list.add(MySection(false, 0, "测试4", true))
        list.add(MySection(false, 0,"测试5",true))
        list.add(MySection(false, 0,"测试6",true))
        list.add(MySection(false, 0,"测试7",true))
        list.add(MySection(false, 0,"测试8",true))
        list.add(MySection(false, 0,"测试9",true))
        list.add(MySection(false, 0,"测试10",true))

        list.add(MySection(true, 2, "全部频道 点击添加频道", false))
        list.add(MySection(false, 0, "测试11", true))
        list.add(MySection(false, 0, "测试12", true))
        list.add(MySection(false, 0, "测试13", true))
        list.add(MySection(false, 0, "测试14", true))
        list.add(MySection(false, 0,"测试15",true))
        list.add(MySection(false, 0,"测试16",true))
        list.add(MySection(false, 0,"测试17",true))
        list.add(MySection(false, 0,"测试18",true))
        list.add(MySection(false, 0,"测试19",true))
        list.add(MySection(false, 0,"测试20",true))
        return list
    }

//    override fun refreshItemDecoration() {
//        mRecyclerView?.invalidateItemDecorations()
//    }
}