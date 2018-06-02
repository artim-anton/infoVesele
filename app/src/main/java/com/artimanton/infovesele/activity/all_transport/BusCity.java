package com.artimanton.infovesele.activity.all_transport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;

public class BusCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_city);
    }

    public void Melitopol(View view) {
        Intent intent = new Intent(BusCity.this, BusMelitopolRead.class);
        startActivity(intent);
    }

    public void Zp(View view) {
        Intent intent = new Intent(BusCity.this, BusZpRead_FireBase.class);
        startActivity(intent);
    }

    public void Zp_back(View view) {
        Intent intent = new Intent(BusCity.this, BusZpReadBack_FireBase.class);
        startActivity(intent);
    }

    public void Melitopol_back(View view) {
        Intent intent = new Intent(BusCity.this, BusMelitopolReadBack.class);
        startActivity(intent);
    }
}