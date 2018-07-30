package com.artimanton.infovesele.activity.all_organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.AllOrganizationActivity;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class OrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        BackGroundActivity.setBackground(this, this);
    }

    public void btnWatercanal(View view) {
        Intent intent = new Intent(OrganizationActivity.this, Watercanal.class);
        startActivity(intent);
    }

    public void btnRes(View view) {
        Intent intent = new Intent(OrganizationActivity.this, ResActivity.class);
        startActivity(intent);
    }


}
