package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_services.AddServicesActivity;
import com.artimanton.infovesele.activity.all_services.BeautyActivity;
import com.artimanton.infovesele.activity.all_services.BuilderActivity;
import com.artimanton.infovesele.activity.all_services.BusinessActivity;
import com.artimanton.infovesele.activity.all_services.CarActivity;
import com.artimanton.infovesele.activity.all_services.DevelopmentActivity;
import com.artimanton.infovesele.activity.all_services.DomesticActivity;
import com.artimanton.infovesele.activity.all_services.HolidaysActivity;
import com.artimanton.infovesele.activity.all_services.HomeMasterActivity;
import com.artimanton.infovesele.activity.all_services.RepairsActivity;
import com.artimanton.infovesele.activity.all_services.TutorsActivity;
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
        Intent intent = new Intent(ServicesActivity.this, HomeMasterActivity.class);
        startActivity(intent);
    }

    public void btnBuilder(View view) {
        Intent intent = new Intent(ServicesActivity.this, BuilderActivity.class);
        startActivity(intent);
    }

    public void btnTutors(View view) {
        Intent intent = new Intent(ServicesActivity.this, TutorsActivity.class);
        startActivity(intent);
    }

    public void btnBusiness(View view) {
        Intent intent = new Intent(ServicesActivity.this, BusinessActivity.class);
        startActivity(intent);
    }

    public void btnDomestic(View view) {
        Intent intent = new Intent(ServicesActivity.this, DomesticActivity.class);
        startActivity(intent);
    }

    public void btnCar(View view) {
        Intent intent = new Intent(ServicesActivity.this, CarActivity.class);
        startActivity(intent);
    }

    public void btnBeauty(View view) {
        Intent intent = new Intent(ServicesActivity.this, BeautyActivity.class);
        startActivity(intent);
    }

    public void btnAddServices(View view) {
        Intent intent = new Intent(ServicesActivity.this, AddServicesActivity.class);
        startActivity(intent);
    }

    public void btnHolidays(View view) {
        Intent intent = new Intent(ServicesActivity.this, HolidaysActivity.class);
        startActivity(intent);
    }

    public void btnDevelopment(View view) {
        Intent intent = new Intent(ServicesActivity.this, DevelopmentActivity.class);
        startActivity(intent);
    }
}
