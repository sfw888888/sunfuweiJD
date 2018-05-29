package com.bwie.myjingdongxiangmu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.shujuku.MyDao;
import com.bwie.myjingdongxiangmu.zidingyi.ZFlowLayout;

import java.util.List;

public class SouSuoActivity extends AppCompatActivity {
    private ZFlowLayout mFlowLayout;
    private String[] mLabels = {"硬盘螺丝","老人机","游玩","北京","CSDN"};
    private EditText edit_sousuo;
    private ListView list_lishi;
    private Button qingkong;
    private MyDao dao;
    private TextView btn_sousuo;
    private LinearLayout linear_lishi;
    private ImageView btn_image;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        mFlowLayout = (ZFlowLayout) findViewById(R.id.flowLayout);
        btn_image = findViewById(R.id.btn_image);
        edit_sousuo = findViewById(R.id.edit_sousuo);
        btn_sousuo = findViewById(R.id.btn_sousuo);
        list_lishi = findViewById(R.id.list_lishi);
        linear_lishi = findViewById(R.id.linear_lishi);
        qingkong = findViewById(R.id.qingkong);

        initLabel();
        dao = new MyDao(SouSuoActivity.this);
        adapter();
        list_lishi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = list.get(position);
                if (!s.equals("")) {
                    Intent intent = new Intent(SouSuoActivity.this, SouSuoLieBiaoActivity.class);
                    intent.putExtra("namesp", s);
                    startActivity(intent);
                    finish();
                }
            }
        });
        if (list.size()>0){
            linear_lishi.setVisibility(View.VISIBLE);
        }else {
            linear_lishi.setVisibility(View.GONE);
        }
        btn_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = edit_sousuo.getText().toString();
                if (!s.equals("")){
                    int i = dao.addtian(s);
                    if (i==1){
                        linear_lishi.setVisibility(View.VISIBLE);
                        adapter();
                        Intent intent = new Intent(SouSuoActivity.this, SouSuoLieBiaoActivity.class);
                        intent.putExtra("namesp",s);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(SouSuoActivity.this,"不为空",Toast.LENGTH_LONG).show();
                }
            }
        });

        list_lishi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SouSuoActivity.this);
                        builder.setTitle("是否删除？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int delet = dao.delet(list.get(position));
                                if (delet==1){
                                    int size = list.size();
                                    size--;
                                    if (size>0){
                                        linear_lishi.setVisibility(View.VISIBLE);
                                    }else {
                                        linear_lishi.setVisibility(View.GONE);
                                    }
                                    adapter();
                                }


                            }
                        });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });

        qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete();
                linear_lishi.setVisibility(View.GONE);
            }
        });
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // 初始化标签
    private void initLabel() {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);// 设置边距
        for (int i = 0; i < mLabels.length; i++) {
            final TextView textView = new TextView(SouSuoActivity.this);
            textView.setTag(i);
            textView.setTextSize(15);
            textView.setText(mLabels[i]);
            textView.setPadding(24, 11, 24, 11);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.lable_item_bg_normal);
            mFlowLayout.addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mLabels[(int) textView.getTag()], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void adapter(){
        list = dao.select();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SouSuoActivity.this, android.R.layout.simple_expandable_list_item_1, list);
        list_lishi.setAdapter(adapter);
    }


}
