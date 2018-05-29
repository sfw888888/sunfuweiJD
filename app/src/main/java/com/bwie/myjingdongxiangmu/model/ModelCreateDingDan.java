package com.bwie.myjingdongxiangmu.model;

import com.bwie.myjingdongxiangmu.bean.MyCreateBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterDingDan;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/15.
 */

public class ModelCreateDingDan {
    private PresenterDingDan presenterDingDan;

    public ModelCreateDingDan(PresenterDingDan presenterDingDan) {

        this.presenterDingDan = presenterDingDan;
    }

    public void getCreateUrl(String create, String price, String uid) {
        Map<String, String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("price",price);
        params.put("source","android");
        OkHttp3Util.doPost(create,params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyCreateBean myCreateBean = new Gson().fromJson(json, MyCreateBean.class);
                    presenterDingDan.getCreateBean(myCreateBean);
                }
            }
        });
    }
}
