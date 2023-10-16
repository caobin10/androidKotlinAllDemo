package com.example.myapplication3

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.util.KeyboardUtil
import kotlinx.android.synthetic.main.activity_rv.*

class RvActivity : AppCompatActivity(), OnItemClickListener {

    private val mAdapter by lazy {
        RvAdapter().apply {
            setOnItemClickListener(this@RvActivity)
        }
    }
    private var mPosition_item_content: String? = null
    private var mItemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)
        init()
    }

    private fun init() {
        ll_comment.visibility = View.GONE
        val mList: MutableList<String> = ArrayList()
        for (i in 0 until 30) { mList.add("$i.编辑文本") }
        recyclerView.adapter = mAdapter
        mAdapter.setList(mList)

        recyclerView.setOnTouchListener(object :OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (ll_comment.visibility == View.VISIBLE){
                    updateEditTextBodyVisible(View.GONE)
                    return true
                }
                return false
            }
        })

        tv_send_comment.setOnClickListener {

            updateEditTextBodyVisible(View.GONE)

//            if (mPosition_item_content == "29.编辑文本"){
//                textView.visibility = View.GONE
//            }
//            KeyboardUtil.hideSoftInput(this)
//            mList[mItemPosition] = et_comment.text.toString()
//            mAdapter.notifyDataSetChanged()
//            ll_comment.visibility = View.GONE
//            et_comment.setText("")
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

        if (ll_comment.visibility == View.VISIBLE){
            updateEditTextBodyVisible(View.GONE)
            return
        }

        var s = adapter.getItem(position) as String
        if (s == "29.编辑文本") {
            textView.visibility = View.VISIBLE
        }
        mPosition_item_content = s
        mItemPosition = position
        ll_comment.visibility = View.VISIBLE
        et_comment.requestFocus()
        et_comment.setText(s)
        KeyboardUtil.showSoftInput(this)
        val v = adapter.getViewByPosition(position,R.id.tv_content) as TextView
//        //item 底部y坐标
//        val mBottomY: Int = getCoordinateY(view) + view.height
//        view.postDelayed(Runnable {
//            val y = getCoordinateY(ll_comment) - 20
//            //评论时滑动到对应item底部和输入框顶部对齐
//            recyclerView.smoothScrollBy(0, mBottomY - y)
//        }, 300)

        //item 底部y坐标
        val mBottomY: Int = getCoordinateY(v) + v.height
        v.postDelayed(Runnable {
            val y = getCoordinateY(ll_comment) - 20
            //评论时滑动到对应item底部和输入框顶部对齐
            recyclerView.smoothScrollBy(0, mBottomY - y)
        }, 300)
    }

    /**
     * 获取控件左上顶点Y坐标
     * @param view
     * @return
     */
    private fun getCoordinateY(view: View): Int {
        val coordinate = IntArray(2)
        view.getLocationOnScreen(coordinate)
        return coordinate[1]
    }

    fun updateEditTextBodyVisible(visibility:Int){
        if (mPosition_item_content.equals("29.编辑文本")){
            textView.visibility = visibility
        }
        ll_comment.visibility = visibility
        if (View.VISIBLE == visibility){
            ll_comment.requestFocus()
            //弹出键盘
//            CommonUtils.showSoftInput(et_comment.context, et_comment)
            KeyboardUtil.showSoftInput(et_comment.context,et_comment)
        }else if (View.GONE == visibility){
            //隐藏键盘
//            CommonUtils.hideSoftInput(et_comment.context, et_comment)
            KeyboardUtil.hideSoftInput(et_comment.context,et_comment)
        }
    }
}