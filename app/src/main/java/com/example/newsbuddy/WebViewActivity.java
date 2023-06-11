package com.example.newsbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.newsbuddy.R;

public class WebViewActivity extends AppCompatActivity {


    Toolbar toolbar;
    WebView webView;

    private String URL;

    ProgressBar pgBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        pgBar = findViewById(R.id.pgBar);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        URL = intent.getStringExtra("url");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pgBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pgBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(URL);

    }

    public void shareNews(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Hey friend, read this news : " + URL);
        Intent chooser = Intent.createChooser(intent,"Share this News using...");
        startActivity(intent);


    }



    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}