package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.activity.ZhuCeActivity;
import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;
import com.bwie.myjingdongxiangmu.model.DengZhuCeModel;
import com.bwie.myjingdongxiangmu.view.DengZhuCe;

/**
 * Created by admin on 2018/1/8.
 */

public class PresenterDengCe implements PresenterDengZhuCe {
    private DengZhuCe dengZhuCe;
    private final DengZhuCeModel ceModel;

    public PresenterDengCe(DengZhuCe dengZhuCe) {
        ceModel = new DengZhuCeModel(this);
        this.dengZhuCe = dengZhuCe;
    }

    @Override
    public void getZhuCeBean(MyZhuCeBean myZhuCeBean) {
        dengZhuCe.getZhuCeBean(myZhuCeBean);
    }

    @Override
    public void getDengLuBean(MyDengLuBean myDengLuBean) {
        dengZhuCe.getDengLuBean(myDengLuBean);
    }

    @Override
    public void getJiaRuGouWuCheBean(MyJiaRuGouWuCheBean myJiaRuGouWuCheBean) {
        dengZhuCe.getJiaRuGouWuCheBean(myJiaRuGouWuCheBean);
    }

    public void getZhuCeUrl(String zhuce, String zhucename, String zhucepwd) {
        ceModel.getZhuCeUrl(zhuce,zhucename,zhucepwd);
    }

    public void getDengLuUrl(String denglu, String dengluname, String denglupwd) {
        ceModel.getDengLuUrl(denglu,dengluname,denglupwd);
    }

    public void getJiaRuGouWuCheUrl(String jiarugouwuche, String pid) {
        ceModel.getJiaRuGouWuCheUrl(jiarugouwuche,pid);
    }
}
