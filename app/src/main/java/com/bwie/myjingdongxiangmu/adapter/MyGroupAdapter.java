package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.holder.MyGridHolder;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;

import java.util.List;

/**
 * Created by admin on 2018/1/6.
 */

public class MyGroupAdapter extends RecyclerView.Adapter<MyGridHolder> {
    private final Context context;
    private final List<MySouSuoLieBiaoBean.DataBean> list;
    private ItemListenner itemListenner;

    public MyGroupAdapter(Context context, List<MySouSuoLieBiaoBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grider, parent, false);
        MyGridHolder holder = new MyGridHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyGridHolder holder, final int position) {
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_grid);
        holder.grid_title.setText(list.get(position).getTitle());
        holder.grid_price.setText("折扣价:"+list.get(position).getPrice());

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
        return list.size();
    }

    public void setItemClicked(ItemListenner itemListenner){

        this.itemListenner = itemListenner;
    }
}
