package com.bwie.myjingdongxiangmu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.activity.SouSuoActivity;
import com.bwie.myjingdongxiangmu.adapter.MyFenLeiYouAdapter;
import com.bwie.myjingdongxiangmu.adapter.MyFenLeiZuoAdapter;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiYouBean;
import com.bwie.myjingdongxiangmu.bean.MyFenLeiZuoBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterFenLei;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.MainFenLei;

import java.util.List;

/**
 * Created by admin on 2018/1/2.
 */

public class Frag_FenLei extends Fragment implements MainFenLei{

    private View view;
    private ListView recyclerzuo_linear;
    private RecyclerView recycleryou_grid;
    private RelativeLayout relative_layout;
    private ImageView image_fenlei;
    private int cid=1;
    private PresenterFenLei presenterFenLei;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_fenlei, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //搜索跳转
        relative_layout = view.findViewById(R.id.relative_layout);
        //左边recyclerview
        recyclerzuo_linear = view.findViewById(R.id.recyclerzuo_linear);
        //图片右边
        image_fenlei = view.findViewById(R.id.image_fenlei);
        //右边recyclerview
        recycleryou_grid = view.findViewById(R.id.recycleryou_grid);

        //搜索跳转点击事件
        relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到搜索页面
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
            }
        });

        presenterFenLei = new PresenterFenLei(this);
        presenterFenLei.getFenLeiZuoUrl(ApiUtil.fenleizuo);
        if (cid==1){
            String tu="http://120.27.23.105/images/category/shop.png";
            Glide.with(getActivity()).load(tu).into(image_fenlei);
            presenterFenLei.getFenLeiYouUrl(ApiUtil.fenleiyou,cid);
        }



    }

    @Override
    public void getFenLeiZuoBean(final MyFenLeiZuoBean myFenLeiZuoBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<MyFenLeiZuoBean.DataBean> list = myFenLeiZuoBean.getData();

                final MyFenLeiZuoAdapter adapter = new MyFenLeiZuoAdapter(getActivity(), list);
                recyclerzuo_linear.setAdapter(adapter);


                recyclerzuo_linear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int index=position;
                        adapter.getIndex(index);
                        adapter.notifyDataSetChanged();
                        recyclerzuo_linear.smoothScrollToPositionFromTop(position,(parent.getHeight()-view.getHeight())/2);

                        cid = myFenLeiZuoBean.getData().get(position).getCid();
                        presenterFenLei.getFenLeiYouUrl(ApiUtil.fenleiyou,cid);
                    }
                });
            }
        });
    }

    @Override
    public void getFenLeiYouBean(final MyFenLeiYouBean myFenLeiYouBean) {
        //回来接着做
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<MyFenLeiYouBean.DataBean> list2 = myFenLeiYouBean.getData();
                recycleryou_grid.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

                MyFenLeiYouAdapter adapter = new MyFenLeiYouAdapter(getActivity(), list2);
                recycleryou_grid.setAdapter(adapter);


            }
        });

    }
}
