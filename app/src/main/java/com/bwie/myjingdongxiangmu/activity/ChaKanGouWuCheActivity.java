package com.bwie.myjingdongxiangmu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.adapter.MyGouWuCheAdapter;
import com.bwie.myjingdongxiangmu.adapter.MyTuiJianAdapter;
import com.bwie.myjingdongxiangmu.bean.MyCreateBean;
import com.bwie.myjingdongxiangmu.bean.MyDingDanBean;
import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.bean.MyPriceHeNumBean;
import com.bwie.myjingdongxiangmu.bean.MySelectBean;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.presenter.PresenterDingDan;
import com.bwie.myjingdongxiangmu.presenter.PresenterSelect;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.CreateDingDan;
import com.bwie.myjingdongxiangmu.view.Main;
import com.bwie.myjingdongxiangmu.view.MainSelect;
import com.bwie.myjingdongxiangmu.zidingyi.MyExpandableListView;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

public class ChaKanGouWuCheActivity extends AppCompatActivity  implements MainSelect, View.OnClickListener,CreateDingDan,Main {
    private View view;
    private MyExpandableListView myexpandable_listview;
    private CheckBox check_all;
    private TextView text_pricesum;
    private TextView text_numsum;
    private PresenterSelect presenterSelect;
    private RelativeLayout relative_bar;
    private String sprice=0+"";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                myPriceHeNumBean = (MyPriceHeNumBean) msg.obj;
                text_pricesum.setText("总价:"+ myPriceHeNumBean.getPrice());
                //Log.d("+++++++++++++jiaqian",myPriceHeNumBean.getPrice());
                text_numsum.setText("去结算("+ myPriceHeNumBean.getNum()+")");
                sprice=myPriceHeNumBean.getPrice();
            }
        }
    };
    private MyGouWuCheAdapter adapter;
    private RecyclerView recycler_gouwuchetuijian;
    private MyPriceHeNumBean myPriceHeNumBean;
    private PresenterDingDan presenterDingDan;
    private String code;
    private String msg;
    private String price;
    private int num;
    private MySelectBean mySelectBean;
    private double price1;
    private int num1;
    private Presenter p;
    private SharedPreferences preferences;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_cha_kan_gou_wu_che);
        myexpandable_listview = findViewById(R.id.myexpandable_listview);
        check_all = findViewById(R.id.check_all);
        text_pricesum = findViewById(R.id.text_pricesum);
        text_numsum = findViewById(R.id.text_numsum);
        relative_bar = findViewById(R.id.relative_bar);
        recycler_gouwuchetuijian = findViewById(R.id.recycler_gouwuchetuijian);

        preferences = getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        uid = preferences.getString("uid", "");

        presenterSelect = new PresenterSelect(this);
        presenterDingDan = new PresenterDingDan(this);
        p = new Presenter(this);

        check_all.setOnClickListener(this);
        text_numsum.setOnClickListener(this);


    }
    @Override
    public void onResume() {
        super.onResume();
        relative_bar.setVisibility(View.VISIBLE);
        if (uid!=null&&uid!=""){
            presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
        }
        //presenterSelect.getTuiJianUrl(ApiUtil.);
        p.getList(ApiUtil.urllist);
    }

    @Override
    public void getSelectBean(final MySelectBean mySelectBean) {
        this.mySelectBean = mySelectBean;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mySelectBean!=null){
                    relative_bar.setVisibility(View.GONE);

                    for (int i=0;i<mySelectBean.getData().size();i++){
                        if (isChildinGroupChecked(i,mySelectBean.getData())){
                            mySelectBean.getData().get(i).setGroup_checked(true);
                        }
                    }

                    check_all.setChecked(isGroupChecked(mySelectBean.getData()));

                    adapter = new MyGouWuCheAdapter(ChaKanGouWuCheActivity.this,mySelectBean,presenterSelect,relative_bar,handler);
                    myexpandable_listview.setAdapter(adapter);

                    for (int i=0;i<mySelectBean.getData().size();i++){
                        myexpandable_listview.expandGroup(i);
                    }

                    //计算价格
                    adapter.sendAllPrice();



                }
            }
        });
    }

    @Override
    public void getTuiJianBean(MyLunBoTuTuiJianMiao myLunBoTuTuiJianMiao) {

    }

    private boolean isGroupChecked(List<MySelectBean.DataBean> list) {
        for (int i=0;i<list.size();i++){
            if (!list.get(i).isGroup_checked()){
                return false;
            }
        }

        return true;
    }

    private boolean isChildinGroupChecked(int i, List<MySelectBean.DataBean> list) {
        for (int j=0;j<list.get(i).getList().size();j++){
            List<MySelectBean.DataBean.ListBean> listBeans = list.get(i).getList();
            if (listBeans.get(j).getSelected()==0){
                return false;
            }
        }

        return true;
    }

    @Override
    public void getCreateBean(final MyCreateBean myCreateBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                code = myCreateBean.getCode();
                msg = myCreateBean.getMsg();
                if (code.equals("0")){
                    Toast.makeText(ChaKanGouWuCheActivity.this,msg,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ChaKanGouWuCheActivity.this, MyDingDanActivity.class);
                    startActivity(intent);
                }else if (code.equals("1")){
                    Toast.makeText(ChaKanGouWuCheActivity.this,msg,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void getDingDanBean(MyDingDanBean myDingDanBean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check_all:
                if (adapter!=null){
                    adapter.setAllChecked(check_all.isChecked());
                }
                break;
            case R.id.text_numsum:
                price1 = 0;
                num1 = 0;
                List<MySelectBean.DataBean> data = mySelectBean.getData();
                for (int i=0;i<data.size();i++){
                    List<MySelectBean.DataBean.ListBean> listBeans = data.get(i).getList();
                    for (int j=0;j<listBeans.size();j++){
                        if (listBeans.get(j).getSelected()==1){
                            price1 +=listBeans.get(j).getBargainPrice()*listBeans.get(j).getNum();
                            num1 +=listBeans.get(j).getNum();
                        }
                    }
                }
                String format = new DecimalFormat("0.00").format(price1);
                if(num1==0){
                    Toast.makeText(ChaKanGouWuCheActivity.this,"请选择商品",Toast.LENGTH_LONG).show();
                }else {
                    //Log.d("+++++++++++++jiaqian",sprice);
                    presenterDingDan.getCreateUrl(ApiUtil.create,format,uid);
                }
                break;
        }
    }


    @Override
    public void getStringJson(String json) {

    }

    @Override
    public void getStringList(final String json) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyLunBoTuTuiJianMiao myLunBoTuTuiJianMiao = new Gson().fromJson(json, MyLunBoTuTuiJianMiao.class);
                final List<MyLunBoTuTuiJianMiao.TuijianEntity.ListEntity> list1 = myLunBoTuTuiJianMiao.getTuijian().getList();
                recycler_gouwuchetuijian.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                MyTuiJianAdapter adapter1 = new MyTuiJianAdapter(ChaKanGouWuCheActivity.this, list1);
                recycler_gouwuchetuijian.setAdapter(adapter1);

                adapter1.setTuiJianItemClick(new ItemListenner() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(ChaKanGouWuCheActivity.this, JiaRuGouWuCheActivity.class);
                        intent.putExtra("pid",list1.get(position).getPid()+"");
                        startActivity(intent);
                    }

                    @Override
                    public void OnLongClick(int position) {
                        //Toast.makeText(ChaKanGouWuCheActivity.this,"笨蛋该点击了!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void getLieBiaoBean(MySouSuoLieBiaoBean mySouSuoLieBiaoBean) {

    }


    public void image_fan(View view) {
        finish();
    }
}
