package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_services.RepairsActivity;
import com.artimanton.infovesele.activity.all_transport.DeliveryService;

public class ServicesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        setupBottomNavigation(4,this);
    }

    public void btnRepairs(View view) {
        Intent intent = new Intent(ServicesActivity.this, RepairsActivity.class);
        startActivity(intent);
    }

    public void btnHomeMaster(View view) {
    }

    public void btnBuilder(View view) {
    }

    public void btnTutors(View view) {
    }

    public void btnBusiness(View view) {
    }

    public void btnDomestic(View view) {
    }

    public void btnCar(View view) {
    }
}
