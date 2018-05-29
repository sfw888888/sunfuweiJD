package com.bwie.myjingdongxiangmu.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bwie.myjingdongxiangmu.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class JiaRuGouWuCheLunBoTuTiaoZhuanActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private Banner mybanner_dianjilunbotu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_jia_ru_gou_wu_che_lun_bo_tu_tiao_zhuan);

        mybanner_dianjilunbotu = findViewById(R.id.Mybanner_dianjilunbotu);
        String images = getIntent().getStringExtra("images");
        String[] split = images.split("\\|");

        for (int i=0;i<split.length;i++) {
            list.add(split[i]);
        }

        mybanner_dianjilunbotu.setBannerStyle(Banner.AUTOFILL_TYPE_LIST);//设置图片的样式
        mybanner_dianjilunbotu.setIndicatorGravity(Banner.CENTER); //设置指示器位置
        mybanner_dianjilunbotu.setDelayTime(2000);//间隔时间
        mybanner_dianjilunbotu.isAutoPlay(true);//设置自动轮播
        mybanner_dianjilunbotu.setImages(list);
    }
}
