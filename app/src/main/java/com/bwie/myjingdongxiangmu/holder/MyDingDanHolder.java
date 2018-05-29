package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;

public class MyDingDanHolder extends RecyclerView.ViewHolder {

    public TextView text_title;
    public TextView text_daizhifu;
    public TextView text_price;
    public TextView text_tame;
    public TextView text_btn;

    public MyDingDanHolder(View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.text_title);
        text_daizhifu = itemView.findViewById(R.id.text_daizhifu);
        text_price = itemView.findViewById(R.id.text_price);
        text_tame = itemView.findViewById(R.id.text_tame);
        text_btn = itemView.findViewById(R.id.text_btn);

    }
}
