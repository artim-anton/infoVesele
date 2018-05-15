package com.artimanton.infovesele;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.artimanton.infovesele.activity.BusAdd;
import com.artimanton.infovesele.activity.Transport;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btTransport(View view) {
        Intent intent = new Intent(MainActivity.this, Transport.class);
        startActivity(intent);
    }
}
