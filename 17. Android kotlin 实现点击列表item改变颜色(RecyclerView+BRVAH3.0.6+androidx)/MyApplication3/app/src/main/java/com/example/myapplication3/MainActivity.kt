package com.example.myapplication3

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener{

    //记录当前位置
    private var currentPosition = 0

    private var mShowItems: MutableList<String> = ArrayList()

    private val mAdapter by lazy {
        RvAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        for (i in 0..19) {
            mShowItems.add("菜单$i")
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        mAdapter.setList(mShowItems)
//        mAdapter.setItemSelectedCallBack(this)

        mAdapter.setItemSelectedCallBack(object : RvAdapter.ItemSelectedCallBack {
            override fun convert(holder: BaseViewHolder?, position: Int) {

                //初始化组件
                val xx = holder!!.getView<LinearLayout>(R.id.item_layout)
                val xxx = holder!!.getView<TextView>(R.id.tv_content)

                //判断传入的position是否和当前一致
                if (position == currentPosition){
                    xx.setBackgroundColor(resources.getColor(R.color.bg)) //<color name="bg">#E18D12</color>
                    xxx.setTextColor(resources.getColor(R.color.white)) //<color name="white">#FFFFFFFF</color>
                }else{
                    xx.setBackgroundColor(resources.getColor(R.color.white))
                    xxx.setTextColor(resources.getColor(R.color.bg))
                }
            }
        })
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

        //这里赋值
        currentPosition = position
        //每点击一次item就刷新适配器
        mAdapter.notifyDataSetChanged()
    }
}