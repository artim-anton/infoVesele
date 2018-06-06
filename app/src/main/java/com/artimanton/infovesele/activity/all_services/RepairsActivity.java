package com.artimanton.infovesele.activity.all_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ServicesModel> result;
    private ServicesAdapter adapter;
    private Button btnPushToServer;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);
        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }

        btnPushToServer = (Button) findViewById(R.id.btn_push_to_server);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("services/repairs");

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.services_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ServicesAdapter(result);
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
                result.add(dataSnapshot.getValue(ServicesModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ServicesModel services = dataSnapshot.getValue(ServicesModel.class);
                int index = getItemIndex(services);
                result.set(index, services);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ServicesModel services = dataSnapshot.getValue(ServicesModel.class);
                int index = getItemIndex(services);
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

    private int getItemIndex(ServicesModel services){
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).key.equals(services.key)){
                index = i;
                break;
            }

        }
        return index;
    }

    private void removeServices(int position){
        reference.child(result.get(position).key).removeValue();
    }

    public void changeServices(int position){
        ServicesModel services = result.get(position);
        Map<String, Object> servicesValue = services.toMap();
        Map<String, Object> newServices = new HashMap<>();
        newServices.put(services.key, servicesValue);
        reference.updateChildren(newServices);
    }

    public void pushToServer(View view) {
        changeServices(ServicesAdapter.getAdapterPosition());
    }

}
