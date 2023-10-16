package com.demo.test;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.test.myapplication36.R;

public class SlidingItemListViewAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<SlidingItembean> list;

    private int mRightViewWidth;

    public SlidingItemListViewAdapter(Context mContext,
                                      List<SlidingItembean> list, int mRightViewWidth) {
        super();
        this.mContext = mContext;
        this.list = list;
        this.mRightViewWidth = mRightViewWidth;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        onClick listener;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_sliding_listview,
                    null);
            viewHolder = new ViewHolder();
            listener = new onClick();// 实例化

            //*****新增代码*****
            viewHolder.ll = (LinearLayout) convertView.findViewById(R.id.ll);


            viewHolder.Re_left = (RelativeLayout) convertView
                    .findViewById(R.id.Re_left);
            viewHolder.ll_right = (LinearLayout) convertView
                    .findViewById(R.id.ll_right);
            viewHolder.num = (TextView) convertView
                    .findViewById(R.id.tv_num_Re_left);
            viewHolder.name = (TextView) convertView
                    .findViewById(R.id.tv_name_Re_left);
            viewHolder.path = (TextView) convertView
                    .findViewById(R.id.tv_path_Re_left);
            viewHolder.play = (ImageView) convertView
                    .findViewById(R.id.img_play_Re_left);
            viewHolder.setTop = (TextView) convertView.findViewById(R.id.tv_setTop);
            viewHolder.ll_delete = (LinearLayout) convertView
                    .findViewById(R.id.ll_delete_ll_right);
            viewHolder.ll_setTop = (LinearLayout) convertView
                    .findViewById(R.id.ll_setTop_ll_right);
            viewHolder.ll_setTop.setOnClickListener(listener);// 监听
            viewHolder.ll_delete.setOnClickListener(listener);// 监听
            viewHolder.play.setOnClickListener(listener);// 监听
            convertView.setTag(viewHolder.play.getId(), listener);// 设置tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            listener = (onClick) convertView.getTag(viewHolder.play.getId());// 获取实例
        }

        listener.setPosition(position);// 传递position

        // 设置布局参数

        LayoutParams lp_left = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        viewHolder.Re_left.setLayoutParams(lp_left);

        LayoutParams lp_right = new LayoutParams(mRightViewWidth,
                LayoutParams.MATCH_PARENT);
        viewHolder.ll_right.setLayoutParams(lp_right);

        SlidingItembean slidingItembean = list.get(position);
        viewHolder.num.setText(slidingItembean.getNum());
        viewHolder.name.setText(slidingItembean.getName());
        viewHolder.path.setText(slidingItembean.getPath());
        viewHolder.setTop.setText(slidingItembean.getSetTop());

        //*****新增代码*****
        if (slidingItembean.getSetTop().equals("取消置顶")){
            viewHolder.ll.setBackgroundColor(Color.parseColor("#ffffbb33"));
        }else if (slidingItembean.getSetTop().equals("置顶")){
            viewHolder.ll.setBackgroundColor(Color.parseColor("#ff99cc00"));
        }


        return convertView;
    }

    class onClick implements OnClickListener {

        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_play_Re_left:
                    Toast.makeText(mContext, "play--->position=" + position,
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_delete_ll_right:
                    list.remove(position);
                    SlidingItemListViewAdapter.this.notifyDataSetChanged();

                    break;
                case R.id.ll_setTop_ll_right:

                    if (mySetTopInterface != null) {
                        mySetTopInterface.Onclick_ll_setTop_ll_right(v, position);
                    } else {
                        Toast.makeText(mContext, "mySetTopInterface==null",
                                Toast.LENGTH_SHORT).show();
                    }

                    break;

                default:
                    break;
            }

        }
    }

    MySetTopInterface mySetTopInterface;

    public interface MySetTopInterface {
        void Onclick_ll_setTop_ll_right(View view, int position);
    }

    public void setMySetTopInterface(MySetTopInterface mySetTopInterface) {
        this.mySetTopInterface = mySetTopInterface;
    }

    static class ViewHolder {

        //*****新增代码*****
        LinearLayout ll;


        RelativeLayout Re_left;
        LinearLayout ll_right;

        LinearLayout ll_delete;
        LinearLayout ll_setTop;

        TextView num;
        TextView name;
        TextView path;

        ImageView play;

        TextView setTop;
    }

}
