package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiZuoBean;

import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */

public class MyFenLeiZuoAdapter extends BaseAdapter {
    private final Context context;
    private final List<MyFenLeiZuoBean.DataBean> list;
    private int index;

    public MyFenLeiZuoAdapter(Context context, List<MyFenLeiZuoBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            view=View.inflate(context,R.layout.item_fenleizuo,null);
            holder=new ViewHolder();
            holder.text_zuolei = view.findViewById(R.id.text_zuolei);
            holder.text_xian = view.findViewById(R.id.text_xian);
            view.setTag(holder);

        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.text_zuolei.setText(list.get(position).getName());

        if (position==index){
            holder.text_zuolei.setTextColor(Color.RED);
            holder.text_xian.setBackgroundColor(Color.RED);
        }else {
            holder.text_zuolei.setTextColor(Color.GRAY);
            holder.text_xian.setBackgroundColor(Color.GRAY);
        }


        return view;
    }
    class ViewHolder{
        TextView text_zuolei;
        TextView text_xian;
    }

    public void getIndex(int index) {
        this.index = index;
    }
}
