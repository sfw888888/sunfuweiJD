package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.holder.MyListHolder;

import java.util.List;

/**
 * Created by admin on 2017/12/26.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListHolder> {
    private final Context context;
    private final List<MyLunBoTuTuiJianMiao.MiaoshaEntity.ListEntityX> list;

    public MyListAdapter(Context context, List<MyLunBoTuTuiJianMiao.MiaoshaEntity.ListEntityX> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        MyListHolder holder = new MyListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyListHolder holder, int position) {
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
