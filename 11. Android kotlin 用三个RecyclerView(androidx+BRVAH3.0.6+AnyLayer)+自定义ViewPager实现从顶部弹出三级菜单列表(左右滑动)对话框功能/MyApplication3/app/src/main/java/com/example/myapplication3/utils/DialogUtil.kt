package com.example.myapplication3.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.adapter.MenuDialogAdapter
import com.example.myapplication3.adapter.MyPagerAdapter
import com.example.myapplication3.bean.MenuData
import com.example.myapplication3.view.MyViewPager
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.widget.SwipeLayout

/**
 * 底部弹出的列表对话框（单选）
 */
fun showRadioListDialog(
    mContext:Context,
    mTextView: TextView,
    callback: OnResultCallback<MenuData>
) {
    //列表显示数据必须要的adapter
    var mListView1Adapter: MenuDialogAdapter? = null
    var mListView2Adapter: MenuDialogAdapter? = null
    var mListView3Adapter: MenuDialogAdapter? = null
    val views: MutableList<View?> = ArrayList() //数据集合
    var mDictDataManager = MenuDataManager.instance //全部数据

    val dialog = AnyLayer.popup(mTextView)
        .contentView(R.layout.three_menu_dialog)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .swipeDismiss(SwipeLayout.Direction.TOP)

    dialog.show()

    val viewpager = dialog.getView<MyViewPager>(R.id.viewpager) as MyViewPager

    viewpager?.offscreenPageLimit = 2 //显示2页

    //为view加载layout,由于三个级的菜单都是只有一个listView，这里就只xie一个了
    val inflater = LayoutInflater.from(mContext)

    //三个菜单级view
    val view1 = inflater.inflate(R.layout.pager_number, null)
    val view2 = inflater.inflate(R.layout.pager_number, null)
    val view3 = inflater.inflate(R.layout.pager_number, null)

    //每个菜单列表都是一个listView
    //获取id
    val mRv1 = view1.findViewById<View>(R.id.mRv) as RecyclerView
    val mRv2 = view2.findViewById<View>(R.id.mRv) as RecyclerView
    val mRv3 = view3.findViewById<View>(R.id.mRv) as RecyclerView

    //获取列表数据了
    val list = mDictDataManager!!.getTripleColumnData(mContext, 0)

    //关联adapter
    mListView1Adapter = MenuDialogAdapter()

    val layoutManager1 = LinearLayoutManager(mContext)
    layoutManager1.orientation = LinearLayoutManager.VERTICAL
    mRv1.layoutManager = layoutManager1

    val layoutManager2 = LinearLayoutManager(mContext)
    layoutManager2.orientation = LinearLayoutManager.VERTICAL
    mRv2.layoutManager = layoutManager2

    val layoutManager3 = LinearLayoutManager(mContext)
    layoutManager3.orientation = LinearLayoutManager.VERTICAL
    mRv3.layoutManager = layoutManager3

    mRv1.adapter = mListView1Adapter
    mListView1Adapter!!.setList(list)

    mListView2Adapter = MenuDialogAdapter()
    mListView3Adapter = MenuDialogAdapter()

    mListView1Adapter!!.setSelectedBackgroundResource(R.drawable.select_white) //选中时的背景
    mListView1Adapter!!.setHasDivider(false)
    mListView1Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray) //未选中

    mRv2.adapter = mListView2Adapter
    mRv3.adapter = mListView3Adapter

    views.add(view1)
    views.add(view2) //当前是第三级菜单，所以前面已经存在第一，第二菜单了

    //关联
    dialog.getView<MyViewPager>(R.id.viewpager)?.adapter = MyPagerAdapter(views)

    mListView1Adapter!!.setOnItemClickListener { adapter, view, position ->
        if (mListView1Adapter != null)
            mListView1Adapter!!.setSelectedPos(position)
        if (mListView2Adapter != null)
            mListView2Adapter!!.setSelectedPos(-1)

        if (views.contains(view3)) {
            views.remove(view3)
            viewpager.adapter!!.notifyDataSetChanged() //立即更新adapter数据
        }

        val menuData = adapter.getItem(position) as MenuData //得到第position个menu子菜单

        if (menuData.id == 0) { //不限
            if (mListView2Adapter != null) {
                mListView2Adapter!!.setList(ArrayList())
                mListView2Adapter!!.notifyDataSetChanged() //刷新
            }
            callback.onResult(menuData)
            dialog.dismiss()

        } else {
            val list2 = mDictDataManager!!.getTripleColumnData(mContext, menuData.id)
            if (mListView2Adapter == null) {
                mListView2Adapter = MenuDialogAdapter()
                mListView2Adapter!!.setNormalBackgroundResource(R.color.white)
                mRv2.adapter = mListView2Adapter
            } else {
                mListView2Adapter!!.setList(list2)

                mListView2Adapter!!.notifyDataSetChanged()
            }
        }

    }

    mListView2Adapter!!.setOnItemClickListener { adapter, view, position ->

        if (mListView2Adapter != null) {
            mListView2Adapter!!.setSelectedPos(position)
            mListView2Adapter!!.setSelectedBackgroundResource(R.drawable.select_gray)
        }

        if (views.contains(view3)) {
            views.remove(view3)
        }

        //从第二级菜单的基础上加载第三级菜单
        val menuData = adapter.getItem(position) as MenuData


        val list3 = mDictDataManager!!.getTripleColumnData(mContext, menuData.id)
        if (mListView3Adapter == null) {
            mListView3Adapter = MenuDialogAdapter()

            mListView3Adapter!!.setHasDivider(false)
            mListView3Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray)
            mRv3.adapter = mListView3Adapter
        } else {
            mListView3Adapter!!.setList(list3)

            mListView3Adapter!!.notifyDataSetChanged()
        }

        //放入第三级菜单列表
        views.add(view3)

        viewpager.adapter!!.notifyDataSetChanged()
        if (viewpager.currentItem != 1) {
            viewpager.postDelayed({
                viewpager.currentItem = 1 //选一个
            }, 300)
        }
    }

    //最后就是第三级菜单的点击了
    mListView3Adapter!!.setOnItemClickListener { adapter, view, position ->
        val menuData = adapter.getItem(position) as MenuData
        callback.onResult(menuData) //选中点击的子菜单，去设置titleName
        dialog.dismiss()
    }
}

interface OnResultCallback<T> {
    fun onResult(t: T)
}