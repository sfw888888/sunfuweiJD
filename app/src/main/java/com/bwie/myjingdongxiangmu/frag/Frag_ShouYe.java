package com.bwie.myjingdongxiangmu.frag;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.MainActivity;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.activity.ErWeiMaActivity;
import com.bwie.myjingdongxiangmu.activity.JiaRuGouWuCheActivity;
import com.bwie.myjingdongxiangmu.activity.Main2Activity;
import com.bwie.myjingdongxiangmu.activity.SouSuoActivity;
import com.bwie.myjingdongxiangmu.activity.SouSuoLieBiaoActivity;
import com.bwie.myjingdongxiangmu.activity.XiangQingActivity;
import com.bwie.myjingdongxiangmu.adapter.MyGridAdapter;
import com.bwie.myjingdongxiangmu.adapter.MyListAdapter;
import com.bwie.myjingdongxiangmu.adapter.MyTuiJianAdapter;
import com.bwie.myjingdongxiangmu.bean.MyGridZuoList;
import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.Main;
import com.bwie.myjingdongxiangmu.zidingyi.ObservableScrollView;
import com.google.gson.Gson;
import com.google.zxing.activity.CaptureActivity;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/2.
 */

public class Frag_ShouYe extends Fragment implements Main{
    private LinearLayout line;
    private ObservableScrollView scrollView;
    private int imageHeight=180; //设置渐变高度，一般为导航图片高度，自己控制


    private static final int REQUEST_CODE =1001 ;
    private View view;
    private RecyclerView recycler_grid;
    private RecyclerView recycler_list;
    private RecyclerView recycler_tuijian;
    private Banner mybanner;
    private List<String> listdata = new ArrayList<>();
    private List<MyLunBoTuTuiJianMiao.DataEntity> data;
    private TextView text_tame;
    private int t=59;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (t>=10){
                    text_tame.setText(t+"");
                    handler.sendEmptyMessageDelayed(0,1000);
                    t--;


                }else if (t>=0&&t<10){
                    text_tame.setText("0"+t);
                    handler.sendEmptyMessageDelayed(0,1000);
                    t--;

                }else {
                    t=59;
                }
            }
        }
    };
    private PullToRefreshScrollView refresh_list_view;
    private RelativeLayout relative_layout;
    private ImageView image_erweima;
    private SmartRefreshLayout smartrefresh_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_shouye, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mybanner = view.findViewById(R.id.mybanner);
        recycler_grid = view.findViewById(R.id.recycler_grid);
        recycler_list = view.findViewById(R.id.recycler_list);
        recycler_tuijian = view.findViewById(R.id.recycler_tuijian);
        text_tame = view.findViewById(R.id.text_tame);
        relative_layout = view.findViewById(R.id.relative_layout);
        smartrefresh_layout = view.findViewById(R.id.smartrefresh_layout);
        image_erweima = view.findViewById(R.id.image_erweima);


        //查找控件
        line= view.findViewById(R.id.line);
        scrollView= view.findViewById(R.id.scrollView);
//搜索框在布局最上面
        line.bringToFront();
//滑动监听
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
// 只是layout背景透明
                    line.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    line.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                }
            }
        });

        smartrefresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartrefresh_layout.finishRefresh(2000);
            }
        });
        smartrefresh_layout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smartrefresh_layout.finishLoadmore(2000);
            }
        });



        image_erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);*/
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }.start();
       // https://www.zhaoapi.cn/product/getProductDetail
        Presenter p = new Presenter(this);
        p.getGrid(ApiUtil.urlgrid);
        p.getList(ApiUtil.urllist);



        //跑马灯
        MarqueeView marqueeView = view.findViewById(R.id.marqueeView);
        final List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是魏从响。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：魏从响微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：魏从响");
        marqueeView.startWithList(info);

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getActivity(),info.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //shuaxin
        /*refresh_list_view.setMode(PullToRefreshListView.Mode.BOTH);
        ILoadingLayout startLabels = refresh_list_view
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = refresh_list_view.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");*/


        relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void getStringJson(final String json) {
        //Log.d("++++++++++",json);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyGridZuoList myGridZuoList = new Gson().fromJson(json, MyGridZuoList.class);
                List<MyGridZuoList.DataEntity> list = myGridZuoList.getData();
                recycler_grid.setLayoutManager(new GridLayoutManager(getActivity(),2, OrientationHelper.HORIZONTAL,false));
                MyGridAdapter adapter = new MyGridAdapter(getActivity(), list);
                recycler_grid.setAdapter(adapter);
            }
        });
    }

    @Override
    public void getStringList(final String json) {
        //Log.d("++++++++++",json);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyLunBoTuTuiJianMiao myLunBoTuTuiJianMiao = new Gson().fromJson(json, MyLunBoTuTuiJianMiao.class);
                data = myLunBoTuTuiJianMiao.getData();
                for (MyLunBoTuTuiJianMiao.DataEntity mydata: data){
                    listdata.add(mydata.getIcon());
                }
                mybanner.setBannerStyle(Banner.AUTOFILL_TYPE_LIST);
                mybanner.setIndicatorGravity(Banner.CENTER);
                mybanner.setDelayTime(3000);
                mybanner.isAutoPlay(true);
                mybanner.setImages(listdata);
                mybanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, int position) {
                        Toast.makeText(getActivity(),"魏从响",Toast.LENGTH_LONG).show();

                        if (data.get(position%listdata.size()).getType()==0){
                            Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                            intent.putExtra("url",data.get(position).getUrl());
                            startActivity(intent);

                        }else if (data.get(position%listdata.size()).getType()==1){
                            Toast.makeText(getActivity(),"错误",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                final List<MyLunBoTuTuiJianMiao.MiaoshaEntity.ListEntityX> list = myLunBoTuTuiJianMiao.getMiaosha().getList();
                recycler_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                MyListAdapter adapter = new MyListAdapter(getActivity(), list);
                recycler_list.setAdapter(adapter);

                final List<MyLunBoTuTuiJianMiao.TuijianEntity.ListEntity> list1 = myLunBoTuTuiJianMiao.getTuijian().getList();
                recycler_tuijian.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                MyTuiJianAdapter adapter1 = new MyTuiJianAdapter(getActivity(), list1);
                recycler_tuijian.setAdapter(adapter1);

                adapter1.setTuiJianItemClick(new ItemListenner() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(getActivity(), JiaRuGouWuCheActivity.class);
                        intent.putExtra("pid",list1.get(position).getPid()+"");
                        startActivity(intent);
                    }

                    @Override
                    public void OnLongClick(int position) {
                        Toast.makeText(getActivity(),"该点击了!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void getLieBiaoBean(MySouSuoLieBiaoBean mySouSuoLieBiaoBean) {

    }

}
