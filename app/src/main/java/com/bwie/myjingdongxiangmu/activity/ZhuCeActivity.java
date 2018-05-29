package com.bwie.myjingdongxiangmu.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterDengCe;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.DengZhuCe;

public class ZhuCeActivity extends AppCompatActivity implements DengZhuCe{

    private ImageView btn_image;
    private EditText zhuce_name;
    private EditText zhuce_pwd;
    private Button zhuce_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_zhu_ce);
        btn_image = findViewById(R.id.btn_image);
        zhuce_name = findViewById(R.id.zhuce_name);
        zhuce_pwd = findViewById(R.id.zhuce_pwd);
        zhuce_btn = findViewById(R.id.zhuce_btn);
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhuce_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhucename = zhuce_name.getText().toString();
                String zhucepwd = zhuce_pwd.getText().toString();
                if (zhucename!=""&&zhucename!=null&&zhucepwd!=""&&zhucepwd!=null){
                    //https://www.zhaoapi.cn/user/reg?mobile=15510744235&password=155107
                    //presenter.getZhuCeUrl(ApiUtil.zhuce,zhucename,zhucepwd);
                    PresenterDengCe presenterDengCe = new PresenterDengCe(ZhuCeActivity.this);
                    presenterDengCe.getZhuCeUrl(ApiUtil.zhuce,zhucename,zhucepwd);

                }
            }
        });
    }

    @Override
    public void getZhuCeBean(final MyZhuCeBean myZhuCeBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myZhuCeBean.getCode().equals("0")){
                    finish();
                }else {
                    Toast.makeText(ZhuCeActivity.this,myZhuCeBean.getMsg(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void getDengLuBean(MyDengLuBean myDengLuBean) {

    }

    @Override
    public void getJiaRuGouWuCheBean(MyJiaRuGouWuCheBean myJiaRuGouWuCheBean) {

    }
}
