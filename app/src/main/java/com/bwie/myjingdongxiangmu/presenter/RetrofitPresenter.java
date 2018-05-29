package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.frag.Frag_My;
import com.bwie.myjingdongxiangmu.model.Model;
import com.bwie.myjingdongxiangmu.model.RetrofitModel;
import com.bwie.myjingdongxiangmu.view.RetrofitView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/2.
 */

public class RetrofitPresenter extends BasePresenter implements RetrofitPresenterPort{

    private RetrofitView retrofitView;
    private final RetrofitModel retrofitModel;

    public RetrofitPresenter(RetrofitView retrofitView) {
        retrofitModel = new RetrofitModel(this);
        this.retrofitView = retrofitView;
    }

    @Override
    public void getResponseBean(ResponseBody responseBody) {
        retrofitView.getResponseBean(responseBody);
    }

    @Override
    public void getError(Throwable throwable) {
        retrofitView.getError(throwable);
    }

    public void getUrl(Observable<ResponseBody> responseBodyObservable) {
        retrofitModel.getUrl(responseBodyObservable);
    }

    public void setJieYue() {
        retrofitModel.setJieYue();
    }
}
