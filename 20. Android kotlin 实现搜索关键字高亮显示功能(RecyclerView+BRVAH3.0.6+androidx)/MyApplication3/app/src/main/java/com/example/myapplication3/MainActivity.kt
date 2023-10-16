package com.example.myapplication3

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.Bean.User
import com.example.myapplication3.adapter.SearchRvAdapter
import com.example.myapplication3.util.KeyWordUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_rv_item.view.*


class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        SearchRvAdapter()
    }

    //用户输入的搜索内容
    private var searchContent = ""

    //用户集合
    private var mUserList: MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter

        mAdapter.setItemSelectedCallBack(object : SearchRvAdapter.ItemSelectedCallBack {

            override fun convert(holder: BaseViewHolder?, item: User) {

                //设置控件的值
                //搜索高亮显示
                if (item.number != null && item.number.length > 0) {

                    val number: SpannableString = KeyWordUtil.matcherSearchTitle(
                        Color.parseColor("#F44336"),
                        item.number + "",
                        searchContent
                    )

                    holder!!.itemView.run { search_rv_item_tv_userNumber.text = number }
                }
                if (item.nick != null && item.nick.length > 0) {
                    val nick: SpannableString = KeyWordUtil.matcherSearchTitle(
                        Color.parseColor("#F44336"),
                        item.nick,
                        searchContent
                    )
                    holder!!.itemView.run { search_rv_item_tv_userNick.text = nick }
                }
            }
        })

        //点击搜索框
        search_editText_searchContent.setOnClickListener {
            search_editText_searchContent.isFocusable = true;
            search_editText_searchContent.isFocusableInTouchMode = true;
            search_editText_searchContent.requestFocus();
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(search_editText_searchContent, 0);
        }

        //点击搜索
        btn_search.setOnClickListener {

            if (mAdapter.data.size > 0) {
                mUserList.clear()
            }

            //获取用户搜索的内容
            searchContent = search_editText_searchContent.text.toString()
            mAdapter.notifyDataSetChanged()

            //初始化一些数据（实际开发中，是去数据库中读取）
            val u1 = User(1, "4473983", "小红");
            mUserList.add(u1);
            val u2 = User(2, "8493940", "小李");
            mUserList.add(u2);
            val u3 = User(3, "0907443", "小彬");
            mUserList.add(u3);
            val u4 = User(4, "8949344", "张小");
            mUserList.add(u4)
            mAdapter.setList(mUserList)
        }
    }
}