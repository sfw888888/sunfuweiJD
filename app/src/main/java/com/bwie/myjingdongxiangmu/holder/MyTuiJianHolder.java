package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;


/**
 * Created by admin on 2017/12/26.
 */

public class MyTuiJianHolder extends RecyclerView.ViewHolder {

    public ImageView image_tuijian;
    public TextView text_tuijian;

    public MyTuiJianHolder(View itemView) {
        super(itemView);
        image_tuijian = itemView.findViewById(R.id.image_tuijian);
        text_tuijian = itemView.findViewById(R.id.text_tuijian);
    }
}
