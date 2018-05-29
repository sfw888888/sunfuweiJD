package com.bwie.myjingdongxiangmu.view;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/2.
 */

public interface RetrofitView {
    void getResponseBean(ResponseBody responseBody);
    void getError(Throwable throwable);
}
