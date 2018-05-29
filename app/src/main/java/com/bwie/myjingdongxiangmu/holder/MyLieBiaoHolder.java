package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;

/**
 * Created by admin on 2018/1/8.
 */

public class MyLieBiaoHolder extends RecyclerView.ViewHolder {

    public ImageView image_linaer;
    public TextView text_title;
    public TextView text_price;

    public MyLieBiaoHolder(View itemView) {
        super(itemView);
        image_linaer = itemView.findViewById(R.id.image_linaer);
        text_title = itemView.findViewById(R.id.text_title);
        text_price = itemView.findViewById(R.id.text_price);

    }
}