package com.bwie.myjingdongxiangmu.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bwie.myjingdongxiangmu.R;

public class NiChengActivity extends AppCompatActivity {

    private EditText edit_nicheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_ni_cheng);

        edit_nicheng = findViewById(R.id.edit_nicheng);
        String s = getIntent().getStringExtra("s");
        edit_nicheng.setText(s);
    }

    public void image_fanhuimy(View view) {
        finish();
    }

    public void queding(View view) {
        String s = edit_nicheng.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("edit_nicheng",s);
        setResult(20,intent);
        finish();

    }
}
