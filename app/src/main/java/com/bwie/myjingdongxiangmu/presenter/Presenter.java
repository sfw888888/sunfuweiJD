package com.bwie.myjingdongxiangmu.presenter;


import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.model.Model;
import com.bwie.myjingdongxiangmu.view.Main;

/**
 * Created by admin on 2017/12/26.
 */

public class Presenter implements PresenterPort{

    private Main main;
    private final Model model;

    public Presenter(Main main) {
        model = new Model(this);
        this.main = main;
    }

    public void getGrid(String urlgrid) {
        model.getGridUrl(urlgrid);
    }

    @Override
    public void getStringJson(String json) {
        main.getStringJson(json);
    }

    @Override
    public void getStringList(String json) {
        main.getStringList(json);
    }

    @Override
    public void getLieBiaoBean(MySouSuoLieBiaoBean mySouSuoLieBiaoBean) {
        main.getLieBiaoBean(mySouSuoLieBiaoBean);
    }

    public void getList(String urllist) {
        model.getListUrl(urllist);
    }

    public void getLieBiaoUrl(String sousuoliebiao, String namesp, int page) {
        model.getLieBiaoUrl(sousuoliebiao,namesp,page);
    }

    public void getMyDest() {
        if (main!=null){
            main=null;
        }
    }
}
