package com.bwie.myjingdongxiangmu.model;

import android.util.Log;

import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterDengCe;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/8.
 */

public class DengZhuCeModel {
    private PresenterDengCe presenterDengCe;

    public DengZhuCeModel(PresenterDengCe presenterDengCe) {

        this.presenterDengCe = presenterDengCe;
    }

    public void getZhuCeUrl(String zhuce, String zhucename, String zhucepwd) {
        Map<String, String> params=new HashMap<>();
        params.put("mobile",zhucename);
        params.put("password",zhucepwd);
        OkHttp3Util.doPost(zhuce, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyZhuCeBean myZhuCeBean = new Gson().fromJson(json, MyZhuCeBean.class);
                    presenterDengCe.getZhuCeBean(myZhuCeBean);
                }
            }
        });
    }

    public void getDengLuUrl(String denglu, String dengluname, String denglupwd) {
        Map<String, String> params=new HashMap<>();
        params.put("mobile",dengluname);
        params.put("password",denglupwd);
        OkHttp3Util.doPost(denglu, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyDengLuBean myDengLuBean = new Gson().fromJson(json, MyDengLuBean.class);
                    presenterDengCe.getDengLuBean(myDengLuBean);
//MVP解析  ApiUtil.denglu地址、路径、网址、接口
                }
            }
        });
    }

    public void getJiaRuGouWuCheUrl(String jiarugouwuche, String pid) {
        Map<String, String> params=new HashMap<>();
        params.put("pid",pid);
        params.put("source","android");
        OkHttp3Util.doPost(jiarugouwuche, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    //Log.d("++++++++++++++",json);
                    MyJiaRuGouWuCheBean myJiaRuGouWuCheBean = new Gson().fromJson(json, MyJiaRuGouWuCheBean.class);
                    presenterDengCe.getJiaRuGouWuCheBean(myJiaRuGouWuCheBean);
                }
            }
        });
    }
}
