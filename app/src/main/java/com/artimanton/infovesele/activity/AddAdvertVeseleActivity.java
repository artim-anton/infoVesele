package com.artimanton.infovesele.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.model.AdvertModel;
import com.artimanton.infovesele.model.BusModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddAdvertVeseleActivity extends AppCompatActivity {

    private EditText etInfoAdvert, etPhone, etMoreInfoAdvert, etPrice;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert_vesele);

        etPrice = (EditText) findViewById(R.id.et_price_add);
        etMoreInfoAdvert = (EditText) findViewById(R.id.et_more_info_advert_add);
        etInfoAdvert = (EditText) findViewById(R.id.et_info_advert_add);
        etPhone = (EditText) findViewById(R.id.et_phone_add);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("advert");
    }

    public void btnSaveAdvert(View view) {
        String id = reference.push().getKey();
        AdvertModel newAdvert = new AdvertModel(etPrice.getText().toString(), etMoreInfoAdvert.getText().toString(), etInfoAdvert.getText().toString(), etPhone.getText().toString(), id);

        Map<String, Object> advertValue = newAdvert.toMap();
        Map<String, Object> bus = new HashMap<>();
        bus.put(id, advertValue);
        reference.updateChildren(bus);
        closeActivity();
    }
    private void closeActivity() {
        this.finish();
    }
}
