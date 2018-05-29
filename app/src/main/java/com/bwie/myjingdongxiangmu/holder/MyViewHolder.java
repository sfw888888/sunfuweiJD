package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;


/**
 * Created by admin on 2017/12/26.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView image_grid;
    public TextView text_title;

    public MyViewHolder(View itemView) {
        super(itemView);
        image_grid = itemView.findViewById(R.id.image_grid);
        text_title = itemView.findViewById(R.id.text_title);
    }
}
