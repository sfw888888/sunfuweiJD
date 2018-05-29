package com.bwie.myjingdongxiangmu.frag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.activity.DengLuActivity;
import com.bwie.myjingdongxiangmu.activity.JiaRuGouWuCheActivity;
import com.bwie.myjingdongxiangmu.activity.MyDingDanActivity;
import com.bwie.myjingdongxiangmu.activity.YongHuXinXiActivity;
import com.bwie.myjingdongxiangmu.adapter.MyTuiJianAdapter;
import com.bwie.myjingdongxiangmu.bean.MyLunBoTuTuiJianMiao;
import com.bwie.myjingdongxiangmu.bean.MySouSuoLieBiaoBean;
import com.bwie.myjingdongxiangmu.bean.MyYongHuXXingXiBean;
import com.bwie.myjingdongxiangmu.bean.YongHuXinXiBean;
import com.bwie.myjingdongxiangmu.itemshijian.ItemListenner;
import com.bwie.myjingdongxiangmu.presenter.PreSenterTouXiang;
import com.bwie.myjingdongxiangmu.presenter.Presenter;
import com.bwie.myjingdongxiangmu.presenter.PresenterTou;
import com.bwie.myjingdongxiangmu.presenter.RetrofitPresenter;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.util.RetrofitUtil;
import com.bwie.myjingdongxiangmu.view.Main;
import com.bwie.myjingdongxiangmu.view.RetrofitView;
import com.bwie.myjingdongxiangmu.view.TouXiang;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/1/2.
 * flyme
 */

public class Frag_My extends Fragment implements TouXiang,Main,RetrofitView, View.OnClickListener {

    private int REQUEST = 0;
    private View view;
    private RelativeLayout image_denglu;
    private ImageView image_shezhi;
    private TextView text_yonghuming;
    private SharedPreferences sharedPreferences;
    private String name;
    private String uid;
    private int REQUESTCode=100;
    private ImageView image_touxiang;
    private MyYongHuXXingXiBean.DataBean data;
    private RecyclerView recycler_myTuiJian;
    private Presenter p;
    private RetrofitPresenter retrofitPresenter;
    private LinearLayout linear_daizhi;
    private LinearLayout linear_daishou;
    private LinearLayout linear_daipin;
    private LinearLayout linear_tuishou;
    private LinearLayout linear_dingdan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_my, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        image_denglu = view.findViewById(R.id.image_denglu);
        image_shezhi = view.findViewById(R.id.image_shezhi);
        image_touxiang = view.findViewById(R.id.image_touxiang);
        text_yonghuming = view.findViewById(R.id.text_yonghuming);
        recycler_myTuiJian = view.findViewById(R.id.recycler_myTuiJian);

        linear_daizhi = view.findViewById(R.id.linear_daizhi);
        linear_daishou = view.findViewById(R.id.linear_daishou);
        linear_daipin = view.findViewById(R.id.linear_daipin);
        linear_tuishou = view.findViewById(R.id.linear_tuishou);
        linear_dingdan = view.findViewById(R.id.linear_dingdan);
        linear_daizhi.setOnClickListener(this);
        linear_daishou.setOnClickListener(this);
        linear_daipin.setOnClickListener(this);
        linear_tuishou.setOnClickListener(this);
        linear_dingdan.setOnClickListener(this);

        p = new Presenter(this);
        p.getList(ApiUtil.urllist);

        retrofitPresenter = new RetrofitPresenter(this);
        retrofitPresenter.attachView(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        retrofitPresenter.getUrl(RetrofitUtil.getService()
                .doGet(ApiUtil.yonghuxinxi,map));

        /*SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString=sharedPreferences1.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        if (imageString==""||imageString==null){
            image_touxiang.setImageResource(R.drawable.user);
        }else{
            byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
            if(byteArray.length==0){
                image_touxiang.setImageResource(R.mipmap.ic_launcher);
            }else {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

                //第三步:利用ByteArrayInputStream生成Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                image_touxiang.setImageBitmap(bitmap);
            }
            //Glide.with(getActivity()).load(imageString).into(image_touxiang);
        }
*/


        //返回上一个页面获取图片方法sharedPreferences
        SharedPreferences sharedPreference=getActivity().getSharedPreferences("testSP"
                , Context.MODE_PRIVATE);

        if (sharedPreference!=null){
            //第一步:取出字符串形式的Bitmap
            String image = sharedPreference.getString("image", "");
            //第二步:利用Base64将字符串转换为ByteArrayInputStream
            byte[] byteArray= Base64.decode(image, Base64.DEFAULT);
            if(byteArray.length==0){

            }else{
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
                //第三步:利用ByteArrayInputStream生成Bitmap
                Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
                image_touxiang.setImageBitmap(bitmap);
            }
        }



        sharedPreferences = getActivity()
                .getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);

        name = sharedPreferences.getString("name", "");
        if (name.equals("")){
            text_yonghuming.setText("登录/注册 ＞");
        }else {
            text_yonghuming.setText(name);
        }
        uid = sharedPreferences.getString("uid", "");
        if (!uid.equals("")){
            PresenterTou presenterTou = new PresenterTou(this);
            presenterTou.getYongHuXingXiUrl(ApiUtil.yonghu,uid);
        }


        image_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("")&&uid.equals("")){
                    //https://www.zhaoapi.cn/user/getUserInfo?uid=71
                    Intent intent = new Intent(getActivity(), DengLuActivity.class);
                    startActivityForResult(intent,REQUEST);
                    getActivity().overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
                    //requestcode
                }else {
                    Intent intent = new Intent(getActivity(), YongHuXinXiActivity.class);
                    intent.putExtra("name",name);
                    //intent.putExtra("icon",data.getIcon()+"");
                    startActivityForResult(intent,REQUESTCode);
                    getActivity().overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
                }

            }
        });
        image_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YongHuXinXiActivity.class);
                intent.putExtra("name",name);
                startActivityForResult(intent,REQUESTCode);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (p!=null){
            p.getMyDest();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST){
            String name1 = data.getStringExtra("name");
            String uid = data.getStringExtra("uid");
            //Log.d("++++++",name1);
            //Log.d("++++++",uid);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",name1);
            editor.putString("uid",uid);

            editor.commit();
            //Toast.makeText(getActivity(),"数据成功写入",Toast.LENGTH_LONG).show();
            text_yonghuming.setText(name1);
        }else if (REQUESTCode==100){

            //image_touxiang.setImageResource();
        }

    }

    @Override
    public void getYongHuXingXi(final MyYongHuXXingXiBean myYongHuXXingXiBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                data = myYongHuXXingXiBean.getData();
                Glide.with(getActivity()).load(data.getIcon()).into(image_touxiang);

            }
        });

    }

    @Override
    public void getStringJson(String json) {

    }

    @Override
    public void getStringList(final String json) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyLunBoTuTuiJianMiao myLunBoTuTuiJianMiao = new Gson().fromJson(json, MyLunBoTuTuiJianMiao.class);
                final List<MyLunBoTuTuiJianMiao.TuijianEntity.ListEntity> list1 = myLunBoTuTuiJianMiao.getTuijian().getList();
                recycler_myTuiJian.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                MyTuiJianAdapter adapter1 = new MyTuiJianAdapter(getActivity(), list1);
                recycler_myTuiJian.setAdapter(adapter1);

                adapter1.setTuiJianItemClick(new ItemListenner() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(getActivity(), JiaRuGouWuCheActivity.class);
                        intent.putExtra("pid",list1.get(position).getPid()+"");
                        startActivity(intent);
                    }

                    @Override
                    public void OnLongClick(int position) {
                        Toast.makeText(getActivity(),"笨蛋该点击了!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void getLieBiaoBean(MySouSuoLieBiaoBean mySouSuoLieBiaoBean) {

    }



    @Override
    public void getResponseBean(ResponseBody responseBody) {
        try {
            //Log.d("qwq++++",responseBody.string());
            YongHuXinXiBean yongHuXinXiBean = new Gson()
                    .fromJson(responseBody.string(), YongHuXinXiBean.class);
            Glide.with(getActivity()).load(yongHuXinXiBean.getData()
                    .getIcon()).into(image_touxiang);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        retrofitPresenter.setJieYue();
        if (retrofitPresenter!=null){
            retrofitPresenter.detachView();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_daizhi:
                Intent intent = new Intent(getActivity()
                        , MyDingDanActivity.class);
                intent.putExtra("myid","1");
                startActivity(intent);
                break;
            case R.id.linear_daishou:
                Intent intent2 = new Intent(getActivity()
                        , MyDingDanActivity.class);
                intent2.putExtra("myid","2");
                startActivity(intent2);
                break;
            case R.id.linear_daipin:

                break;
            case R.id.linear_tuishou:
                Intent intent3 = new Intent(getActivity()
                        , MyDingDanActivity.class);
                intent3.putExtra("myid","3");
                startActivity(intent3);
                break;
            case R.id.linear_dingdan:
                Intent intent0 = new Intent(getActivity()
                        , MyDingDanActivity.class);
                intent0.putExtra("myid","0");
                startActivity(intent0);
                break;
        }
    }
}
