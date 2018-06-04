package com.artimanton.infovesele.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;

public class AdvertVeseleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_vesele);
        setupBottomNavigation(2,this);
    }

    public void btnAddAdvert(View view) {


    }
}
