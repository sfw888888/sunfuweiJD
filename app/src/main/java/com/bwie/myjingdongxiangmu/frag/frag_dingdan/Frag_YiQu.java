package com.bwie.myjingdongxiangmu.frag.frag_dingdan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.adapter.MyYiQuAdapter;
import com.bwie.myjingdongxiangmu.adapter.MydaizhiAdapter;
import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.presenter.RetrofitPresenter;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.util.RetrofitUtil;
import com.bwie.myjingdongxiangmu.view.RetrofitView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

public class Frag_YiQu extends Fragment implements RetrofitView{

    @BindView(R.id.recycler_yiqu)
    RecyclerView recyclerYiqu;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    @BindView(R.id.relative_bar)
    RelativeLayout relativeBar;
    Unbinder unbinder;
    private View view;
    private RetrofitPresenter retrofitPresenter;
    private int page = 1;
    private Map<String, String> map;
    private MyDataDingDanBean myDataDingDanBean;
    private MyYiQuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_yiqu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sharedPreferences= getActivity()
                .getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "");
        retrofitPresenter = new RetrofitPresenter(this);
        retrofitPresenter.attachView(this);
        map = new HashMap<>();
        map.put("uid", uid);
        map.put("status", "2");
        map.put("page", String.valueOf(page));

    }

    @Override
    public void onResume() {
        super.onResume();
        retrofitPresenter.getUrl(RetrofitUtil.getService().doGet(ApiUtil.dingdan2, map));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        retrofitPresenter.setJieYue();
        if (retrofitPresenter != null) {
            retrofitPresenter.detachView();
        }
    }

    @Override
    public void getResponseBean(ResponseBody responseBody) {
        try {
            //Log.d("+++++++++aaa", responseBody.string());

            myDataDingDanBean = new Gson().fromJson(responseBody
                    .string(), MyDataDingDanBean.class);
            myAdapter();
            relativeBar.setVisibility(View.GONE);
            smartRefresh.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {

                    page = 1;
                    retrofitPresenter.getUrl(RetrofitUtil.getService().doGet(ApiUtil.dingdan2, map));
                    smartRefresh.finishRefresh();
                }
            });
            smartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    //list.clear();
                    page++;
                    retrofitPresenter.getUrl(RetrofitUtil.getService().doGet(ApiUtil.dingdan2, map));
                    smartRefresh.finishLoadmore();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getError(Throwable throwable) {

    }
    public void myAdapter(){
        if (adapter==null){
            recyclerYiqu.setLayoutManager(new LinearLayoutManager(getActivity()
                    , LinearLayoutManager.VERTICAL,false));
            adapter = new MyYiQuAdapter(getActivity()
                    , myDataDingDanBean,retrofitPresenter,map);
            recyclerYiqu.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

}
