package com.bwie.myjingdongxiangmu.model;

import android.app.Activity;

import com.bwie.myjingdongxiangmu.bean.MySelectBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterSelect;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/12.
 */

public class ModelSelect {
    private PresenterSelect presenterSelect;

    public ModelSelect(PresenterSelect presenterSelect) {

        this.presenterSelect = presenterSelect;
    }
    public void getSelectUrl(String chaxun, String uid) {
        Map<String, String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("source","android");
        OkHttp3Util.doPost(chaxun, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MySelectBean mySelectBean = new Gson().fromJson(json, MySelectBean.class);
                    presenterSelect.getSelectBean(mySelectBean);
                }
            }
        });
    }

}
