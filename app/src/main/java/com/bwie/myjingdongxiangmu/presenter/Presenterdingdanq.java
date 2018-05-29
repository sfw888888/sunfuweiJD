package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.model.DingdanModel;
import com.bwie.myjingdongxiangmu.view.Maindingdan;

public class Presenterdingdanq implements PresenterPortdingdan {


    private Maindingdan main;
    private final DingdanModel model;

    public Presenterdingdanq(Maindingdan main) {
        model = new DingdanModel(this);
        this.main = main;
    }

    @Override
    public void getDingDanBean(MyDataDingDanBean myDingDanBean) {
        main.getDingDanBean(myDingDanBean);
    }

    @Override
    public void getDingRefreshDanBean(MyDataDingDanBean myDingDanBean) {
        main.getDingRefreshDanBean(myDingDanBean);
    }


    public void getDingRefreshUrl(String dingdan, int page) {
        model.getDingRefreshUrl(dingdan,page);
    }

    public void getDingUrl(String dingdan, int page, String uid) {
        model.getDingUrl(dingdan,page,uid);
    }
}
