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
import com.bwie.myjingdongxiangmu.adapter.MyDingDanAdapter;
import com.bwie.myjingdongxiangmu.bean.MyDataDingDanBean;
import com.bwie.myjingdongxiangmu.presenter.Presenterdingdanq;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.Maindingdan;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class Frag_QuanBu extends Fragment implements Maindingdan {

    private List<MyDataDingDanBean.DataBean> list = new ArrayList<MyDataDingDanBean.DataBean>();
    private View view;
    private RecyclerView recycler_daizhi;
    private RelativeLayout relative_bar;
    private SmartRefreshLayout smart_refresh;
    private int page=1;
    private MyDingDanAdapter adapter;
    private Presenterdingdanq presenterdingdanq;
    private SharedPreferences sharedPreferences;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_quanbui, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler_daizhi = view.findViewById(R.id.recycler_daizhi);
        relative_bar = view.findViewById(R.id.relative_bar);
        smart_refresh = view.findViewById(R.id.smart_refresh);
        sharedPreferences = getActivity()
                .getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        presenterdingdanq = new Presenterdingdanq(this);


        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                page = 1;
                presenterdingdanq.getDingRefreshUrl(ApiUtil.dingdan,page);

            }
        });
        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //list.clear();
                page++;
                presenterdingdanq.getDingUrl(ApiUtil.dingdan,page,uid);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        relative_bar.setVisibility(View.VISIBLE);

        presenterdingdanq.getDingUrl(ApiUtil.dingdan,page,uid);
    }
    @Override
    public void getDingDanBean(final MyDataDingDanBean myDataDingDanBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDataDingDanBean!=null){
                    list.addAll(myDataDingDanBean.getData());
                    myAdapter();
                    relative_bar.setVisibility(View.GONE);

                    smart_refresh.finishLoadmore();
                }
            }
        });
    }

    @Override
    public void getDingRefreshDanBean(final MyDataDingDanBean myDingDanBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.clear();
                list.addAll(0,myDingDanBean.getData());
                myAdapter();
                smart_refresh.finishRefresh();
            }
        });

    }

    public void myAdapter(){
        if (adapter==null){
            recycler_daizhi.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
            adapter = new MyDingDanAdapter(getActivity(), list,presenterdingdanq,page);
            recycler_daizhi.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }


}
