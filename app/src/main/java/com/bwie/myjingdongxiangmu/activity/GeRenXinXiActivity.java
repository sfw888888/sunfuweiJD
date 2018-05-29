package com.bwie.myjingdongxiangmu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.MainActivity;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.UserInfo;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GeRenXinXiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView yonghunicheng;
    private TextView text_yonghuname;
    private ImageView image_touxiang;

    private SharedPreferences sharedPreferences;
    private ImageView iv_img;
    private TextView bt_camera;
    private TextView bt_xiangce;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private Bitmap bitmap1;
    private LinearLayout line_chuan;
    private RelativeLayout reyong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_ge_ren_xin_xi);
        //加载控件
        initView();
        //image_touxiang = findViewById(R.id.image_touxiang);
        text_yonghuname = findViewById(R.id.text_yonghuname);
        yonghunicheng = findViewById(R.id.yonghunicheng);
        reyong = findViewById(R.id.reyong);
        line_chuan = findViewById(R.id.line_chuan);

        Intent inten = getIntent();
        String name = inten.getStringExtra("name");
        //String icon = inten.getStringExtra("icon");
        text_yonghuname.setText(name);
        //Glide.with(GeRenXinXiActivity.this).load(icon).into(image_touxiang);


        reyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_chuan.setVisibility(View.VISIBLE);
            }
        });


    }

    public void image_fanhuimy(View view) {
        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
        finish();
    }


    public void nicheng(View view) {
        String s = yonghunicheng.getText().toString();
        Intent intent = new Intent(GeRenXinXiActivity.this, NiChengActivity.class);
        intent.putExtra("s",s);
        startActivityForResult(intent,10);
        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10){
            String edit_nicheng = data.getStringExtra("edit_nicheng");
            yonghunicheng.setText(edit_nicheng);
        }
    }*/

    private void initView() {
        iv_img = (ImageView) findViewById(R.id.iv_img);
        bt_camera =findViewById(R.id.bt_camera);
        bt_xiangce = findViewById(R.id.bt_xiangce);
        //从SharedPreferences获取图片
        getBitmapFromSharedPreferences();
        //监听两个按钮，相册按钮和相机按钮
        bt_camera.setOnClickListener(this);
        bt_xiangce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_camera:
                // 激活相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

                line_chuan.setVisibility(View.GONE);
                break;
            case R.id.bt_xiangce:
                // 激活系统图库，选择一张图片
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);

                line_chuan.setVisibility(View.GONE);
                break;
            case R.id.quxiao:
                line_chuan.setVisibility(View.GONE);
                break;
        }
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
         * 剪切图片
         */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //防止低版本没有返回确认数据，导致奔溃
        if(data==null){
            return;
        }
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(GeRenXinXiActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                //防止低版本没有办法获取数据，导致奔溃
                if(bitmap ==null){
                    return;
                }
                /**
                 * 获得图片
                 */
                /*Intent qwt = new Intent();
                UserInfo info = new UserInfo(data.getParcelableExtra("data"));
                qwt.putExtra("bitmap1",info);
                setResult(60,qwt);*/
                bitmap1 = bitmap;
                iv_img.setImageBitmap(bitmap);
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);



                if (requestCode==10){
                    String edit_nicheng = data.getStringExtra("edit_nicheng");
                    yonghunicheng.setText(edit_nicheng);
                }
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();

        //上传头像
        setImgByStr(imageString,"");
    }


    /**
     * 上传头像
     * @param imgStr
     * @param imgName
     */
    public  void setImgByStr(String imgStr, String imgName) {
        sharedPreferences = getSharedPreferences("yonghuxinxi"
                , Activity.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "");
        //这里是头像接口，通过Post请求，拼接接口地址和ID，上传数据。
        String url = "https://www.zhaoapi.cn/file/upload";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);// 11111111
        params.put("file", imgStr);
        OkHttp3Util.doPost(url, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.i("上传失败", "失败" + request.toString() + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            try {
                                String json = response.body().string();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
            }
        });
    }

    //从SharedPreferences获取图片
    private void getBitmapFromSharedPreferences(){
        SharedPreferences sharedPreferences=getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString=sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
        if(byteArray.length==0){
            iv_img.setImageResource(R.mipmap.ic_launcher);
        }else{
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
            iv_img.setImageBitmap(bitmap);
        }

    }

}
