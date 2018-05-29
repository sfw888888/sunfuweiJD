package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;

/**
 * Created by admin on 2018/1/10.
 */

public class MyFenLeiYouZiHolder extends RecyclerView.ViewHolder {

    public ImageView image_fenleiyouzi;
    public TextView text_fenleiyouzi;

    public MyFenLeiYouZiHolder(View itemView) {
        super(itemView);
        image_fenleiyouzi = itemView.findViewById(R.id.image_fenleiyouzi);
        text_fenleiyouzi = itemView.findViewById(R.id.text_fenleiyouzi);
    }
}
