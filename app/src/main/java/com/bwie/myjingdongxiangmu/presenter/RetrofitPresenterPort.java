package com.bwie.myjingdongxiangmu.presenter;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/2.
 */

public interface RetrofitPresenterPort {
    void getResponseBean(ResponseBody responseBody);
    void getError(Throwable throwable);
}
