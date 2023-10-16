package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.util.OnResultCallback
import com.example.myapplication3.util.showRadioListDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    var list = mutableListOf<MutableMap<Int, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        textView.setOnClickListener {

            showRadioListDialog(textView,list,object : OnResultCallback<Int> {
                override fun onResult(position: Int) {

                }
            })
        }
    }

    private fun initData() {
        setResources()
    }

    private fun setResources() {
        disableGroup()
    }

    private fun disableGroup() {
        addItemResources(R.mipmap.ic_c, getString(R.string.test1))//<string name="test1">测试1</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test2))//<string name="test1">测试2</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test3))//<string name="test1">测试3</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test4))//<string name="test1">测试4</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test5))//<string name="test1">测试5</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test6))//<string name="test1">测试6</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test7))//<string name="test1">测试7</string>
        addItemResources(R.mipmap.ic_c, getString(R.string.test8))//<string name="test1">测试8</string>
    }

    private fun addItemResources(key: Int, value: String) {
        list.add(mutableMapOf<Int, String>(key to value))
    }
}

