package com.bwie.myjingdongxiangmu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public class YongHuXinXiActivity extends AppCompatActivity {

    private int REQUEST = 0;
    private String name;
    private ImageView image_touxiang;
    private TextView text_yonghuming;
    private int REQUESTCode=300;
    private SharedPreferences yonghuxinxi;
    private SharedPreferences testSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_yong_hu_xin_xi);

        image_touxiang = findViewById(R.id.image_touxiang);
        text_yonghuming = findViewById(R.id.text_yonghuming);
        yonghuxinxi = getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        name = getIntent().getStringExtra("name");

        text_yonghuming.setText(name);



        //返回上一个页面获取图片方法
        SharedPreferences sharedPreferences=getSharedPreferences("testSP"
                , Context.MODE_PRIVATE);

        if (sharedPreferences!=null){
            //第一步:取出字符串形式的Bitmap
            String imageString= sharedPreferences.getString("image", "");
            //第二步:利用Base64将字符串转换为ByteArrayInputStream
            byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
            if(byteArray.length==0){

            }else{
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
                //第三步:利用ByteArrayInputStream生成Bitmap
                Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
                image_touxiang.setImageBitmap(bitmap);
            }
        }


    }

    public void image_fanhuimy(View view) {
        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
        finish();
    }

    public void gerenxinxi(View view) {
        if (name.equals("")){
            Intent intent = new Intent(YongHuXinXiActivity.this, DengLuActivity.class);
            startActivityForResult(intent,REQUEST);
        }else {
            Intent intent = new Intent(YongHuXinXiActivity.this, GeRenXinXiActivity.class);
            intent.putExtra("name",name);

            startActivityForResult(intent,REQUESTCode);
        }
    }

    public void julebu(View view) {
        Toast.makeText(YongHuXinXiActivity.this,"开发中...",Toast.LENGTH_LONG).show();
    }

    public void shezhi(View view) {

    }

    public void tuichudenglu(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否退出登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences.Editor edit = yonghuxinxi.edit();
                edit.clear().commit();
                SharedPreferences.Editor edit1 = testSP.edit();
                edit1.clear().commit();
                overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

        /*String uid = yonghuxinxi.getString("uid", "");
        Toast.makeText(YongHuXinXiActivity.this,uid,Toast.LENGTH_LONG).show();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST){

            String name1 = data.getStringExtra("name");
            String uid = data.getStringExtra("uid");
            //Toast.makeText(YongHuXinXiActivity.this,"数据成功写入",Toast.LENGTH_LONG).show();
            text_yonghuming.setText(name1);
        }else if (requestCode==REQUESTCode){
            //UserInfo info= (UserInfo) data.getSerializableExtra("bitmap1");
            //bitmap1.getClass().
           // image_touxiang.setImageResource(info);
        }

    }
}
