package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_services.BuilderActivity;
import com.artimanton.infovesele.adapters.AdvertAdapter;
import com.artimanton.infovesele.adapters.ServicesAdapter;
import com.artimanton.infovesele.model.AdvertModel;
import com.artimanton.infovesele.model.ServicesModel;
import com.artimanton.infovesele.permission.Internet;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertVeseleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<AdvertModel> result;
    private AdvertAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_vesele);
        setupBottomNavigation(2,this);
        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("advert");

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.advert_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdvertAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();

    }
    //Context menu
   /*  @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                removeBus(item.getGroupId());
                break;
            case 1:
                changeBus(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }*/

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(AdvertModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AdvertModel advert = dataSnapshot.getValue(AdvertModel.class);
                int index = getItemIndex(advert);
                result.set(index, advert);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                AdvertModel advert = dataSnapshot.getValue(AdvertModel.class);
                int index = getItemIndex(advert);
                result.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(AdvertModel advert){
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).key.equals(advert.key)){
                index = i;
                break;
            }

        }
        return index;
    }

    private void removeAdvert(int position){
        reference.child(result.get(position).key).removeValue();
    }

    public void changeAdvert(int position){
        AdvertModel advert = result.get(position);
        Map<String, Object> advertValue = advert.toMap();
        Map<String, Object> newAdvert = new HashMap<>();
        newAdvert.put(advert.key, advertValue);
        reference.updateChildren(newAdvert);
    }

    public void pushToServer(View view) {
        changeAdvert(AdvertAdapter.getAdapterPosition());
    }

    public void btnAddAdvert(View view) {
        Intent intent = new Intent(AdvertVeseleActivity.this, AddAdvertVeseleActivity.class);
        startActivity(intent);
    }
}

