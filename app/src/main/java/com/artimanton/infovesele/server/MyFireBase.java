package com.artimanton.infovesele.server;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.artimanton.infovesele.adapters.BusAdapter;
import com.artimanton.infovesele.model.BusModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyFireBase {
    private RecyclerView recyclerView;
    private List<BusModel> result;
    private BusAdapter adapter;
    private Button btnPushToServer;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Context context;

    public MyFireBase(Context context) {
        this.context = context;
    }

    public void fireBaseInit(Context context){

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("transport/buses/zp");

        result = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BusAdapter(result);
        recyclerView.setAdapter(adapter);
        updateList();
    }

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


}
