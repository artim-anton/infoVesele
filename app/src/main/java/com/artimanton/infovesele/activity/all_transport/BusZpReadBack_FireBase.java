package com.artimanton.infovesele.activity.all_transport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artimanton.infovesele.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.adapters.BusAdapter;
import com.artimanton.infovesele.model.BusModel;
import com.artimanton.infovesele.permission.Internet;
import com.artimanton.infovesele.server.MyFireBase;
import com.artimanton.infovesele.utilities.BackGroundActivity;
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

public class BusZpReadBack_FireBase extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<BusModel> result;
    private BusAdapter adapter;
//    private Button btnPushToServer;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private EditText etCallZpReadBackOne;
    private EditText etCallZpReadBackTwo;
    private EditText etCallZpReadBackThree;
    private EditText etCallZpReadBackFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_zp_read_back);

        if (!Internet.isOnline(this)){
            Toast.makeText(this, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show();
        }

        final PhotoView photoView = findViewById(R.id.img_zp);

        Picasso.get()
                .load("http://s1vesele.ucoz.net/infoVesele/zp_white_svan.jpg")
                .into(photoView);

        //btnPushToServer = (Button) findViewById(R.id.btn_push_to_server);
        recyclerView =  findViewById(R.id.bus_list_back);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("transport/buses/zp_back");


        result = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BusAdapter(result);
        recyclerView.setAdapter(adapter);

        MyFireBase myFireBase = new MyFireBase(this);
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

    public void btnCallZpReadBackOne(View view) {
        etCallZpReadBackOne = (EditText) findViewById(R.id.et_phone_zp_read_one);
        String phone = etCallZpReadBackOne.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public void btnCallZpReadBackTwo(View view) {
        etCallZpReadBackTwo = (EditText) findViewById(R.id.et_phone__zp_read_two);
        String phone = etCallZpReadBackTwo.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public void btnCallZpReadBackThree(View view) {
        etCallZpReadBackThree = (EditText) findViewById(R.id.et_phone__zp_read_three);
        String phone = etCallZpReadBackThree.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public void btnCallZpReadBackFour(View view) {
        etCallZpReadBackFour = (EditText) findViewById(R.id.et_phone__zp_read_four);
        String phone = etCallZpReadBackFour.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}
