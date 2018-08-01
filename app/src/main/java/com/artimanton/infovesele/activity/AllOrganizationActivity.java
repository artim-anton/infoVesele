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
import com.artimanton.infovesele.activity.all_organization.OrganizationActivity;
import com.artimanton.infovesele.activity.all_organization.ShopsActivity;
import com.artimanton.infovesele.activity.all_organization.Watercanal;
import com.artimanton.infovesele.activity.all_transport.FreightTaxiActivity;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class AllOrganizationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_organization);
        setupBottomNavigation(3, this);
        BackGroundActivity.setBackground(this, this);
    }


    public void btnOrganization(View view) {
        Intent intent = new Intent(AllOrganizationActivity.this, OrganizationActivity.class);
        startActivity(intent);
    }

    public void btnShops(View view) {
        Intent intent = new Intent(AllOrganizationActivity.this, ShopsActivity.class);
        startActivity(intent);
    }

    public void btnEducationalInstitution(View view) {
        Intent intent = new Intent(AllOrganizationActivity.this, AllEducationInstitutionActivity.class);
        startActivity(intent);

    }
}
