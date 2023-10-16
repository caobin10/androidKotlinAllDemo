package com.example.myapplication3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.myapplication3.adapter.RadioAdapter
import com.example.myapplication3.data.MyList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pop_user.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val mAdapter by lazy {
        RadioAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }
    }

    val MYLIVE_MODE_CHECK = 0//取消模式
    val MYLIVE_MODE_EDIT = 1//编辑模式
    var mLinearLayoutManager: LinearLayoutManager? = null
    val mList: MutableList<MyList> = ArrayList()
    var mEditMode = MYLIVE_MODE_CHECK
    var isSelectAll = false
    var editorStatus = false
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initListener()
    }

    private fun initData() {
        mLinearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = mLinearLayoutManager

        //RecyclerView的通用间隔条(vivider)
        val itemDecorationHeader = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorationHeader.setDrawable(
            ContextCompat.getDrawable(this, R.drawable.divider_main_bg_height_1)!!
        )
        recyclerview.addItemDecoration(itemDecorationHeader)
        recyclerview.adapter = mAdapter
        for (i in 0..2) {
            mList.add(MyList().apply {
                title = "这是第" + i + "个条目"
                source = "来源$i"
            })
        }
        mAdapter.setList(mList)
    }

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size
     */
    private fun setBtnBackground(size: Int) {
        if (size != 0) {
            btn_delete.setBackgroundResource(R.drawable.button_shape)
            btn_delete.isEnabled = true
            btn_delete.setTextColor(Color.WHITE)

        } else {
            btn_delete.setBackgroundResource(R.drawable.button_noclickable_shape)
            btn_delete.isEnabled = false
            btn_delete.setTextColor(ContextCompat.getColor(this, R.color.color_b7b8bd))
        }
    }

    private fun initListener() {
        btn_delete.setOnClickListener { deleteVideo() }
        select_all.setOnClickListener { selectAllMain() }
        btn_editor.setOnClickListener { updataEditMode() }
    }

    /**
     * 全选和反选
     */
    private fun selectAllMain() {
        if (mAdapter == null) return
        if (!isSelectAll) {
            var i = 0
            val j: Int = mAdapter.data.size
            while (i < j) {
                mAdapter.data.get(i).setSelect(true)
                i++
            }
            index = mAdapter.data.size
            select_all.text = "取消全选"
            isSelectAll = true
        } else {
            var i = 0
            val j: Int = mAdapter.data.size
            while (i < j) {
                mAdapter.data.get(i).setSelect(false)
                i++
            }
            index = 0
            btn_delete.isEnabled = false
            select_all.text = "全选"
            isSelectAll = false
        }
        mAdapter.notifyDataSetChanged()
        setBtnBackground(index)
        tv_select_num.text = index.toString()
    }

    /**
     * 删除逻辑
     */
    private fun deleteVideo() {
        if (index == 0) {
            btn_delete.isEnabled = false
            return
        }
        val builder = AlertDialog.Builder(this).create()
        builder.show()
        if (builder.window == null) return
        builder.window!!.setContentView(R.layout.pop_user) //设置弹出框加载的布局
        if (builder.tv_msg == null || builder.btn_cancle == null || builder.btn_sure == null) return
        if (index == 1) {
            builder.tv_msg.text = "删除后不可恢复，是否删除该条目？"

        } else {
            builder.tv_msg.text = "删除后不可恢复，是否删除这" + index + "个条目？"
        }
        builder.btn_cancle.setOnClickListener { builder.dismiss() }
        builder.btn_sure.setOnClickListener {
            var i: Int = mAdapter.data.size
            val j = 0
            while (i > j) {
                val myLive: MyList = mAdapter.data.get(i - 1)
                if (myLive.isSelect()) {
                    mAdapter.data.remove(myLive)
                    index--
                }
                i--
            }
            index = 0
            tv_select_num.text = 0.toString()
            setBtnBackground(index)
            if (mAdapter.data.size == 0) {
                ll_mycollection_bottom_dialog.visibility = View.GONE
            }
            mAdapter.notifyDataSetChanged()
            builder.dismiss()
        }
    }

    private fun updataEditMode() {

//        mEditMode = if (mEditMode == MYLIVE_MODE_CHECK) MYLIVE_MODE_EDIT else MYLIVE_MODE_CHECK

        if (mEditMode == MYLIVE_MODE_CHECK) {
            mEditMode = MYLIVE_MODE_EDIT //编辑模式
        } else {
            mEditMode = MYLIVE_MODE_CHECK //取消模式
        }

        if (mEditMode == MYLIVE_MODE_EDIT) {
            btn_editor.text = "取消"
            ll_mycollection_bottom_dialog.visibility = View.VISIBLE
            editorStatus = true
        } else {
            btn_editor.text = "编辑"
            ll_mycollection_bottom_dialog.visibility = View.GONE
            editorStatus = false
            index = 0
            clearAll()
        }
        mAdapter.setEditMode(mEditMode)
    }

    private fun clearAll() {
        tv_select_num.text = 0.toString()
        isSelectAll = false
        select_all.text = "全选"
        setBtnBackground(0)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        if (editorStatus) {
            val myLive: MyList = mAdapter.data.get(position)
            val isSelect: Boolean = myLive.isSelect()
            if (!isSelect) {
                index++
                myLive.setSelect(true)
                if (index == mAdapter.data.size) {
                    isSelectAll = true
                    select_all.text = "取消全选"
                }
            } else {
                myLive.setSelect(false)
                index--
                isSelectAll = false
                select_all.text = "全选"
            }
            setBtnBackground(index)
            tv_select_num.text = index.toString()
            mAdapter.notifyDataSetChanged()
        }
    }
}

