package com.artimanton.infovesele.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_transport.BusCity;
import com.artimanton.infovesele.activity.all_transport.DeliveryService;
import com.artimanton.infovesele.activity.all_transport.FuelingActivity;
import com.artimanton.infovesele.activity.all_transport.TaxiRead_FireBase;
import com.artimanton.infovesele.activity.all_transport.Train;

public class TransportActivity extends BaseActivity {
    private static final int REQUEST_READ_PHONE_STATE = 10001;
    private static final String READ_PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        setupBottomNavigation(1, this);

        // проверяем разрешения: если они уже есть,
        // то приложение продолжает работу в нормальном режиме
        if (isPermissionGranted(READ_PHONE_STATE_PERMISSION)) {
            //Toast.makeText(this, "Разрешения есть, можно работать", Toast.LENGTH_SHORT).show();
        } else {
            // иначе запрашиваем разрешение у пользователя
            requestPermission(READ_PHONE_STATE_PERMISSION, REQUEST_READ_PHONE_STATE);
        }
    }

    public boolean isPermissionGranted(String permission) {
        // проверяем разрешение - есть ли оно у нашего приложения
        int permissionCheck = ActivityCompat.checkSelfPermission(this, permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(TransportActivity.this, "Разрешения получены", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(TransportActivity.this, "Разрешения не получены", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void requestPermission(String permission, int requestCode) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }


    public void btTrains(View view) {
        Intent intent = new Intent(TransportActivity.this, Train.class);
        startActivity(intent);
    }

    public void btBuses(View view) {
        Intent intent = new Intent(TransportActivity.this, BusCity.class);
        startActivity(intent);
    }


    public void btTaxi(View view) {
        Intent intent = new Intent(TransportActivity.this, TaxiRead_FireBase.class);
        startActivity(intent);
    }


    public void btDelivery(View view) {
        Intent intent = new Intent(TransportActivity.this, DeliveryService.class);
        startActivity(intent);
    }

    public void btFueling(View view) {
        Intent intent = new Intent(TransportActivity.this, FuelingActivity.class);
        startActivity(intent);
    }

}
