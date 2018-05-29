package com.bwie.myjingdongxiangmu.presenter;

import android.support.v4.app.FragmentActivity;

import com.bwie.myjingdongxiangmu.bean.MyFenLeiYouBean;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiZuoBean;
import com.bwie.myjingdongxiangmu.model.ModelFenLei;
import com.bwie.myjingdongxiangmu.view.MainFenLei;

/**
 * Created by admin on 2018/1/10.
 */

public class PresenterFenLei implements PresenterPortFenLei{

    private MainFenLei mainFenLei;
    private final ModelFenLei modelFenLei;

    public PresenterFenLei(MainFenLei mainFenLei) {
        modelFenLei = new ModelFenLei(this);
        this.mainFenLei = mainFenLei;
    }

    @Override
    public void getFenLeiZuoBean(MyFenLeiZuoBean myFenLeiZuoBean) {
        mainFenLei.getFenLeiZuoBean(myFenLeiZuoBean);
    }

    @Override
    public void getFenLeiYouBean(MyFenLeiYouBean myFenLeiYouBean) {
        mainFenLei.getFenLeiYouBean(myFenLeiYouBean);
    }

    public void getFenLeiZuoUrl(String fenleizuo) {
        modelFenLei.getFenLeiZuoUrl(fenleizuo);
    }

    public void getFenLeiYouUrl(String fenleiyou, int cid) {
        modelFenLei.getFenLeiYouUrl(fenleiyou,cid);
    }
}
