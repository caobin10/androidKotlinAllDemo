package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.data.Dict
import com.example.myapplication3.util.OnResultCallback
import com.example.myapplication3.util.showMCListDialog
import com.example.myapplication3.util.showRadioListDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list: MutableList<Dict> = ArrayList()
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initDatas()
        initListener()
    }

    private fun initView() {
        textView1.text = "测试1"//默认
        textView2.text = "测试1"//默认
    }

    private fun initDatas() {
        for (i in 1..10) {
            val sc = Dict("测试$i", "$i")
            list.add(sc)
        }
    }

    private fun initListener() {

        //从底部弹出列表对话框(单选)
        textView1.setOnClickListener {
            for (i in 0 until list.size) {
                if (textView1.text == list[i].dataName) {
                    index = i;
                    break
                }
            }
            showRadioListDialog(
                textView1.text.toString(),
                list[index].dataValue,
                list as List<Dict>,
                object : OnResultCallback<String> {
                    override fun onResult(dataName: String) {
                        textView1.text = dataName
                    }
                })
        }

        //从底部弹出列表对话框(多选)
        textView2.setOnClickListener {
            for (i in 0 until list.size) {
                if (textView1.text == list[i].dataName) {
                    index = i;
                    break
                }
            }
            showMCListDialog(
                textView1.text.toString(),
                list[index].dataValue,
                list as List<Dict>,
                object : OnResultCallback<String> {
                    override fun onResult(s: String) {
                        textView2.text = s
                    }
                })
        }
    }
}

