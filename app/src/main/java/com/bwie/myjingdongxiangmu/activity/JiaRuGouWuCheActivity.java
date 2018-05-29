package com.bwie.myjingdongxiangmu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.MainActivity;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyTianJiaGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;
import com.bwie.myjingdongxiangmu.frag.Frag_GouWuChe;
import com.bwie.myjingdongxiangmu.presenter.PresenterDengCe;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;
import com.bwie.myjingdongxiangmu.util.ShareUtil;
import com.bwie.myjingdongxiangmu.view.DengZhuCe;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JiaRuGouWuCheActivity extends AppCompatActivity implements DengZhuCe{

    private Banner mybanner_jiarugouwuche;
    private Banner banner;
    private List<String> list = new ArrayList<>();
    private TextView jiaru_title;
    private TextView jiaru_price;
    private Button btn_chakan;
    private Button btn_jiaru;
    private TextView jiaru_subhead;
    private TextView jiaru_createtime;
    private MyJiaRuGouWuCheBean.DataBean data;
    private SharedPreferences yonghuxinxi;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.activity_jia_ru_gou_wu_che);
        //https://www.zhaoapi.cn/product/getProductDetail?pid=71&source=android
        mybanner_jiarugouwuche = findViewById(R.id.Mybanner_jiarugouwuche);
        jiaru_title = findViewById(R.id.jiaru_title);
        jiaru_price = findViewById(R.id.jiaru_price);
        btn_chakan = findViewById(R.id.btn_chakan);
        btn_jiaru = findViewById(R.id.btn_jiaru);
        jiaru_subhead = findViewById(R.id.jiaru_subhead);
        jiaru_createtime = findViewById(R.id.jiaru_createtime);

        yonghuxinxi = this.getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        if (yonghuxinxi!=null){
            uid = yonghuxinxi.getString("uid", "");
        }else {
            Intent intent = new Intent(JiaRuGouWuCheActivity.this
                    , DengLuActivity.class);
            startActivity(intent);
        }

        String pid = getIntent().getStringExtra("pid");
        PresenterDengCe presenterDengCe = new
                PresenterDengCe(JiaRuGouWuCheActivity.this);
        presenterDengCe.getJiaRuGouWuCheUrl(ApiUtil.jiarugouwuche,pid);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void getZhuCeBean(MyZhuCeBean myZhuCeBean) {

    }

    @Override
    public void getDengLuBean(MyDengLuBean myDengLuBean) {

    }

    @Override
    public void getJiaRuGouWuCheBean(final MyJiaRuGouWuCheBean myJiaRuGouWuCheBean) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //明天接着做
                data = myJiaRuGouWuCheBean.getData();
                String[] split = data.getImages().split("\\|");
                final String images = data.getImages();

                for (int i=0;i<split.length;i++) {
                    list.add(split[i]);
                }

                mybanner_jiarugouwuche.setBannerStyle(Banner.AUTOFILL_TYPE_LIST);//设置图片的样式
                mybanner_jiarugouwuche.setIndicatorGravity(Banner.CENTER); //设置指示器位置
                mybanner_jiarugouwuche.setDelayTime(2000);//间隔时间
                mybanner_jiarugouwuche.isAutoPlay(true);//设置自动轮播
                mybanner_jiarugouwuche.setImages(list);
                mybanner_jiarugouwuche.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, int position) {
                        Intent intent = new Intent(JiaRuGouWuCheActivity.this, JiaRuGouWuCheLunBoTuTiaoZhuanActivity.class);
                        intent.putExtra("images",images);
                        startActivity(intent);

                    }
                });

                jiaru_title.setText(data.getTitle());
                jiaru_subhead.setText(data.getSubhead());
                jiaru_createtime.setText(data.getCreatetime());
                jiaru_price.setText("¥ "+ data.getPrice());

                btn_jiaru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("+++++++++", String.valueOf(data.getPid()));
                        Map<String, String> params=new HashMap<>();
                        params.put("uid",uid);
                        params.put("pid", String.valueOf(data.getPid()));
                        params.put("source","android");
                        OkHttp3Util.doPost(ApiUtil.tianjia, params, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()){
                                    String json = response.body().string();
                                    final MyTianJiaGouWuCheBean myTianJiaGouWuCheBean = new Gson()
                                            .fromJson(json, MyTianJiaGouWuCheBean.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(JiaRuGouWuCheActivity
                                                    .this,myTianJiaGouWuCheBean.getMsg(),Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                            }
                        });
                    }
                });

                btn_chakan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(JiaRuGouWuCheActivity.this, ChaKanGouWuCheActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }

    public void fanhui(View view) {
        finish();
    }

    public void shangpin(View view) {
        Intent intent = new Intent(JiaRuGouWuCheActivity.this, ShangPinActivity.class);
        intent.putExtra("detailUrl",data.getDetailUrl());
        startActivity(intent);
    }

    public void xiangqing(View view) {
        Toast.makeText(JiaRuGouWuCheActivity.this,"正在开发中...",Toast.LENGTH_LONG).show();
    }

    public void pingjia(View view) {
        Toast.makeText(JiaRuGouWuCheActivity.this,"正在开发中...",Toast.LENGTH_LONG).show();
    }

    public void fenXiang(View view) {

        /*ShareUtil.shareWeb(JiaRuGouWuCheActivity.this,
                data.getDetailUrl()
                ,"一加手机"
                ,"我在京东上次发..."
                ,"http://img.jsqq.net/uploads/allimg/141015/1_141015171421_3.jpg"
                ,R.mipmap.ic_launcher_round, SHARE_MEDIA.QQ);*/
        UMWeb web = new UMWeb(data.getDetailUrl());
        web.setTitle(data.getTitle());
        web.setDescription(data.getCreatetime());
        String[] split = data.getImages().split("\\|");
        if (!split[0].equals("")){
            web.setThumb(new UMImage(this,split[0]));
        }else {
            web.setThumb(new UMImage(this,R.drawable.ic_launcher));
        }

        new ShareAction(JiaRuGouWuCheActivity.this)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(shareListener)//回调监听器
                .withMedia(web)
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(JiaRuGouWuCheActivity.this
                    ,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(JiaRuGouWuCheActivity.this
                    ,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(JiaRuGouWuCheActivity.this
                    ,"取消了",Toast.LENGTH_LONG).show();

        }
    };

}
