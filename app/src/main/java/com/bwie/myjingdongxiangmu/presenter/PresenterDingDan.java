package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyCreateBean;
import com.bwie.myjingdongxiangmu.bean.MyDingDanBean;
import com.bwie.myjingdongxiangmu.frag.Frag_GouWuChe;
import com.bwie.myjingdongxiangmu.model.ModelCreateDingDan;
import com.bwie.myjingdongxiangmu.view.CreateDingDan;

/**
 * Created by admin on 2018/1/15.
 */

public class PresenterDingDan implements presenterCreateDingDanPort{


    private CreateDingDan createDingDan;
    private final ModelCreateDingDan modelCreateDingDan;

    public PresenterDingDan(CreateDingDan createDingDan) {
        modelCreateDingDan = new ModelCreateDingDan(this);
        this.createDingDan = createDingDan;
    }

    public void getCreateUrl(String create, String price, String uid) {
        modelCreateDingDan.getCreateUrl(create,price,uid);
    }

    @Override
    public void getCreateBean(MyCreateBean myCreateBean) {
        createDingDan.getCreateBean(myCreateBean);
    }

    @Override
    public void getDingDanBean(MyDingDanBean myDingDanBean) {

    }

}
