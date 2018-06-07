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
import com.artimanton.infovesele.model.AdvertModel;

import java.util.List;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertViewHolder> {
    private List<AdvertModel> list;

    private static int adapterPosition;

    public static int getAdapterPosition() {
        return adapterPosition;
    }

    public AdvertAdapter(List<AdvertModel> list) {
        this.list = list;
    }

    @Override
    public AdvertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdvertViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_advert, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdvertViewHolder holder, final int position) {
        final AdvertModel advert = list.get(position);
        final Context context = holder.itemView.getContext();
        holder.etPrice.setText(advert.price);
        holder.etMoreInfoAdvert.setText(advert.more_info_advert);
        holder.etInfoAdvert.setText(advert.info_advert);
        holder.etPhone.setText(advert.phone);
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", advert.phone, null));
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
                advert.info_car = holder.etInfoCar.getText().toString();
                advert.phone = holder.etPhone.getText().toString();
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

    class AdvertViewHolder extends RecyclerView.ViewHolder{
        private EditText etInfoAdvert, etPhone;
        private TextView etMoreInfoAdvert, etPrice;
        private ImageButton btnCall;

        private AdvertViewHolder(View itemView) {
            super(itemView);
            etPrice = itemView.findViewById(R.id.et_price);
            etMoreInfoAdvert = itemView.findViewById(R.id.et_more_info_advert);
            etInfoAdvert = itemView.findViewById(R.id.et_info_advert);
            etPhone = itemView.findViewById(R.id.et_phone);
           // btnEdit = itemView.findViewById(R.id.btn_edit);
            btnCall = itemView.findViewById(R.id.btn_call);
        }
    }

}