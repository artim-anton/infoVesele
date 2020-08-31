package com.artimanton.infovesele.activity.all_transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;
import com.squareup.picasso.Picasso;

public class Train extends AppCompatActivity {
    ImageView imageTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        imageTrain = (ImageView) findViewById(R.id.img_ukrainian_railways);

        Picasso.with(this)
                .load("https://steamusercontent-a.akamaihd.net/ugc/89346793346185062/FC43E449086EEE508073413310E0A9994B291FCF/")
                .into(imageTrain);
    }
}
