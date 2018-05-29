package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bwie.myjingdongxiangmu.R;


/**
 * Created by admin on 2017/12/26.
 */

public class MyListHolder extends RecyclerView.ViewHolder {

    public ImageView image_list;

    public MyListHolder(View itemView) {
        super(itemView);
        image_list = itemView.findViewById(R.id.image_list);
    }
}
