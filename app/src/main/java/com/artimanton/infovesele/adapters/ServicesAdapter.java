package com.artimanton.infovesele.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_services.RepairsActivity;
import com.artimanton.infovesele.model.ServicesModel;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {
    private List<ServicesModel> list;
    RepairsActivity activity;

    private static int adapterPosition;

    public static int getAdapterPosition() {
        return adapterPosition;
    }

    public ServicesAdapter(List<ServicesModel> list) {
        this.list = list;
    }

    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServicesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_services, parent, false));
    }

    @Override
    public void onBindViewHolder(final ServicesViewHolder holder, final int position) {
        final ServicesModel services = list.get(position);
        final Context context = holder.itemView.getContext();
        holder.etMoreInfoServices.setText(services.more_info_services);
        holder.etInfoServices.setText(services.info_services);
        holder.etPhone.setText(services.phone);
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", services.phone, null));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);

            }
        });

        /*holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "После редактированя, нажмите сохранить", Toast.LENGTH_SHORT).show();
                holder.etInfoCar.setEnabled(true);
                holder.etPhone.setEnabled(true);
                services.info_car = holder.etInfoCar.getText().toString();
                services.phone = holder.etPhone.getText().toString();
                adapterPosition = holder.getAdapterPosition();
            }
        });*/

        //Context menu
        /*holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Удалить");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "Изменить");

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder{
        private EditText etInfoServices, etPhone;
        private TextView etMoreInfoServices;
        private ImageButton btnCall;

        private ServicesViewHolder(View itemView) {
            super(itemView);
            etMoreInfoServices = itemView.findViewById(R.id.et_more_info_services);
            etInfoServices = itemView.findViewById(R.id.et_info_services);
            etPhone = itemView.findViewById(R.id.et_phone_services);
           // btnEdit = itemView.findViewById(R.id.btn_edit);
            btnCall = itemView.findViewById(R.id.btn_call_services);
        }
    }

}