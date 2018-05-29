package com.bwie.myjingdongxiangmu.activity;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.frag.frag_dingdan.Frag_DaiZhi;
import com.bwie.myjingdongxiangmu.frag.frag_dingdan.Frag_QuanBu;
import com.bwie.myjingdongxiangmu.frag.frag_dingdan.Frag_YiQu;
import com.bwie.myjingdongxiangmu.frag.frag_dingdan.Frag_YiZhi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDingDanActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_btn)
    ImageView imageBtn;
    @BindView(R.id.text_quanbu)
    TextView textQuanbu;
    @BindView(R.id.text_daizhi)
    TextView textDaizhi;
    @BindView(R.id.text_yizhi)
    TextView textYizhi;
    @BindView(R.id.text_yiqu)
    TextView textYiqu;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private ImageView image_btn;
    private TextView text_daizhi;
    private TextView text_yizhi;
    private TextView text_yiqu;
    private FrameLayout frame_layout;
    private View item_popup;
    private View view;
    private PopupWindow popupWindow;
    private TextView btn_daizhi;
    private TextView btn_yizhi;
    private TextView btn_yiqu;
    private TextView btn_quanbu;
    private TextView text_quanbu;
    private String indexid="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_my_ding_dan);
        ButterKnife.bind(this);
        image_btn = findViewById(R.id.image_btn);
        text_quanbu = findViewById(R.id.text_quanbu);
        text_daizhi = findViewById(R.id.text_daizhi);
        text_yizhi = findViewById(R.id.text_yizhi);
        text_yiqu = findViewById(R.id.text_yiqu);
        frame_layout = findViewById(R.id.frame_layout);

        text_quanbu.setOnClickListener(this);
        text_daizhi.setOnClickListener(this);
        text_yizhi.setOnClickListener(this);
        text_yiqu.setOnClickListener(this);
        String myid = getIntent().getStringExtra("myid");
        if (myid!=null&&myid!=""){
            Log.d("+++++++++wer",myid);
            indexid=myid;
        }
        if (indexid=="0"){
            Frag_QuanBu frag_daizhi = new Frag_QuanBu();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, frag_daizhi).commit();
        }else if (indexid=="1"){
            Frag_DaiZhi frag_daizhi = new Frag_DaiZhi();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, frag_daizhi).commit();

        }else if (indexid=="2"){
            Frag_YiZhi frag_yiZhi = new Frag_YiZhi();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, frag_yiZhi).commit();

        }else if (indexid=="3"){
            Frag_YiQu frag_yiQu = new Frag_YiQu();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, frag_yiQu).commit();

        }

        //PopupWindow布局
        item_popup = View.inflate(this, R.layout.item_popup, null);
        //当前布局
        view = View.inflate(this, R.layout.activity_main, null);
        popupWindow = new PopupWindow(item_popup
                , ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);

        //PopupWindow布局控件
        btn_quanbu = item_popup.findViewById(R.id.btn_quanbu);
        btn_daizhi = item_popup.findViewById(R.id.btn_daizhi);
        btn_yizhi = item_popup.findViewById(R.id.btn_yizhi);
        btn_yiqu = item_popup.findViewById(R.id.btn_yiqu);
        btn_quanbu.setOnClickListener(this);
        btn_daizhi.setOnClickListener(this);
        btn_yizhi.setOnClickListener(this);
        btn_yiqu.setOnClickListener(this);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮PopupWindow布局显示的位置
                //popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
                popupWindow.showAsDropDown(image_btn);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_quanbu:
                Frag_QuanBu frag_quanBu = new Frag_QuanBu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_quanBu).commit();
                break;
            case R.id.text_daizhi:
                Frag_DaiZhi frag_daizhi = new Frag_DaiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_daizhi).commit();
                break;
            case R.id.text_yizhi:
                Frag_YiZhi frag_yiZhi = new Frag_YiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiZhi).commit();
                break;
            case R.id.text_yiqu:
                Frag_YiQu frag_yiQu = new Frag_YiQu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiQu).commit();
                break;


            case R.id.btn_quanbu:
                Frag_QuanBu frag_quanBu2 = new Frag_QuanBu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_quanBu2).commit();
                break;
            case R.id.btn_daizhi:
                Frag_DaiZhi frag_daizhi2 = new Frag_DaiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_daizhi2).commit();
                break;
            case R.id.btn_yizhi:
                Frag_YiZhi frag_yiZhi2 = new Frag_YiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiZhi2).commit();
                popupWindow.dismiss();
                break;
            case R.id.btn_yiqu:
                Frag_YiQu frag_yiQu2 = new Frag_YiQu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiQu2).commit();
                popupWindow.dismiss();
                break;
        }
    }

    /*@OnClick({R.id.image_btn, R.id.text_quanbu, R.id.text_daizhi, R.id.text_yizhi, R.id.text_yiqu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_btn:
                break;
            case R.id.text_quanbu:
                break;
            case R.id.text_daizhi:
                break;
            case R.id.text_yizhi:
                break;
            case R.id.text_yiqu:
                break;
        }
    }*/
}
