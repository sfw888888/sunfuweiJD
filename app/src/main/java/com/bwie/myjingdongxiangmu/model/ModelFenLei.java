package com.bwie.myjingdongxiangmu.model;

import android.util.Log;

import com.bwie.myjingdongxiangmu.bean.MyFenLeiYouBean;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiZuoBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterFenLei;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/10.
 */

public class ModelFenLei {
    private PresenterFenLei presenterFenLei;

    public ModelFenLei(PresenterFenLei presenterFenLei) {

        this.presenterFenLei = presenterFenLei;
    }

    public void getFenLeiZuoUrl(String fenleizuo) {
        OkHttp3Util.doGet(fenleizuo, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyFenLeiZuoBean myFenLeiZuoBean = new Gson().fromJson(json, MyFenLeiZuoBean.class);
                    presenterFenLei.getFenLeiZuoBean(myFenLeiZuoBean);

                }
            }
        });


    }

    public void getFenLeiYouUrl(String fenleiyou, int cid) {
        Map<String, String> params=new HashMap<>();
        params.put("cid",cid+"");
        params.put("source","android");
        OkHttp3Util.doPost(fenleiyou, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    Log.d("+++++++++++weicx",json);
                    MyFenLeiYouBean myFenLeiYouBean = new Gson().fromJson(json, MyFenLeiYouBean.class);
                    presenterFenLei.getFenLeiYouBean(myFenLeiYouBean);
                }
            }
        });
    }
}
