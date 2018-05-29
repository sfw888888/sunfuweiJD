package com.bwie.myjingdongxiangmu.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.myjingdongxiangmu.R;

public class ShangPinActivity extends AppCompatActivity {

    private WebView web_shangpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SCREEN_STATE_OFF;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_shang_pin);
        web_shangpin = findViewById(R.id.web_shangpin);
        String detailUrl = getIntent().getStringExtra("detailUrl");
        web_shangpin.loadUrl(detailUrl);

        web_shangpin.setWebViewClient(new WebViewClient());

        WebSettings settings = web_shangpin.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

    }
}
