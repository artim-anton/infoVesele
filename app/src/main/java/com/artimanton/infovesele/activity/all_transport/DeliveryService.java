package com.artimanton.infovesele.activity.all_transport;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.artimanton.infovesele.R;
import com.squareup.picasso.Picasso;

public class DeliveryService extends AppCompatActivity {

    private Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);
    }

    public void btnSiteNP(View view) {
        String url = "https://novaposhta.ua";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void btnSiteInTime(View view) {
        String url = "https://intime.ua";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
