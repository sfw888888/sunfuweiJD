package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyGridZuoList;
import com.bwie.myjingdongxiangmu.holder.MyViewHolder;

import java.util.List;

/**
 * Created by admin on 2017/12/26.
 */

public class MyGridAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private final Context context;
    private final List<MyGridZuoList.DataEntity> list;

    public MyGridAdapter(Context context, List<MyGridZuoList.DataEntity> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.image_grid);
        holder.text_title.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
