package com.bwie.myjingdongxiangmu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.activity.MyDingDanActivity;
import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.bean.MyDingDanBean;
import com.bwie.myjingdongxiangmu.holder.MyDingDanHolder;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.presenter.Presenterdingdanq;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyDingDanAdapter extends RecyclerView.Adapter<MyDingDanHolder> {

    private final Context context;
    private final List<MyDataDingDanBean.DataBean> list;
    private final Presenterdingdanq presenterdingdanq;
    private final int page;

    public MyDingDanAdapter(Context context, List<MyDataDingDanBean.DataBean> list, Presenterdingdanq presenterdingdanq, int page) {

        this.context = context;
        this.list = list;
        this.presenterdingdanq = presenterdingdanq;
        this.page = page;
    }

    @Override
    public MyDingDanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dingdan,parent, false);
        MyDingDanHolder holder = new MyDingDanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyDingDanHolder holder, final int position) {
        SharedPreferences sharedPreferences= context
                .getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        final String uid = sharedPreferences.getString("uid", "");
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_price.setText("价格: "+list.get(position).getPrice());
        holder.text_tame.setText(list.get(position).getCreatetime());
        if (list.get(position).getStatus()==0){
            holder.text_daizhifu.setText("待支付");
            holder.text_daizhifu.setTextColor(Color.RED);
            holder.text_btn.setText("取消订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定要取消订单吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params=new HashMap<>();
                            params.put("uid",uid);
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(2));
                            OkHttp3Util.doPost(ApiUtil.genxin, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }
                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        Log.d("+++++++++响哥1", String.valueOf(list.get(position).getStatus()));
                                        //presenterdingdanq.getDingUrl(ApiUtil.dingdan,page);
                                        list.get(position).setStatus(2);
                                        ((MyDingDanActivity)context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();

                }
            });

        }else if (list.get(position).getStatus()==1){
            holder.text_daizhifu.setText("已支付");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
        }else if (list.get(position).getStatus()==2){
            holder.text_daizhifu.setText("已取消");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定循环利用吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params = new HashMap<>();
                            params.put("uid", uid);
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(0));
                            OkHttp3Util.doPost(ApiUtil.genxin, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        list.get(position).setStatus(0);
                                        //Log.d("+++++++++响哥", String.valueOf(list.get(position).getStatus()));
                                        //presenterdingdanq.getDingUrl(ApiUtil.dingdan, page);
                                        ((MyDingDanActivity)context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                notifyDataSetChanged();
                                            }
                                        });

                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
