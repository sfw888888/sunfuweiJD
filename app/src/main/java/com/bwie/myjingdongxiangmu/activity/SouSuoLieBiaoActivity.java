package com.bwie.myjingdongxiangmu.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.adapter.MyGroupAdapter;
import com.bwie.myjingdongxiangmu.adapter.MySouSuoLieBiaoAdapter;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.Main;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SouSuoLieBiaoActivity extends AppCompatActivity implements Main{
    private List<MySouSuoLieBiaoBean.DataBean> listad=new ArrayList<>();
    private RecyclerView recycler_view;
    private ImageView image_fanhuishouye;
    private RelativeLayout relative_sousuoliebiao;
    private TextView sousuo_liebiao;
    private ImageView image_qiehuan;
    private int l=0;
    private int page=1;
    private String namesp;
    private SmartRefreshLayout smartrefresh_liebiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_sou_suo_lie_biao);
        smartrefresh_liebiao = findViewById(R.id.smartrefresh_liebiao);

        image_fanhuishouye = findViewById(R.id.image_fanhuishouye);
        image_qiehuan = findViewById(R.id.image_qiehuan);
        sousuo_liebiao = findViewById(R.id.sousuo_liebiao);

        relative_sousuoliebiao = findViewById(R.id.relative_sousuoliebiao);

        recycler_view = findViewById(R.id.xrecycler_view);

        namesp = getIntent().getStringExtra("namesp");
        sousuo_liebiao.setText(namesp);
        Presenter presenter = new Presenter(SouSuoLieBiaoActivity.this);
        presenter.getLieBiaoUrl(ApiUtil.sousuoliebiao, namesp,page);

        //返回首页
        image_fanhuishouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //搜索页面
        relative_sousuoliebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SouSuoLieBiaoActivity.this, SouSuoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //切换布局
        image_qiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!namesp.equals("")){
                    if (l==0){
                        image_qiehuan.setImageResource(R.drawable.kind_liner);
                        l=1;
                    }else if (l==1){
                        image_qiehuan.setImageResource(R.drawable.kind_grid);
                        l=0;
                    }
                    Presenter presenter = new Presenter(SouSuoLieBiaoActivity.this);
                    presenter.getLieBiaoUrl(ApiUtil.sousuoliebiao,namesp,page);
                }else {
                    Toast.makeText(SouSuoLieBiaoActivity.this,"++++++",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void getStringJson(String json) {

    }

    @Override
    public void getStringList(String json) {

    }

    @Override
    public void getLieBiaoBean(final MySouSuoLieBiaoBean mySouSuoLieBiaoBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listad.addAll(mySouSuoLieBiaoBean.getData());
                if (l==0){
                    //final List<MySouSuoLieBiaoBean.DataBean> list = mySouSuoLieBiaoBean.getData();

                    recycler_view.setLayoutManager(new LinearLayoutManager(
                            SouSuoLieBiaoActivity.this,LinearLayoutManager.VERTICAL,false));
                    MySouSuoLieBiaoAdapter adapter = new MySouSuoLieBiaoAdapter(SouSuoLieBiaoActivity.this, listad);
                    recycler_view.setAdapter(adapter);

                    smartrefresh_liebiao.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            smartrefresh_liebiao.finishRefresh(2000);
                        }
                    });

                    smartrefresh_liebiao.setOnLoadmoreListener(new OnLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            page++;
                            Presenter presenter = new Presenter(SouSuoLieBiaoActivity.this);
                            presenter.getLieBiaoUrl(ApiUtil.sousuoliebiao, namesp,page);

                            recycler_view.setLayoutManager(new LinearLayoutManager(
                                    SouSuoLieBiaoActivity.this,LinearLayoutManager.VERTICAL,false));
                            MySouSuoLieBiaoAdapter adapter = new MySouSuoLieBiaoAdapter(SouSuoLieBiaoActivity.this, listad);
                            recycler_view.setAdapter(adapter);

                            smartrefresh_liebiao.finishLoadmore();
                        }
                    });

                    adapter.setItemListenner(new ItemListenner() {
                        @Override
                        public void OnItemClick(int position) {
                            Intent intent = new Intent(SouSuoLieBiaoActivity.this, JiaRuGouWuCheActivity.class);
                            intent.putExtra("pid",listad.get(position).getPid()+"");
                            startActivity(intent);
                        }

                        @Override
                        public void OnLongClick(int position) {
                            Toast.makeText(SouSuoLieBiaoActivity.this,"笨蛋该点击了!",Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    //listad.addAll(mySouSuoLieBiaoBean.getData());
                    //final List<MySouSuoLieBiaoBean.DataBean> list = mySouSuoLieBiaoBean.getData();
                    recycler_view.setLayoutManager(new GridLayoutManager(
                            SouSuoLieBiaoActivity.this,2,GridLayoutManager.VERTICAL,false));
                    MyGroupAdapter adapter = new MyGroupAdapter(SouSuoLieBiaoActivity.this, listad);
                    recycler_view.setAdapter(adapter);

                    smartrefresh_liebiao.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            smartrefresh_liebiao.finishRefresh(2000);
                        }
                    });

                    smartrefresh_liebiao.setOnLoadmoreListener(new OnLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            page++;
                            Presenter presenter = new Presenter(SouSuoLieBiaoActivity.this);
                            presenter.getLieBiaoUrl(ApiUtil.sousuoliebiao, namesp,page);

                            recycler_view.setLayoutManager(new LinearLayoutManager(
                                    SouSuoLieBiaoActivity.this,LinearLayoutManager.VERTICAL,false));
                            MySouSuoLieBiaoAdapter adapter = new MySouSuoLieBiaoAdapter(SouSuoLieBiaoActivity.this, listad);
                            recycler_view.setAdapter(adapter);

                            smartrefresh_liebiao.finishLoadmore();
                        }
                    });

                    adapter.setItemClicked(new ItemListenner() {
                        @Override
                        public void OnItemClick(int position) {
                            Intent intent = new Intent(SouSuoLieBiaoActivity.this, JiaRuGouWuCheActivity.class);
                            intent.putExtra("pid",listad.get(position).getPid()+"");
                            startActivity(intent);
                        }

                        @Override
                        public void OnLongClick(int position) {
                            Toast.makeText(SouSuoLieBiaoActivity.this
                                    ,"点击了!",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
