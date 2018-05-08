package com.artimanton.infovesele;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.artimanton.infovesele.model.BusModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Bus extends AppCompatActivity {

    private TextView tvToZP;
    private Button btAddBus;
    private EditText etTimeGo;
    private EditText etTimeFinish;
    private EditText etPhone;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        tvToZP = (TextView) findViewById(R.id.tv_to_zp);
        btAddBus = (Button) findViewById(R.id.btAddBus);
        btAddBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBus();
            }
        });
       // etTimeGo = (EditText) findViewById(R.id.et_time_go);
       // etTimeFinish = (EditText) findViewById(R.id.et_time_finish);
        //etPhone = (EditText) findViewById(R.id.et_phone);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("buses");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvToZP.setText("");
                //tvToZP.setText(dataSnapshot.toString());

                for(DataSnapshot bus: dataSnapshot.getChildren()) {
                    tvToZP.append(bus.getValue(BusModel.class).toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addBus(){
        String id = reference.push().getKey();
        BusModel newBus = new BusModel("8:20","10:20", "0674512821", id);

        Map<String, Object> busValue = newBus.toMap();
        Map<String, Object> bus = new HashMap<>();
        bus.put(id, busValue);
        reference.updateChildren(bus);
    }
}
