package com.bwie.myjingdongxiangmu.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.frag.Frag_FaXian;
import com.bwie.myjingdongxiangmu.frag.Frag_FenLei;
import com.bwie.myjingdongxiangmu.frag.Frag_GouWuChe;
import com.bwie.myjingdongxiangmu.frag.Frag_My;
import com.bwie.myjingdongxiangmu.frag.Frag_ShouYe;

public class Main2Activity extends AppCompatActivity {

    private FrameLayout frame_layout;
    private RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //SYSTEM_UI_FLAG_FULLSCREEN
        //SYSTEM_UI_FLAG_IMMERSIVE
        //SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN有标题栏不完美
        //SCREEN_STATE_OFF有标题栏完美
        //SCREEN_STATE_ON有标题栏完美
        //SCROLL_AXIS_HORIZONTAL有标题栏完美
        //SCROLL_AXIS_NONE有标题栏完美
        //SCROLL_AXIS_VERTICAL有标题栏完美
        //
        //
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        /*if (Build.VERSION.SDK_INT >= 21) {
    View decorView = getWindow().getDecorView();
    int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    decorView.setSystemUiVisibility(option);
    getWindow().setNavigationBarColor(Color.TRANSPARENT);
    getWindow().setStatusBarColor(Color.TRANSPARENT);
}
ActionBar actionBar = getSupportActionBar();
actionBar.hide();
        * */

        setContentView(R.layout.activity_main2);
        frame_layout = findViewById(R.id.frame_layout);
        radio_group = findViewById(R.id.radio_group);
        Frag_ShouYe shouYe = new Frag_ShouYe();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, shouYe).commit();
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_01:
                        Frag_ShouYe shouYe = new Frag_ShouYe();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, shouYe).commit();
                        break;
                    case R.id.rb_02:
                        Frag_FenLei fenLei = new Frag_FenLei();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, fenLei).commit();
                        break;
                    case R.id.rb_03:
                        Frag_FaXian faXian = new Frag_FaXian();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, faXian).commit();
                        break;
                    case R.id.rb_04:
                        Frag_GouWuChe gouWuChe = new Frag_GouWuChe();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, gouWuChe).commit();
                        break;
                    case R.id.rb_05:
                        Frag_My fragMy = new Frag_My();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, fragMy).commit();
                        break;
                }
            }
        });



    }
}
