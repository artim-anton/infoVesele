package com.artimanton.infovesele.activity.all_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.adapters.ServicesAdapter;
import com.artimanton.infovesele.model.ServicesModel;
import com.artimanton.infovesele.permission.Internet;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ServicesModel> result;
    private ServicesAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    ImageView imageViewPcMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);
        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }
        imageViewPcMaster = (ImageView) findViewById(R.id.img_pc_master);

     Picasso.get()
                .load("http://s1vesele.ucoz.net/infoVesele/pc_master.jpg")
                .into(imageViewPcMaster);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("services/repairs");

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
