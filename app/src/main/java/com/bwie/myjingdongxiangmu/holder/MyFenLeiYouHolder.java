package com.bwie.myjingdongxiangmu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;

/**
 * Created by admin on 2018/1/10.
 */

public class MyFenLeiYouHolder extends RecyclerView.ViewHolder {

    public RecyclerView recycler_fenleiyouzi;
    public TextView text_fenleiyou;

    public MyFenLeiYouHolder(View itemView) {
        super(itemView);
        text_fenleiyou = itemView.findViewById(R.id.text_fenleiyou);
        recycler_fenleiyouzi = itemView.findViewById(R.id.recycler_fenleiyouzi);
    }
}
