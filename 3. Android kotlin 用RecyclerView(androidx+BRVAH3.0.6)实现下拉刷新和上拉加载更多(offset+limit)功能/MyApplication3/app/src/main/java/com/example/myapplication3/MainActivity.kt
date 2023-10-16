package com.example.myapplication3

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RVAdapter
import com.example.myapplication3.factory.DaoFactory
import com.example.myapplication3.file.File.copyDbFileFromAsset
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val mAdapter by lazy {
        RVAdapter().apply {
//            setOnItemClickListener(this@MainActivity)
        }
    }

    //起始位
    var offset: Int = 0

    //每页加载数
    var limit: Int = 20

    var mLinearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFile()
        initData()
    }

    private fun initView() {
        mLinearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = mLinearLayoutManager
        recyclerview.adapter = mAdapter
        setLoadMoreAdapter(mAdapter)

        swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({
                offset = 0
                limit = 20
                swipeRefreshLayout.isRefreshing = false
                initData()
            }, 1000)
        }
    }

    private fun initFile() {
        copyDbFileFromAsset()
    }

    private fun initData() {

        val dao = DaoFactory.instant.getDisBaseDao()
        val query = dao.queryBuilder()
            .offset(offset)
            .limit(limit)
            .build()
        val datas = query.list()

        val mList = arrayListOf<MutableMap<String, String>>()
        for (myDatas in datas) {
            val map = mutableMapOf<String, String>()
            map["Code"] = myDatas.code ?: ""
            map["Name"] = myDatas.name ?: ""
            map["Age"] = myDatas.age ?: ""
            mList.add(map)
        }
        loadMoreResult(mList, mAdapter)
    }

    //加载更多适配
    private fun <T> setLoadMoreAdapter(mAdapter: BaseQuickAdapter<T, BaseViewHolder>) {
//        //设置加载更多
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            Handler().postDelayed({
                offset += limit
                initData()
            }, 500)
        }

        //设置是否自动加载更多
        mAdapter.loadMoreModule.isAutoLoadMore = true
//        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
    }


    private fun <T> loadMoreResult(data: List<T>, mAdapter: BaseQuickAdapter<T, BaseViewHolder>) {
        if (data.isEmpty() && offset == 0) {

        } else {
            if (offset == 0) {
                //下拉刷新使用
                mAdapter.setList(data)
            } else {
                //上拉加载更多使用
                mAdapter.addData(data)
            }
            if (data.size < limit) {
                //如果s少于20,显示没有更多数据布局
                val isLoadEndMoreGone = (offset == 0)
                mAdapter.loadMoreModule.loadMoreEnd(isLoadEndMoreGone)
            } else {
                mAdapter.loadMoreModule.loadMoreComplete()
            }
        }
    }

    override fun onRefresh() {
//        Handler().postDelayed({
        offset = 0
        limit = 20
        swipeRefreshLayout.isRefreshing = false
        initData()
//        }, 1000)
    }

    private fun copyDbFileFromAsset() {
        val map = mutableMapOf<String, Boolean>()
        map["teststaff.db"] = true
        copyDbFileFromAsset(this, map)
    }
}

