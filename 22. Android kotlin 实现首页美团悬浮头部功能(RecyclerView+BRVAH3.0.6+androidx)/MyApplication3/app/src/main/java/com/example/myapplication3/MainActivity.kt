package com.example.myapplication3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.myapplication3.adapter.RvAdapter
import com.example.myapplication3.entity.DelegateMultiEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),OnItemClickListener{

    private val mAdapter by lazy {
        RvAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }
    }

    private val mList: MutableList<DelegateMultiEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        for (i in 0..49) {
            val xxx = DelegateMultiEntity("第 $i 行")
            mList.add(xxx)
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter
        mAdapter.setList(mList)

        recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val myLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = myLayoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItem >= 1){
                    invis.visibility = View.VISIBLE
                }else{
                    invis.visibility = View.GONE
                }
            }
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Toast.makeText(this@MainActivity,"$position",Toast.LENGTH_SHORT).show()
    }
}