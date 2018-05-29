package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;

/**
 * Created by admin on 2017/12/26.
 */

public interface PresenterPort {
    public void getStringJson(String json);
    public void getStringList(String json);
    void getLieBiaoBean(MySouSuoLieBiaoBean mySouSuoLieBiaoBean);
}
