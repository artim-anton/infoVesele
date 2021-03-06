package com.artimanton.infovesele.activity.all_education_institution;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.permission.Internet;

public class LyceumActivity extends AppCompatActivity {
    WebView mWebView;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyceum);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        isOnline();
        LoadWeb();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });

        if ( !Internet.isOnline(this) ){
            Toast.makeText(this, "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }
    }

    public void LoadWeb(){
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
                mWebView.loadUrl("file:///android_asset/error.html");
            }

            public void onPageFinished(WebView view,String url){
                ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
                progressbar.setVisibility(View.GONE);
                swipe.setRefreshing(false);

            }
        });

        mWebView.loadUrl("https://vespal60.jimdofree.com");
    }

    public void isOnline(){
        if ( !Internet.isOnline(this) ){
            Toast.makeText(this, "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack() & !Internet.isOnline(this)) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
