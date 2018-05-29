package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyCreateBean;
import com.bwie.myjingdongxiangmu.bean.MyDingDanBean;

/**
 * Created by admin on 2018/1/15.
 */

public interface presenterCreateDingDanPort {
    void getCreateBean(MyCreateBean myCreateBean);
    void getDingDanBean(MyDingDanBean myDingDanBean);
}
