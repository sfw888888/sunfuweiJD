package com.bwie.myjingdongxiangmu.model;

import com.bwie.myjingdongxiangmu.presenter.RetrofitPresenter;
import com.bwie.myjingdongxiangmu.presenter.RetrofitPresenterPort;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/2.
 */

public class RetrofitModel {
    private RetrofitPresenterPort retrofitPresenterPort;
    private Disposable d;
    public RetrofitModel(RetrofitPresenterPort retrofitPresenterPort) {

        this.retrofitPresenterPort = retrofitPresenterPort;
    }

    public void getUrl(Observable<ResponseBody> responseBodyObservable) {
        responseBodyObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                        RetrofitModel.this.d = d;
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        retrofitPresenterPort.getResponseBean(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setJieYue() {
        d.dispose();
    }
}
