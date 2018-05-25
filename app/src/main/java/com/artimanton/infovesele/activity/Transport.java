package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;

public class Transport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
    }


    public void btTrains(View view) {
    }

    public void btBuses(View view) {
        Intent intent = new Intent(Transport.this, BusCity.class);
        startActivity(intent);
    }


    public void btTaxi(View view) {
        Intent intent = new Intent(Transport.this, TaxiRead.class);
        startActivity(intent);
    }
}
