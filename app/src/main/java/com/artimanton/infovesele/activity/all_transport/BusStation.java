package com.artimanton.infovesele.activity.all_transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class BusStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station);


        final PhotoView photoView = findViewById(R.id.img_bus_station);
        Toast.makeText(this, "Загрузка...", Toast.LENGTH_LONG).show();
        Picasso.get()
                .load("http://s1vesele.ucoz.net/infoVesele/bus_station.jpg")
                .into(photoView);
    }

    public void backButton(View view) {
        finish();
    }
}
