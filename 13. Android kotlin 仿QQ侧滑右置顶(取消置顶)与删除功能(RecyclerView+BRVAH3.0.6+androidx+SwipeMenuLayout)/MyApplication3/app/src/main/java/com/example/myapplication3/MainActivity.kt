package com.example.myapplication3

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), RvAdapter.FirstOnItemClickListener, RvAdapter.OnItemClickListener {

    private val mAdapter by lazy {
        RvAdapter()
    }

    private val list: MutableList<SlidingItembean> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
        initEvent()
    }

    private fun initEvent() {
        recyclerView.adapter = mAdapter
        mAdapter.setList(list)
        mAdapter.setFirstOnItemClickListener(this)
        mAdapter.setOnItemClickListener(this)
    }

    private fun initData() {
        for (i in 0..29) {
            var slidingItembean: SlidingItembean? = null
            slidingItembean = SlidingItembean("$i", "", "", "置顶")
            list.add(slidingItembean)
        }
    }

    /**
     * 初始化界面
     */
    private fun initView() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    /**
     * 取消置顶
     * @param position
     */
    private fun unSetTop(position: Int) {
        var isAdd = false

        /** 差值  */
        var min = 9999999

        /** 当前position的数值  */
//        val num: Int

//        var num: Int

        // 差值最小处的行数
        var j = 0
        var num2 = 0
        var jumpNum = 0
        mAdapter.data[position].setTop = "置顶"

        var num = mAdapter.data[position].num.toInt()

        // list长度为2特殊处理
        if (mAdapter.data.size == 2) {
            // 第一行确定为取消置顶
            if (mAdapter.data[1].setTop.equals("取消置顶")) {
                if (position == 0) {
                    if (num == 0) {
                        mAdapter.data.add(2, mAdapter.data[position])
                    }
                    if (num == 1) {
                        mAdapter.data.add(2, mAdapter.data[position])
                    }

//                    list.removeAt(position)
                    mAdapter.data.removeAt(position)

                    mAdapter.notifyDataSetChanged()
                } else {
                    mAdapter.data.add(2, mAdapter.data[position])
                    mAdapter.data.removeAt(position)
                    mAdapter.notifyDataSetChanged()
                }
            } else {
                if (num == 0) {
                    mAdapter.data.add(1, mAdapter.data[position])
                }
                if (num == 1) {
                    mAdapter.data.add(2, mAdapter.data[position])
                }
                mAdapter.data.removeAt(position)
                mAdapter.notifyDataSetChanged()
            }
        } else {

            //for (i in mAdapter.data.indices)
            for (i in 0 until mAdapter.data.size) {

                if (num < mAdapter.data[i].num.toInt() && mAdapter.data[i].setTop.equals("置顶")) {
                    mAdapter.data.add(i, mAdapter.data[position])
                    isAdd = true
                    break
                }


                if (i + 1 != mAdapter.data.size) {
                    if (num > mAdapter.data[i].num.toInt() && num < mAdapter.data[i + 1].num.toInt()) {
                        mAdapter.data.add(i + 1, mAdapter.data[position])
                        isAdd = true
                        break
                    }
                } else {
                    mAdapter.data.add(i + 1, mAdapter.data[position])
                    isAdd = true
                    break
                }
            }

            // 如果没有比自己小的值 例如0 则isAdd=false
            // 遍历list 寻找差值最小的地方插入list
            if (!isAdd) {
//                for (i in mAdapter.data.indices)
                for (i in 0 until mAdapter.data.size) {
                    if (i == position || mAdapter.data[i].setTop.equals("取消置顶")) {
                        // 排除与自身相比较
                        // 排除置顶item比较
                        Log.i("TAG", "调过$i")
                        jumpNum++
                        if (jumpNum == mAdapter.data.size) {
                            j = mAdapter.data.size
                        }
                        continue
                    }
                    num2 = mAdapter.data[i].num.toInt()
                    if (num2 - num < min) {
                        min = num2 - num
                        // 记录行号
                        j = i
                        Log.i("TAG", "插入行数J=$j")
                    }
                }
                // 遍历完成后拿到差值min
                val number = min + num
                mAdapter.data.add(j, mAdapter.data[position])
                Log.i("TAG", "*********插入行数J=$j")
            }
            mAdapter.data.removeAt(position)
            mAdapter.notifyDataSetChanged()
        }
    }

    //取消置顶
    private fun unSetTop2(position: Int) {

        //1、如果列表某一个item的num值是0，top文本是“取消置顶”，就直接把该item插入“置顶”首位，没有什么其他的
        if (mAdapter.data[position].num.toInt() == 0) {
            for (i in mAdapter.data.indices) {
                if (mAdapter.data[i].setTop.equals("置顶")) {
                    mAdapter.data[position].setTop = "置顶"
                    mAdapter.data.add(i, mAdapter.data[position])
                    mAdapter.data.removeAt(position)
                    mAdapter.notifyDataSetChanged()
                    return
                }
            }
        }

        //2、如果列表item的top文本都是“取消置顶”，不用数一下就直接把该item插入底部
        var j = 0
        for (i in 0..mAdapter.data.size) {
            if (j == mAdapter.data.size) {
                mAdapter.data[position].setTop = "置顶"
                mAdapter.data.add(mAdapter.data.size, mAdapter.data[position])
                mAdapter.data.removeAt(position)
                mAdapter.notifyDataSetChanged()
                return
            }
            if (i == mAdapter.data.size) break
            if (mAdapter.data[i].setTop.equals("取消置顶")) {
                j++
            }
        }

        //3、如果某一个item的num值大于最后一个item的num值，并且最后一个item的top文本是“置顶”，不用数一下就直接把该item插入底部
        if (mAdapter.data[position].num.toInt() > mAdapter.data[mAdapter.data.size - 1].num.toInt() && mAdapter.data[mAdapter.data.size - 1].setTop.equals("置顶")) {
            mAdapter.data[position].setTop = "置顶"
            mAdapter.data.add(mAdapter.data.size, mAdapter.data[position])
            mAdapter.data.removeAt(position)
            mAdapter.notifyDataSetChanged()
            return
        }

        //4、如果某一个item的num值小于首位到最后的哪一个item的num值，并且哪一个item的top文本为“置顶”，就把该item插入哪一个item的top文本“置顶”上面，注意：需要在上面第3个下可以解决
        var num = mAdapter.data[position].num.toInt()
        for (i in 0 until mAdapter.data.size) {
            if (num < mAdapter.data[i].num.toInt() && mAdapter.data[i].setTop.equals("置顶")) {
                mAdapter.data[position].setTop = "置顶"
                mAdapter.data.add(i, mAdapter.data[position])
                mAdapter.data.removeAt(position)
                mAdapter.notifyDataSetChanged()
                break
            }
        }
    }

    /**
     * 置顶
     * @param position
     */
    private fun setTop(position: Int) {
        mAdapter.data[position].setTop = "取消置顶"
        mAdapter.data.add(0, mAdapter.data[position])

        // 置顶后list.size增加一 所以要position+1
        mAdapter.data.removeAt(position + 1)
        mAdapter.notifyDataSetChanged()
    }

    //置顶
    private fun setTop2(position: Int) {

        //1、如果列表某一个item的num值是0，top文本是“置顶”，就直接把该item插入它的上面，没有什么其他的
        if (mAdapter.data[position].num.toInt() == 0 && mAdapter.data[position].setTop.equals("置顶")) {
            mAdapter.data[position].setTop = "取消置顶"
            mAdapter.data.add(position + 1, mAdapter.data[position])
            mAdapter.data.removeAt(position)
            mAdapter.notifyDataSetChanged()
            return
        }

        //2、如果列表item的top文本都是“置顶”，不用数一下就直接把该item插入首位
        var j = 0
        for (i in 0..mAdapter.data.size) {
            if (j == mAdapter.data.size) {
                mAdapter.data[position].setTop = "取消置顶"
                mAdapter.data.add(0, mAdapter.data[position])
                mAdapter.data.removeAt(position + 1)
                mAdapter.notifyDataSetChanged()
                return
            }
            if (i == mAdapter.data.size) break
            if (mAdapter.data[i].setTop.equals("置顶")) {
                j++
            }
        }

        //3、如果某一个item的num值大于首位一个item的num值，并且某一个item的top文本为“置顶”和首位一个item的top文本是“取消置顶”，不用数一下就直接把该item插入首位
        if (mAdapter.data[position].num.toInt() > mAdapter.data[0].num.toInt() && mAdapter.data[0].setTop.equals("取消置顶")) {
            mAdapter.data[position].setTop = "取消置顶"
            mAdapter.data.add(0, mAdapter.data[position])
            mAdapter.data.removeAt(position + 1)
            mAdapter.notifyDataSetChanged()
            return
        }

        //4、如果某一个item的num值小于首位到最后的哪一个item的num值，并且哪一个item的top文本为“取消置顶”，就把该item插入哪一个item的top文本“取消置顶”下面，注意：需要在上面第3个下可以解决
        var num = mAdapter.data[position].num.toInt()
//        for (i in 0 until mAdapter.data.size)
        for (i in mAdapter.data.size - 1 downTo 0) {
//            if (num < mAdapter.data[i].num.toInt() && num > mAdapter.data[i+1].num.toInt() && mAdapter.data[i].setTop.equals("取消置顶") && mAdapter.data[i+1].num.equals("取消置顶")) {
            if (num < mAdapter.data[i].num.toInt() && mAdapter.data[i].setTop.equals("取消置顶")) {
                mAdapter.data[position].setTop = "取消置顶"
                mAdapter.data.add(i + 1, mAdapter.data[position])
                mAdapter.data.removeAt(position + 1)
                mAdapter.notifyDataSetChanged()
                break
            }
        }
    }

    //侧滑右置顶
    override fun firstOnItemClickListener(holder: BaseViewHolder) {
        if (mAdapter.data[holder.adapterPosition].setTop.equals("置顶")) {
            setTop2(holder.adapterPosition)
//            setTop(holder.adapterPosition)
        } else if (mAdapter.data[holder.adapterPosition].setTop.equals("取消置顶")) {
            unSetTop2(holder.adapterPosition)
        }
    }

    //侧滑右删除
    override fun onItemClickListener(holder: BaseViewHolder) {
        mAdapter.data.removeAt(holder.adapterPosition)
        mAdapter.notifyDataSetChanged()
    }
}