package com.artimanton.infovesele.activity.all_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.adapters.ServicesAdapter;
import com.artimanton.infovesele.model.ServicesModel;
import com.artimanton.infovesele.permission.Internet;
import com.artimanton.infovesele.utilities.BackGroundActivity;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ServicesModel> result;
    private ServicesAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        //BackGroundActivity.setBackground(this, this);
        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }

        final PhotoView photoView = findViewById(R.id.img_gaz);

        Picasso.with(this)
                .load("http://s1vesele.ucoz.net/infoVesele/gaz.jpg")
                .into(photoView);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("services/car");

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.services_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ServicesAdapter(result);
        recyclerView.setAdapter(adapter);

        BaseServicesActivity baseServicesActivity = new BaseServicesActivity(result,adapter,reference);
        baseServicesActivity.updateList();
    }
}
