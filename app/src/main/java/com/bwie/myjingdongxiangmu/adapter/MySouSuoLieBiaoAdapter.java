package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.holder.MyLieBiaoHolder;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;

import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */

public class MySouSuoLieBiaoAdapter extends RecyclerView.Adapter<MyLieBiaoHolder> {
    private final Context context;
    private final List<MySouSuoLieBiaoBean.DataBean> list;
    private ItemListenner itemListenner;

    public MySouSuoLieBiaoAdapter(Context context, List<MySouSuoLieBiaoBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyLieBiaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_linaer, parent, false);
        MyLieBiaoHolder holder = new MyLieBiaoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyLieBiaoHolder holder, final int position) {
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_linaer);
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_price.setText("折扣价:"+list.get(position).getPrice());
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

    public void setItemListenner(ItemListenner itemListenner){

        this.itemListenner = itemListenner;
    }
}
