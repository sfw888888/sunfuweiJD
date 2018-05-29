package com.bwie.myjingdongxiangmu.model;

import android.app.Activity;
import android.content.SharedPreferences;

import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.presenter.Presenterdingdanq;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DingdanModel {
    private Presenterdingdanq presenter;
    private Presenterdingdanq presenterdingdanq;
    private String uid;

    public DingdanModel(Presenterdingdanq presenterdingdanq) {
        this.presenterdingdanq = presenterdingdanq;
    }

    public void getDingUrl(String dingdan, int page, String uid) {
        this.uid = uid;
        Map<String, String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("page",page+"");
        params.put("source","android");
        OkHttp3Util.doPost(dingdan, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyDataDingDanBean myDingDanBean = new Gson().fromJson(json, MyDataDingDanBean.class);
                    presenterdingdanq.getDingDanBean(myDingDanBean);

                }
            }
        });
    }

    public void getDingRefreshUrl(String dingdan, int page) {
        Map<String, String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("page",page+"");
        params.put("source","android");
        OkHttp3Util.doPost(dingdan, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyDataDingDanBean myDingDanBean = new Gson().fromJson(json, MyDataDingDanBean.class);
                    presenterdingdanq.getDingRefreshDanBean(myDingDanBean);

                }
            }
        });
    }
}
