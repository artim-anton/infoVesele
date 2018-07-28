package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_organization.Watercanal;

public class AllOrganizationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_organization);
        setupBottomNavigation(3, this);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sprinkles); // берем картинку из ресурса
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT); // гшоворим обьекту как рисовать (у меня это повторяющийся фон)
        ScrollView layout = findViewById(R.id.myview);
        layout.setBackgroundDrawable(bitmapDrawable); // задаём фон нашему лэйауту
    }


    public void btnOrganization(View view) {
    }

    public void btnShops(View view) {
    }

}
