package com.artimanton.infovesele.activity.all_services;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;
import com.squareup.picasso.Picasso;

public class AddServicesActivity extends AppCompatActivity {
    EditText etPhoneReclame;
    ImageView imageReclame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);
        BackGroundActivity.setBackground(this, this);
        imageReclame= (ImageView) findViewById(R.id.img_reclame);

        Picasso.with(this)
                .load("https://drogmedia.net.ua/wp-content/uploads/2017/10/Reklama-na-bloge-dlya-vas-po-dostupny-m-tsenam.png")
                .into(imageReclame);


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/



    }


    public void backButton(View view) {
        finish();
    }

    public void btnCallReclame(View view) {
        etPhoneReclame = (EditText) findViewById(R.id.et_phone_reclame);
        String phone = etPhoneReclame.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);


    }
}
