package com.artimanton.infovesele.activity.all_transport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class BusNewBogdanovka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_new_bogdanovka);
    }

    public void btnCallNB(View view) {
        TextView etPhoneNB =  findViewById(R.id.et_phone_nb);
        String phone = etPhoneNB.getText().toString();
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
