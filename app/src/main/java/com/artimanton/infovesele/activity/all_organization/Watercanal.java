package com.artimanton.infovesele.activity.all_organization;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class Watercanal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watercanal);
        BackGroundActivity.setBackground(this, this);
    }

    public void btnTestify(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfK3M4llCkwh5ild7vZqSSvW4lMiGkbv14LUCOAxQkxeMYWow/viewform"));
        startActivity(intent);
    }

    public void btnSite(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://vodokanalvesele.at.ua"));
        startActivity(intent);
    }
}
