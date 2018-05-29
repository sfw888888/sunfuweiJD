package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiYouBean;
import com.bwie.myjingdongxiangmu.holder.MyFenLeiYouZiHolder;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;

import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */

public class MyFenLeiYouZiAdapter extends RecyclerView.Adapter<MyFenLeiYouZiHolder> {
    private final Context context;
    private final List<MyFenLeiYouBean.DataBean.ListBean> list;
    private ItemListenner itemListenner;

    public MyFenLeiYouZiAdapter(Context context, List<MyFenLeiYouBean.DataBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyFenLeiYouZiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fenleiyouzi, parent, false);
        MyFenLeiYouZiHolder holder = new MyFenLeiYouZiHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyFenLeiYouZiHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.image_fenleiyouzi);
        holder.text_fenleiyouzi.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListenner.OnItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFenLeiYouZi(ItemListenner itemListenner){

        this.itemListenner = itemListenner;
    }
}
