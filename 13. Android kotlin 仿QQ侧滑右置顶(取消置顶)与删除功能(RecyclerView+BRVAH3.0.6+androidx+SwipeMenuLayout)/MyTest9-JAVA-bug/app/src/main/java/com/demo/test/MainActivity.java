package com.demo.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.demo.test.myapplication36.R;

import java.util.ArrayList;
import java.util.List;


//import static com.example.administrator.myapplication36.R.id.btn1;

public class MainActivity extends Activity implements SlidingItemListViewAdapter.MySetTopInterface {

    private SlidingItemListView mListView;
    private SlidingItemListViewAdapter adapter;
    private List<SlidingItembean> list = new ArrayList<SlidingItembean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();

    }

    private void initEvent() {
        adapter = new SlidingItemListViewAdapter(MainActivity.this, list,
                mListView.getRightViewWidth());
        mListView.setAdapter(adapter);
        adapter.setMySetTopInterface(this);
        // mListView.setSelection(position);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this,
                        "item onclick " + list.get(position).getNum(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            SlidingItembean slidingItembean = null;
            if (i % 3 == 0) {
                slidingItembean = new SlidingItembean(String.valueOf(i),
                        "你会不会忽然的出现", "/var/mobile/Contalners/Application",
                        "置顶");
            } else if (i % 3 == 1) {
                slidingItembean = new SlidingItembean(String.valueOf(i),
                        "在街角的咖啡店", "/var/mobile/Contalners/Application",
                        "置顶");
            } else {
                slidingItembean = new SlidingItembean(String.valueOf(i),
                        "我会带着笑脸，和你，坐着聊聊天", "/var/mobile/Contalners/Application",
                        "置顶");
            }

            list.add(slidingItembean);
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mListView = (SlidingItemListView) findViewById(R.id.listview);

    }

    @Override
    public void Onclick_ll_setTop_ll_right(View view, int position) {

        if (list.get(position).getSetTop().equals("置顶")) {

            setTop(position);

        } else if (list.get(position).getSetTop().equals("取消置顶")) {

            unSetTop(position);

        }

    }

    /**
     * 取消置顶
     *
     * @param position
     */
    private void unSetTop(int position) {
        boolean isAdd = false;
        /** 差值 */
        int min = 9999999;
        /** 当前position的数值 */
        int num;
        // 差值最小处的行数
        int j = 0;
        int num2 = 0;
        int jumpNum = 0;
        list.get(position).setSetTop("置顶");
        num = Integer.parseInt(list.get(position).getNum());
        // list长度为2特殊处理
        if (list.size() == 2) {
            // 第一行确定为取消置顶
            if (list.get(1).getSetTop().equals("取消置顶")) {
                if (position == 0) {
                    if (num == 0) {
                        list.add(2, list.get(position));
                    }
                    if (num == 1) {
                        list.add(2, list.get(position));
                    }
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                } else {
                    list.add(2, list.get(position));
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            } else {
                if (num == 0) {
                    list.add(1, list.get(position));
                }
                if (num == 1) {
                    list.add(2, list.get(position));
                }
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        } else {

            for (int i = 0; i < list.size(); i++) {

                if (num > Integer.parseInt(list.get(i).getNum())
                        && num < Integer.parseInt(list.get(i + 1).getNum())) {
                    list.add(i + 1, list.get(position));
                    isAdd = true;
                    break;
                }
            }

            // 如果没有比自己小的值 例如0 则isAdd=false
            // 遍历list 寻找差值最小的地方插入list
            if (!isAdd) {
                for (int i = 0; i < list.size(); i++) {

                    if (i == position || list.get(i).getSetTop().equals("取消置顶")) {
                        // 排除与自身相比较
                        // 排除置顶item比较
                        Log.i("TAG", "调过" + i);
                        jumpNum++;
                        if (jumpNum == list.size()) {
                            j = list.size();
                        }

                        continue;
                    }

                    num2 = Integer.parseInt(list.get(i).getNum());
                    if (num2 - num < min) {
                        min = num2 - num;
                        // 记录行号
                        j = i;
                        Log.i("TAG", "插入行数J=" + j);
                    }
                }
                // 遍历完成后拿到差值min
                int number = min + num;
                list.add(j, list.get(position));
                Log.i("TAG", "*********插入行数J=" + j);
            }

            list.remove(position);
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * 置顶
     *
     * @param position
     */
    private void setTop(int position) {
        list.get(position).setSetTop("取消置顶");
        list.add(0, list.get(position));
        // 置顶后list.size增加一 所以要position+1
        list.remove(position + 1);
        adapter.notifyDataSetChanged();
    }
}
