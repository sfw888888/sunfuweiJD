package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyYongHuXXingXiBean;
import com.bwie.myjingdongxiangmu.frag.Frag_My;
import com.bwie.myjingdongxiangmu.model.ModelTou;
import com.bwie.myjingdongxiangmu.view.TouXiang;

/**
 * Created by admin on 2018/1/11.
 */

public class PresenterTou implements PreSenterTouXiang {
    private TouXiang touXiang;
    private final ModelTou modelTou;

    public PresenterTou(TouXiang touXiang) {
        modelTou = new ModelTou(this);
        this.touXiang = touXiang;
    }

    @Override
    public void getYongHuXingXi(MyYongHuXXingXiBean myYongHuXXingXiBean) {
        touXiang.getYongHuXingXi(myYongHuXXingXiBean);
    }

    public void getYongHuXingXiUrl(String yonghu, String uid) {
        modelTou.getYongHuXingXiUrl(yonghu,uid);
    }
}
