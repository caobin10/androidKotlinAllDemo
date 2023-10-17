package com.example.mytesttree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.mytesttree.adapter.node.tree.NodeTreeAdapter
import com.example.mytesttree.entity.node.tree.FirstNode
import com.example.mytesttree.entity.node.tree.SecondNode
import com.example.mytesttree.entity.node.tree.ThirdNode
import kotlinx.android.synthetic.main.activity_main.rv_list

class MainActivity : AppCompatActivity() {

//    private val mAdapter = NodeTreeAdapter()

    private val mAdapter by lazy {
        NodeTreeAdapter().apply {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        rv_list.layoutManager = GridLayoutManager(this@MainActivity, 4)
        rv_list.adapter = mAdapter
    }

    private fun initData() {
        mAdapter.setList(getEntity())
    }

    private fun getEntity(): MutableList<BaseNode>? {
        val list: MutableList<BaseNode> = ArrayList()
        for (i in 0..4) {
            val secondNodeList: MutableList<BaseNode> = ArrayList()

            if (i == 1) {
                val thirdNodeList: MutableList<BaseNode> = ArrayList()
                for (t in 0..1) {
                    val node =
                        ThirdNode("三级标题$t")
                    thirdNodeList.add(node)
                }
                val seNode =
                    SecondNode(
                        thirdNodeList,
                        "二级标题0"
                    )
                seNode.isExpanded = false
                secondNodeList.add(seNode)
            } else if (i == 2) {
                for (n in 0..1) {

                    val thirdNodeList: MutableList<BaseNode> = ArrayList()

                    if (n == 0) {
                        for (t in 0..2) {
                            val node =
                                ThirdNode(
                                    "三级标题$t"
                                )
                            thirdNodeList.add(node)
                        }
                    }

                    if (n == 1) {
                        for (t in 0..1) {
                            val node =
                                ThirdNode(
                                    "三级标题$t"
                                )
                            thirdNodeList.add(node)
                        }
                    }

//                    for (t in 0..2) {
//                        val node =
//                            ThirdNode(
//                                "三级标题$t"
//                            )
//                        thirdNodeList.add(node)
//                    }
                    val seNode =
                        SecondNode(
                            thirdNodeList,
                            "二级标题$n"
                        )
                    seNode.isExpanded = false
                    secondNodeList.add(seNode)

                }
            } else {
                val thirdNodeList: MutableList<BaseNode> = ArrayList()
                for (t in 0..3) {
                    val node =
                        ThirdNode("三级标题$t")
                    thirdNodeList.add(node)
                }
                val seNode =
                    SecondNode(
                        thirdNodeList,
                        "二级标题0"
                    )
                seNode.isExpanded = false
                secondNodeList.add(seNode)
            }

            val entity = FirstNode(
                secondNodeList,
                "一级标题$i"
            )
            // 模拟 默认第0个是展开的
            entity.isExpanded = false
            list.add(entity)
        }
        return list
    }
}