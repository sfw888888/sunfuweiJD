package com.bwie.myjingdongxiangmu.presenter;

import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.bean.MySelectBean;
import com.bwie.myjingdongxiangmu.frag.Frag_GouWuChe;
import com.bwie.myjingdongxiangmu.model.ModelSelect;
import com.bwie.myjingdongxiangmu.view.MainSelect;

/**
 * Created by admin on 2018/1/12.
 */

public class PresenterSelect implements PresenterPortSelect {
    private MainSelect mainSelect;
    private final ModelSelect modelSelect;

    public PresenterSelect(MainSelect mainSelect) {
        modelSelect = new ModelSelect(this);
        this.mainSelect = mainSelect;
    }

    @Override
    public void getSelectBean(MySelectBean mySelectBean) {
        mainSelect.getSelectBean(mySelectBean);
    }

    @Override
    public void getTuiJianBean(MyLunBoTuTuiJianMiao myLunBoTuTuiJianMiao) {

    }

    public void getSelectUrl(String chaxun, String uid) {
        modelSelect.getSelectUrl(chaxun,uid);
    }
}
