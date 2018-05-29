package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.holder.MyTuiJianHolder;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;

import java.util.List;

/**
 * Created by admin on 2017/12/26.
 */

public class MyTuiJianAdapter extends RecyclerView.Adapter<MyTuiJianHolder> {
    private final Context context;
    private final List<MyLunBoTuTuiJianMiao.TuijianEntity.ListEntity> list1;
    private ItemListenner itemListenner;

    public MyTuiJianAdapter(Context context, List<MyLunBoTuTuiJianMiao.TuijianEntity.ListEntity> list1) {

        this.context = context;
        this.list1 = list1;
    }

    @Override
    public MyTuiJianHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuijian, parent, false);
        MyTuiJianHolder holder = new MyTuiJianHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyTuiJianHolder holder, final int position) {
        String[] split = list1.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_tuijian);
        holder.text_tuijian.setText(list1.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListenner.OnItemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemListenner.OnLongClick(position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setTuiJianItemClick(ItemListenner itemListenner){

        this.itemListenner = itemListenner;
    }
}
