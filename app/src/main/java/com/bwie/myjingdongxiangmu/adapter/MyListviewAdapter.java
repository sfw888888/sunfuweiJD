package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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

public class MyListviewAdapter extends BaseAdapter {
    private final Context context;
    private final List<MyFenLeiZuoBean.DataBean> list;

    public MyListviewAdapter(Context context, List<MyFenLeiZuoBean.DataBean> list) {

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
        viewHolder holder;
        if (view==null){
            //view=View.inflate(context, R.layout.item_fenleizuo,null);
            holder=new viewHolder();
            //holder.text_titlefenleizuo=view.findViewById(R.id.text_titlefenleizuo);
            //view.setTag(holder);

        }else {
            //holder= (viewHolder) view.getTag();
        }
        //holder.text_titlefenleizuo.setText(list.get(position).getName());
        return view;
    }
    class viewHolder{
        //TextView text_titlefenleizuo;
    }
}
