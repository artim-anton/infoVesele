package com.artimanton.infovesele.activity.all_organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.TransportActivity;
import com.artimanton.infovesele.activity.all_transport.Train;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class ShopsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        BackGroundActivity.setBackground(this, this);
    }

    public void btnSale(View view) {
        Intent intent = new Intent(ShopsActivity.this, SaleActivity.class);
        startActivity(intent);
    }


    public void btnCheaper(View view) {
        Intent intent = new Intent(ShopsActivity.this, CheaperActivity.class);
        startActivity(intent);
    }
}
