package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;

/**
 * Created by admin on 2018/1/8.
 */

public interface PresenterDengZhuCe {
    void getZhuCeBean(MyZhuCeBean myZhuCeBean);
    void getDengLuBean(MyDengLuBean myDengLuBean);
    void getJiaRuGouWuCheBean(MyJiaRuGouWuCheBean myJiaRuGouWuCheBean);
}
