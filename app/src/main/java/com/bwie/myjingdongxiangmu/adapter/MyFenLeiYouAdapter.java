package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.activity.SouSuoLieBiaoActivity;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiYouBean;
import com.bwie.myjingdongxiangmu.holder.MyFenLeiYouHolder;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;

import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */

public class MyFenLeiYouAdapter extends RecyclerView.Adapter<MyFenLeiYouHolder> {
    private final Context context;
    private final List<MyFenLeiYouBean.DataBean> list;

    public MyFenLeiYouAdapter(Context context, List<MyFenLeiYouBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyFenLeiYouHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fenleiyou, parent, false);
        MyFenLeiYouHolder holder = new MyFenLeiYouHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyFenLeiYouHolder holder, int position) {
        holder.text_fenleiyou.setText(list.get(position).getName());
        holder.recycler_fenleiyouzi.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
        final List<MyFenLeiYouBean.DataBean.ListBean> list1 = this.list.get(position).getList();
        MyFenLeiYouZiAdapter adapter = new MyFenLeiYouZiAdapter(context, list1);
        holder.recycler_fenleiyouzi.setAdapter(adapter);

        adapter.setFenLeiYouZi(new ItemListenner() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(context, SouSuoLieBiaoActivity.class);
                intent.putExtra("namesp",list1.get(position).getName());
                context.startActivity(intent);

            }

            @Override
            public void OnLongClick(int position) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
