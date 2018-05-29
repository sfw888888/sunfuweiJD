package com.bwie.myjingdongxiangmu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.holder.MyDingDanHolder;

import java.util.List;

/**
 * Created by admin on 2018/4/2.
 */

public class MyYiZhiAdapter extends RecyclerView.Adapter<MyDingDanHolder> {
    private final Context context;
    private final MyDataDingDanBean myDataDingDanBean;

    public MyYiZhiAdapter(Context context, MyDataDingDanBean myDataDingDanBean) {

        this.context = context;
        this.myDataDingDanBean = myDataDingDanBean;
    }

    @Override
    public MyDingDanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R
                .layout.item_dingdan,parent, false);
        MyDingDanHolder holder = new MyDingDanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyDingDanHolder holder, int position) {
        List<MyDataDingDanBean.DataBean> list = myDataDingDanBean.getData();
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_price.setText("价格: "+list.get(position).getPrice());
        holder.text_tame.setText(list.get(position).getCreatetime());
        if (list.get(position).getStatus()==1){
            holder.text_daizhifu.setText("已支付");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
        }
    }

    @Override
    public int getItemCount() {
        return myDataDingDanBean.getData().size();
    }
}
