package com.bwie.myjingdongxiangmu.model;

import android.util.Log;

import com.bwie.myjingdongxiangmu.bean.MyYongHuXXingXiBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterTou;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/11.
 */

public class ModelTou {
    private PresenterTou presenterTou;

    public ModelTou(PresenterTou presenterTou) {

        this.presenterTou = presenterTou;
    }

    public void getYongHuXingXiUrl(String yonghu, String uid) {
        Map<String, String> params=new HashMap<>();
        params.put("uid",uid+"");
        OkHttp3Util.doPost(yonghu, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    //Log.d("++++++++++++12q",json);
                    MyYongHuXXingXiBean myYongHuXXingXiBean = new Gson().fromJson(json, MyYongHuXXingXiBean.class);
                    presenterTou.getYongHuXingXi(myYongHuXXingXiBean);
                }
            }
        });
    }
}
