package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by admin on 2017/12/29.
 */

public class MyLiShiAdapter extends BaseAdapter {
    private final Context context;
    private final int simple_expandable_list_item_1;
    private final List<String> list;

    public MyLiShiAdapter(Context context, int simple_expandable_list_item_1, List<String> list) {
        this.context = context;
        this.simple_expandable_list_item_1 = simple_expandable_list_item_1;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            //View view = View.inflate(context, R.layout.support_simple_spinner_dropdown_item, null);

        }
        return null;
    }
}
