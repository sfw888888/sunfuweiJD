package com.bwie.myjingdongxiangmu.model;


import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2017/12/26.
 */

public class Model {

    private Presenter presenter;

    public Model(Presenter presenter) {
        this.presenter = presenter;
    }

    public void getGridUrl(String urlgrid) {
        OkHttp3Util.doGet(urlgrid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    presenter.getStringJson(json);
                }
            }
        });
    }

    public void getListUrl(String urllist) {
        OkHttp3Util.doGet(urllist, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    presenter.getStringList(json);
                }
            }
        });
    }

    public void getLieBiaoUrl(String sousuoliebiao, String namesp, int page) {
        Map<String, String> params=new HashMap<>();
        params.put("keywords",namesp);
        params.put("page",page+"");
        params.put("source","android");
        OkHttp3Util.doPost(sousuoliebiao, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MySouSuoLieBiaoBean mySouSuoLieBiaoBean = new Gson().fromJson(json, MySouSuoLieBiaoBean.class);
                    presenter.getLieBiaoBean(mySouSuoLieBiaoBean);
                }
            }
        });
    }
}
