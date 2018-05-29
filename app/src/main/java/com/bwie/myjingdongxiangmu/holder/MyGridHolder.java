package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;


/**
 * Created by admin on 2018/1/6.
 */

public class MyGridHolder extends RecyclerView.ViewHolder{

    public ImageView image_grid;
    public TextView grid_title;
    public TextView grid_price;

    public MyGridHolder(View itemView) {
        super(itemView);
        image_grid = itemView.findViewById(R.id.image_grid);
        grid_title = itemView.findViewById(R.id.grid_title);
        grid_price = itemView.findViewById(R.id.grid_price);
    }
}
