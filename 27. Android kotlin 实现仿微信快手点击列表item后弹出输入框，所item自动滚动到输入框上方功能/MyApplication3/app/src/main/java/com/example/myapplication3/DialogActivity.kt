package com.example.myapplication3

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.util.KeyboardUtil
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.rv_item.view.*
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.dialog.DialogLayer
import per.goweii.anylayer.dialog.DialogLayer.OutsideTouchedListener
import per.goweii.anylayer.widget.SwipeLayout

class DialogActivity : AppCompatActivity() {

    private var mPosition_item_content: String? = null
    private var mItemPosition = 0

    private var llComment: LinearLayout? = null

    private var llComment7: LinearLayout? = null
    private var etComment7: EditText? = null

    private var etComment: EditText? = null

    private var etComment3: TextView? = null

    private var dialog: DialogLayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        setContentView(R.layout.activity_dialog)
        val mList: MutableList<String> = ArrayList()
        for (i in 0 until 30) { mList.add("$i.编辑文本") }
        button1.setOnClickListener { showRadListDialog(mList) }
    }

    private fun showRadListDialog(dictList: List<String>) {
        val rvDialog = AnyLayer.dialog()
            .contentView(R.layout.activity_dialog_rv)
            .backgroundDimDefault()
            .gravity(Gravity.BOTTOM)
            .swipeDismiss(SwipeLayout.Direction.BOTTOM)
            .animStyle(DialogLayer.AnimStyle.BOTTOM)
            .backgroundDimAmount(0F)//背景变暗的程度0~1，直接调用到这函数里看文档就即可
        rvDialog.show()
        val mAdapter = RvAdapter()
        val mRecyclerView = rvDialog.getView<RecyclerView>(R.id.recyclerView)
        val textView = rvDialog.getView<TextView>(R.id.textView)
        llComment = rvDialog.getView<LinearLayout>(R.id.ll_comment)
        etComment3 = rvDialog.getView<TextView>(R.id.et_comment3)
        etComment3!!.setOnClickListener {
            dialog = showDialog()
            dialog!!.outsideTouched(OutsideTouchedListener {
                KeyboardUtil.hideSoftInput(this@DialogActivity)
            })
            dialog!!.show()
            etComment7 = dialog!!.getView<EditText>(R.id.et_comment7)
            etComment7?.postDelayed(object : Runnable {
                override fun run() {
                    etComment7!!.requestFocus()
                }
            }, 500)
            KeyboardUtil.showSoftInput(this@DialogActivity)
        }
        mRecyclerView!!.adapter = mAdapter
        mAdapter.setList(dictList)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            dialog = showDialog()
//             AnyLayer.dialog()
//                .compatSoftInput(true)
//                .contentView(R.layout.dialog_et)
//                .backgroundDimDefault()
//                .gravity(Gravity.BOTTOM)
//                .animStyle(DialogLayer.AnimStyle.BOTTOM)
//                .cancelableOnTouchOutside(true) //设置点击浮层以外区域是否可关闭
            dialog!!.outsideTouched(object : OutsideTouchedListener {
                override fun outsideTouched() {
//                    if (mPosition_item_content.equals("29.编辑文本")) {
//                        textView!!.visibility = View.GONE
//                    }
                    KeyboardUtil.hideSoftInput(this@DialogActivity)
                }
            })
            dialog!!.show()
            llComment7 = dialog!!.getView<LinearLayout>(R.id.ll_comment7)
            etComment7 = dialog!!.getView<EditText>(R.id.et_comment7)

            var s = adapter.getItem(position) as String
//            if (s == "29.编辑文本") {
//                textView!!.visibility = View.VISIBLE
//            }
            mPosition_item_content = s
            mItemPosition = position
//            etComment?.postDelayed(Runnable { //给他个延迟时间
            etComment7!!.requestFocus()
//            }, 300)

            etComment7!!.setText(s)
            KeyboardUtil.showSoftInput(this@DialogActivity)
            val v = adapter.getViewByPosition(position,R.id.tv_content) as TextView
            //item 底部y坐标
            val mBottomY: Int = getCoordinateY(v) + v.height
            v.postDelayed(Runnable {
                val y = getCoordinateY(llComment7!!) - 20
                //评论时滑动到对应item底部和输入框顶部对齐
                mRecyclerView.smoothScrollBy(0, mBottomY - y)
            }, 600)
        }
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

    private fun showDialog(): DialogLayer {
        return AnyLayer.dialog()
            .compatSoftInput(true)
            .contentView(R.layout.dialog_et)
            .backgroundDimDefault()
            .gravity(Gravity.BOTTOM)
            .animStyle(DialogLayer.AnimStyle.BOTTOM)
            .cancelableOnTouchOutside(true) //设置点击浮层以外区域是否可关闭
            .cancelableOnClickKeyBack(false)
    }
}