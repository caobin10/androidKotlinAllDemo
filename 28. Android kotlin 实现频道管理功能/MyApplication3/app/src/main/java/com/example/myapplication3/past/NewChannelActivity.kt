package com.example.myapplication3.past

import androidx.appcompat.app.AppCompatActivity

class NewChannelActivity : AppCompatActivity()
//    , OnItemClickListener
{

//    /**
//     * 动画时长
//     */
//    private val animDuration: Long = 300
//
//    companion object {
//        private var SPAN_COUNT = 4
//    }
//
//    //我最喜欢 点击进入频道
//    private var list1: MutableList<String> = ArrayList()
//    var moreFlag = MutableLiveData<Boolean>()
//
//    //全部频道 点击添加频道
//    private var list2: MutableList<String> = ArrayList()
//
//    private var mRecyclerView1: RecyclerView? = null
//    private val mAdapter1 by lazy {
//        ChannelRvAdapter1(true).apply {
//            setOnItemClickListener(this@NewChannelActivity)
//        }
//    }
//
//    private var mRecyclerView2: RecyclerView? = null
//    private val mAdapter2 by lazy {
//        ChannelRvAdapter1(false).apply {
//            setOnItemClickListener(this@NewChannelActivity)
//        }
//    }
//
//    private lateinit var mGridItemDecoration: GridSpaceItemDecoration
//    private var itemTouchHelper: ItemTouchHelper? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_channel)
//        initData()
//        button.setOnClickListener {
//            showRadListDialog(list1, list2)
//        }
//    }
//
//    private fun initData() {
//        for (i in 0..13) {
//            list1.add("测试$i")
//        }
//        for (i in 14..25) {
//            list2.add("测试$i")
//        }
//    }
//
//    private fun showRadListDialog(mList1: MutableList<String>, mList2: MutableList<String>) {
//        val rvDialog = AnyLayer.dialog()
//            .contentView(R.layout.channel_rv)
//            .backgroundDimDefault()
//            .gravity(Gravity.BOTTOM)
//            .swipeDismiss(SwipeLayout.Direction.BOTTOM)
//            .animStyle(DialogLayer.AnimStyle.BOTTOM)
//            .backgroundDimAmount(0.5F)//背景变暗的程度0~1，直接调用到这函数里看文档就即可
//        rvDialog.show()
//        val mEdit = rvDialog.getView<TextView>(R.id.editTv)//编辑/完成
//        mRecyclerView1 = rvDialog.getView<RecyclerView>(R.id.rv_list1)
//        mRecyclerView2 = rvDialog.getView<RecyclerView>(R.id.rv_list2)
//        mRecyclerView1?.layoutManager = GridLayoutManager(this, SPAN_COUNT)
//        mRecyclerView2?.layoutManager = GridLayoutManager(this, SPAN_COUNT)
//        if (!::mGridItemDecoration.isInitialized) {
//            mGridItemDecoration = GridSpaceItemDecoration(SPAN_COUNT)
//            mRecyclerView1?.addItemDecoration(mGridItemDecoration)
//            mRecyclerView2?.addItemDecoration(mGridItemDecoration)
//        }
////        val mAdapter1 = ChannelRvAdapter1(true)
////        mAdapter1.setOnItemClickListener(this@NewChannelActivity)
////        val mAdapter2 = ChannelRvAdapter1(false)
////        mAdapter2.setOnItemClickListener(this)
//        mRecyclerView1?.adapter = mAdapter1
//        mRecyclerView2?.adapter = mAdapter2
//        mAdapter1.setNewInstance(mList1)
//        mAdapter2.setNewInstance(mList2)
//        // 设置拖拽
//        val dragCallBack = DragCallBack(mAdapter1, mList1)
//        itemTouchHelper = ItemTouchHelper(dragCallBack)
//        itemTouchHelper?.attachToRecyclerView(mRecyclerView1)
//
//        var editBoolean = true //
//        mEdit?.setOnClickListener {
//            if (editBoolean) {
//                mEdit.text = "完成"
//                enterTv.text = "按住拖动可以排序"
//                editBoolean = false
//                val isEdit = ChannelRvAdapter1.isEdit()
//                ChannelRvAdapter1.setEdit(!isEdit)
//                moreFlag.value = !isEdit
//                mAdapter1.notifyDataSetChanged()
////                mAdapter2.notifyDataSetChanged()
//            } else {
//                mEdit.text = "编辑"
//                enterTv.text = "点击进入频道"
//                editBoolean = true
//                val isEdit = ChannelRvAdapter1.isEdit()
//                ChannelRvAdapter1.setEdit(!isEdit)
//                moreFlag.value = !isEdit
//                mAdapter1.notifyDataSetChanged()
////                mAdapter2.notifyDataSetChanged()
//            }
//        }
//
////        mAdapter1.setOnItemClickListener(object : ChannelRvAdapter1.OnItemClickListener {
////            override fun onItemLongClick(holder: BaseViewHolder) {
////                if (holder.adapterPosition != mAdapter1.fixedPosition) {
////                    itemTouchHelper?.startDrag(holder)
////                }
////            }
////        })
//        mAdapter1.setOnItemChildClickListener { adapter, view, position ->
//            //无效
//        }
//    }
//
//    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//        //判断是否是编辑态
//        //如果是编辑态则点击的时候处理增加/删除操作
//        //如果不是编辑态则弹出Toast提示，
//        if (ChannelRvAdapter1.isEdit()) {
//            var currentView: RecyclerView //currentView表示当前被点击的对象
//            var anotherView: RecyclerView //anotherView表示另一个对象
//            if (mAdapter1 == adapter) {
//                currentView = mRecyclerView1!!
//                anotherView = mRecyclerView2!!
//            } else {
//                currentView = mRecyclerView2!!
//                anotherView = mRecyclerView1!!
//            }
//            //计算起点，获取点击View的坐标
//            var startPos = IntArray(2)
//            var endPos = IntArray(2)
//            view.getLocationInWindow(startPos)
//
//            var currentAdapter = currentView.adapter as ChannelRvAdapter1
//            var anotherAdapter = anotherView.adapter as ChannelRvAdapter1
//            //标记点击的item待删除，并添加到anotherView中
//            Log.i("wltest", "onItemClick: " + currentAdapter.getItem(position))
//            anotherAdapter.setTranslating(true)
//            anotherAdapter.add(currentAdapter.setRemove(position))
//            var cloneView = ChannelViewModel.getCloneView(view)
//            currentView.post(Runnable {
//                (window.decorView as ViewGroup).addView(
//                    cloneView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//                var lastView = anotherView.getChildAt(anotherView.childCount - 1)
//                lastView.getLocationInWindow(endPos)
//                ChannelViewModel.moveAnimation(cloneView,startPos,endPos,animDuration,mAdapter1,mAdapter2)
//            })
//        } else {
//            Toast.makeText(this@NewChannelActivity,list1[position],Toast.LENGTH_SHORT).show()
//        }
//    }
}

