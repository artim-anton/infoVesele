package com.artimanton.infovesele.activity.all_transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.adapters.BusAdapter;
import com.artimanton.infovesele.model.BusModel;
import com.artimanton.infovesele.permission.Internet;
import com.artimanton.infovesele.utilities.BackGroundActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusMelitopolRead extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<BusModel> result;
    private BusAdapter adapter;
  //  private Button btnPushToServer;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_melitopol_read);

        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }

        //btnPushToServer = (Button) findViewById(R.id.btn_push_to_server);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("transport/buses/melitopol");

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.bus_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BusAdapter(result);
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
                result.add(dataSnapshot.getValue(BusModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                BusModel bus = dataSnapshot.getValue(BusModel.class);
                int index = getItemIndex(bus);
                result.set(index, bus);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                BusModel bus = dataSnapshot.getValue(BusModel.class);
                int index = getItemIndex(bus);
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

    private int getItemIndex(BusModel bus){
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).key.equals(bus.key)){
                index = i;
                break;
            }

        }
        return index;
    }

    private void removeBus(int position){
        reference.child(result.get(position).key).removeValue();
    }

    public void changeBus(int position){
        BusModel bus = result.get(position);
        Map<String, Object> busValue = bus.toMap();
        Map<String, Object> newBus = new HashMap<>();
        newBus.put(bus.key, busValue);
        reference.updateChildren(newBus);
    }

    public void pushToServer(View view) {
        changeBus(BusAdapter.getAdapterPosition());
    }

}
