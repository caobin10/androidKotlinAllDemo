package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.data.FirstClassItem
import com.example.myapplication3.data.SecondClassItem
import com.example.myapplication3.util.OnResultCallback
import com.example.myapplication3.util.showRadListDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    //左侧一级分类的数据
    private var firstList: MutableList<FirstClassItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //左侧一级分类和右侧二级分类的数据
        initData()

        initListener()
    }

    private fun initData() {

        firstList = ArrayList()

        //1,右侧二级分类的数据
        val secondList1 = ArrayList<SecondClassItem>()
        secondList1.add(SecondClassItem(101, "附近"))
        secondList1.add(SecondClassItem(102, "500m"))
        secondList1.add(SecondClassItem(103, "1km"))
        secondList1.add(SecondClassItem(104, "3km"))
        secondList1.add(SecondClassItem(105, "5km"))
        secondList1.add(SecondClassItem(106, "10km"))
        firstList!!.add(FirstClassItem(1, "附近", secondList1))
        //2
        val secondList2 = ArrayList<SecondClassItem>()
        secondList2.add(SecondClassItem(201, "高新区"))
        secondList2.add(SecondClassItem(202, "春熙路"))
        secondList2.add(SecondClassItem(203, "华阳"))
        secondList2.add(SecondClassItem(204, "建设路/电子科技大学"))
        secondList2.add(SecondClassItem(205, "中坝/青羊达"))
        secondList2.add(SecondClassItem(206, "大丰"))
        secondList2.add(SecondClassItem(207, "科华北路"))
        secondList2.add(SecondClassItem(208, "郫简"))
        secondList2.add(SecondClassItem(209, "温江大学城"))
        secondList2.add(SecondClassItem(210, "龙湖时代天街"))
        secondList2.add(SecondClassItem(211, "驷马桥/动物园"))
        secondList2.add(SecondClassItem(212, "万年场/万象城"))

        //******RecyclerView新增一个数据********
        secondList2.add(SecondClassItem(213, "天府新区"))
        secondList2.add(SecondClassItem(214, "航空港"))
        firstList!!.add(FirstClassItem(2, "推荐商圈", secondList2))
        //3
        val secondList3 = ArrayList<SecondClassItem>()
        secondList3.add(SecondClassItem(301, "全部"))
        secondList3.add(SecondClassItem(302, "科华北路"))
        secondList3.add(SecondClassItem(303, "桐梓林"))
        secondList3.add(SecondClassItem(304, "高升桥"))
        secondList3.add(SecondClassItem(305, "武侯祠"))
        firstList!!.add(FirstClassItem(3, "武侯区", secondList3))
        //4
        firstList!!.add(FirstClassItem(4, "青羊区", ArrayList()))
        //5
        firstList!!.add(FirstClassItem(5, "成华区", null))
    }

    private fun initListener() {

        //顶部第一个标签的点击事件
        textView.setOnClickListener {
            showRadListDialog(textView,firstList as List<FirstClassItem>,object :OnResultCallback<String>{

                //处理点击结果
                override fun onResult(selectedName: String) {
                    textView.text = selectedName
                }
            })
        }
    }
}


