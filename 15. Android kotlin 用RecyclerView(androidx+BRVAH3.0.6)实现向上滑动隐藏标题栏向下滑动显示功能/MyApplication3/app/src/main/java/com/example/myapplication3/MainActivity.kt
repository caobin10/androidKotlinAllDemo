package com.example.myapplication3

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication3.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list = ArrayList<String>()

    private var mTouchSlop = 0
    private var mFirstY = 0f
    private var mCurrentY = 0f

    private val mAdapter by lazy {
        RvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTouchSlop = ViewConfiguration.get(this@MainActivity).scaledTouchSlop
        init()
        showHideTitleBar(true);
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter

        for (i in 0..50) {
            list.add("第 ${i + 1} 行")
        }

        mAdapter.setList(list)

        recyclerView.setOnTouchListener(View.OnTouchListener{ v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    mFirstY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    mCurrentY = event.y
                    if (mCurrentY - mFirstY > mTouchSlop) {

                        // 下滑 显示titleBar
                        showHideTitleBar(true)
                    }else if (mFirstY - mCurrentY > mTouchSlop){

                        // 上滑 隐藏titleBar
                        showHideTitleBar(false)
                    }
                }
                MotionEvent.ACTION_UP ->{

                }
            }
            return@OnTouchListener false
        })
    }

    private var mAnimatorTitle: Animator? = null
    private var mAnimatorContent: Animator? = null

    private fun showHideTitleBar(tag: Boolean) {
        if (mAnimatorTitle != null && mAnimatorTitle!!.isRunning) {
            mAnimatorTitle!!.cancel()
        }
        if (mAnimatorContent != null && mAnimatorContent!!.isRunning) {
            mAnimatorContent!!.cancel()
        }
        if (tag) {
//            mAnimatorTitle = ObjectAnimator.ofFloat(rl_title, "translationY", rl_title.getTranslationY(), 0)

            mAnimatorTitle = ObjectAnimator.ofFloat(rl_title, "translationY", rl_title.translationX,0f)

//            mAnimatorContent = ObjectAnimator.ofFloat(recyclerView, "translationY", recyclerView.getTranslationY(), getResources().getDimension(R.dimen.title_height))
            mAnimatorContent = ObjectAnimator.ofFloat(recyclerView, "translationY", recyclerView.translationY, resources.getDimension(R.dimen.title_height))

        } else {
//            mAnimatorTitle = ObjectAnimator.ofFloat(rl_title, "translationY", rl_title.getTranslationY(), -rl_title.getHeight())
            mAnimatorTitle = ObjectAnimator.ofFloat(rl_title, "translationY", rl_title.translationY, -resources.getDimension(R.dimen.title_height))

//            mAnimatorContent = ObjectAnimator.ofFloat(recyclerView, "translationY", recyclerView.getTranslationY(), 0)
            mAnimatorContent = ObjectAnimator.ofFloat(recyclerView, "translationY", recyclerView.translationY, 0f)

        }
        mAnimatorTitle!!.start();
        mAnimatorContent!!.start();
    }

//    public class MainActivity extends AppCompatActivity {
//
//        private RelativeLayout mTitle;
//        ArrayList<String> list = new ArrayList<String>();
//        ArrayAdapter adapter;
//        private ListView mListView;
//        private int mTouchSlop;
//        private float mFirstY;
//        private float mCurrentY;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            setContentView(R.layout.activity_main);
//            mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
//
//            initViews();
//            showHideTitleBar(true);
//        }
//
//        private void initViews() {
//            mListView = (ListView) findViewById(R.id.listview);
//            mTitle = (RelativeLayout) findViewById(R.id.title);
//
//            for (int i = 0; i < 100; i++) {  //设置数据
//            list.add("第  " + (i + 1) + "  行");
//        }
//
//            adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, android.R.id.text1, list);
//            mListView.setAdapter(adapter);
//            mListView.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                        mFirstY = event.getY();
//                        break;
//                        case MotionEvent.ACTION_MOVE:
//                        mCurrentY = event.getY();
//                        if (mCurrentY - mFirstY > mTouchSlop) {
//                            System.out.println("mtouchislop:" + mTouchSlop);
//                            // 下滑 显示titleBar
//                            showHideTitleBar(true);
//                        } else if (mFirstY - mCurrentY > mTouchSlop) {
//                            // 上滑 隐藏titleBar
//                            showHideTitleBar(false);
//                        }
//                        break;
//                        case MotionEvent.ACTION_UP:
//                        break;
//                    }
//                    return false;
//                }
//            });
//        }
//
//        private Animator mAnimatorTitle;
//        private Animator mAnimatorContent;
//
//        private void showHideTitleBar(boolean tag) {
//            if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) {
//                mAnimatorTitle.cancel();
//            }
//            if (mAnimatorContent != null && mAnimatorContent.isRunning()) {
//                mAnimatorContent.cancel();
//            }
//            if (tag) {
//                mAnimatorTitle = ObjectAnimator.ofFloat(mTitle, "translationY", mTitle.getTranslationY(), 0);
//                mAnimatorContent = ObjectAnimator.ofFloat(mListView, "translationY", mListView.getTranslationY(), getResources().getDimension(R.dimen.title_height));
//
//            } else {
//                mAnimatorTitle = ObjectAnimator.ofFloat(mTitle, "translationY", mTitle.getTranslationY(), -mTitle.getHeight());
//                mAnimatorContent = ObjectAnimator.ofFloat(mListView, "translationY", mListView.getTranslationY(), 0);
//            }
//            mAnimatorTitle.start();
//            mAnimatorContent.start();
//        }
//    }
}