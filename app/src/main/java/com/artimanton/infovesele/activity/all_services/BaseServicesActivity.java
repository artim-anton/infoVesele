package com.artimanton.infovesele.activity.all_services;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.artimanton.infovesele.adapters.ServicesAdapter;
import com.artimanton.infovesele.model.ServicesModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServicesActivity extends AppCompatActivity{
    private  List<ServicesModel> result;
    private  ServicesAdapter adapter;
    private  DatabaseReference reference;

    public BaseServicesActivity() {

    }

    public BaseServicesActivity(List<ServicesModel> result, ServicesAdapter adapter, DatabaseReference reference) {
        this.result = result;
        this.adapter = adapter;
        this.reference = reference;
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

    public void updateList(){
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
