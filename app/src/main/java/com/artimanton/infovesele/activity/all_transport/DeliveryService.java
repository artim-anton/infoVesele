package com.artimanton.infovesele.activity.all_transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.artimanton.infovesele.R;

public class DeliveryService extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);

        mWebView = (WebView) findViewById(R.id.webView);
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        mWebView.loadUrl("https://www.google.com.ua/search?biw=1880&bih=970&ei=EsESW_esO4HGsgGAj7OQBg&q=службы%20доставки%20Веселого&oq=службы+доставки+Веселого&gs_l=psy-ab.3...0.0.0.2387.0.0.0.0.0.0.0.0..0.0....0...1c..64.psy-ab..0.0.0....0.WoUZ2hw8_GM&npsic=0&rflfq=1&rlha=0&rllag=47016365,34920895,1140&tbm=lcl&rldimm=14574523347272079619&ved=0ahUKEwiytMS9t7XbAhXLCSwKHWzkDpQQvS4ISjAB&rldoc=1&tbs=lrf:!2m4!1e17!4m2!17m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:3#rlfi=hd:;si:;mv:!1m3!1d5553.774156973023!2d34.920895800000004!3d47.01636515!2m3!1f0!2f0!3f0!3m2!1i682!2i392!4f13.1");
    }
}
