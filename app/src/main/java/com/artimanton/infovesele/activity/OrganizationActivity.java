package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_organization.WatercanalActivity;
import com.artimanton.infovesele.activity.all_transport.FuelingActivity;

public class OrganizationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        setupBottomNavigation(3, this);
    }

    public void btnWatercanal(View view) {
        Intent intent = new Intent(OrganizationActivity.this, WatercanalActivity.class);
        startActivity(intent);
    }
}
