package com.artimanton.infovesele.activity.all_transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.adapters.TaxiAdapter;
import com.artimanton.infovesele.model.TaxiModel;
import com.artimanton.infovesele.permission.Internet;
import com.github.chrisbanes.photoview.PhotoView;
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

public class TaxiRead_FireBase extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<TaxiModel> result;
    private TaxiAdapter adapter;
//    private Button btnPushToServer;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_read);

        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }

        final PhotoView photoView = findViewById(R.id.img_taxi);

        Picasso.get()
                .load("http://s1vesele.ucoz.net/infoVesele/taxi_lanos.jpg")
                .into(photoView);

       // btnPushToServer = (Button) findViewById(R.id.btn_push_to_server);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("transport/taxi");

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.taxi_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaxiAdapter(result);
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
                result.add(dataSnapshot.getValue(TaxiModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                TaxiModel taxi = dataSnapshot.getValue(TaxiModel.class);
                int index = getItemIndex(taxi);
                result.set(index, taxi);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TaxiModel taxi = dataSnapshot.getValue(TaxiModel.class);
                int index = getItemIndex(taxi);
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

    private int getItemIndex(TaxiModel taxi){
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).key.equals(taxi.key)){
                index = i;
                break;
            }

        }
        return index;
    }

    private void removeTaxi(int position){
        reference.child(result.get(position).key).removeValue();
    }

    public void changeTaxi(int position){
        TaxiModel taxi = result.get(position);
        Map<String, Object> taxiValue = taxi.toMap();
        Map<String, Object> newTaxi = new HashMap<>();
        newTaxi.put(taxi.key, taxiValue);
        reference.updateChildren(newTaxi);
    }

    public void pushToServer(View view) {
        changeTaxi(TaxiAdapter.getAdapterPosition());
    }

}
