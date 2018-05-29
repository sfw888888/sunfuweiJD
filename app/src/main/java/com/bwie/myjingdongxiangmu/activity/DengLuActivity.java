package com.bwie.myjingdongxiangmu.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.MainActivity;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyDengLuBean;
import com.bwie.myjingdongxiangmu.bean.MyJiaRuGouWuCheBean;
import com.bwie.myjingdongxiangmu.bean.MyZhuCeBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterDengCe;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.view.DengZhuCe;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class DengLuActivity extends AppCompatActivity implements DengZhuCe{

    private int RESULT =1;
    private EditText edit_name;
    private EditText edit_pwd;
    private Button btn_denglu;
    private Button btn_zhuce;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        textView = findViewById(R.id.text);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edit_name = findViewById(R.id.edit_name);
        edit_pwd = findViewById(R.id.edit_pwd);
        btn_denglu = findViewById(R.id.btn_denglu);
        btn_zhuce = findViewById(R.id.btn_zhuce);


        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengLuActivity.this, ZhuCeActivity.class);
                startActivity(intent);
            }
        });

        btn_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dengluname = edit_name.getText().toString();
                String denglupwd = edit_pwd.getText().toString();
                //dengluname.equals("")&&denglupwd.equals("")
                if (!dengluname.equals("")&&!denglupwd.equals("")){
                    //presenter.getDengLuUrl(ApiUtil.denglu,dengluname,denglupwd);
                    PresenterDengCe presenterDengCe = new PresenterDengCe(DengLuActivity.this);
                    presenterDengCe.getDengLuUrl(ApiUtil.denglu,dengluname,denglupwd);
                }else {
                    Toast.makeText(DengLuActivity.this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public void getZhuCeBean(MyZhuCeBean myZhuCeBean) {

    }

    @Override
    public void getDengLuBean(final MyDengLuBean myDengLuBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDengLuBean!=null){
                    if (myDengLuBean.getCode().equals("0")){
                        //https://www.zhaoapi.cn/user/login?mo
                        //https://www.zhaoapi.cn/user/login?mobile=15510744235&password=155107
                        Intent intent = new Intent();
                        intent.putExtra("uid",myDengLuBean.getData().getUid()+"");
                        //Log.d("111+++++++---",myDengLuBean.getData().getUid()+"");
                        intent.putExtra("name",myDengLuBean.getData().getUsername());
                        setResult(RESULT,intent);
                        //result
                        //Toast.makeText(DengLuActivity.this,myDengLuBean.getMsg(),Toast.LENGTH_LONG).show();
                        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
                        finish();

                    }else {
                        Toast.makeText(DengLuActivity.this,myDengLuBean.getMsg(),Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    public void getJiaRuGouWuCheBean(MyJiaRuGouWuCheBean myJiaRuGouWuCheBean) {

    }

    public void fan_fragmy(View view) {
        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
        finish();
    }


    public void qqDengLu(View view) {
        UMShareAPI.get(this).getPlatformInfo(DengLuActivity
                        .this, SHARE_MEDIA.QQ
                , new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(DengLuActivity.this, "开始："
                                , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(DengLuActivity.this, "完成："
                                , Toast.LENGTH_LONG).show();
                        textView.setText("id:"+map.get("uid")+",昵称:"+map
                                .get("name")+",性别："+map.get("gender")
                                +",头像:"+map.get("iconurl"));
                        finish();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Toast.makeText(DengLuActivity.this, "失败："
                                , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Toast.makeText(DengLuActivity.this, "取消："
                                , Toast.LENGTH_LONG).show();
                    }
                });
    }
}
