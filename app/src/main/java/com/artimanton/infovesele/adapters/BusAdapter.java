package com.artimanton.infovesele.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.BusActivity;
import com.artimanton.infovesele.model.BusModel;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {
    private List<BusModel> list;
    private Context mcon;


    public BusAdapter(Context con, List<BusModel> list) {
        mcon = con;
        this.list = list;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final BusViewHolder holder, final int position) {
        final BusModel bus = list.get(position);
        final Context context = holder.itemView.getContext();
        holder.etTimeGo.setText(bus.time_go);
        holder.etTimeFinish.setText(bus.time_finish);
        holder.etPhone.setText(bus.phone);
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", bus.phone, null));
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

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Удалить");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "Изменить");

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BusViewHolder extends RecyclerView.ViewHolder{
        EditText etTimeGo, etTimeFinish, etPhone;
        ImageButton btnCall, btnEdit;

        private BusViewHolder(View itemView) {
            super(itemView);
            etTimeGo = itemView.findViewById(R.id.et_time_go);
            etTimeFinish = itemView.findViewById(R.id.et_time_finish);
            etPhone = itemView.findViewById(R.id.et_phone);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnCall = itemView.findViewById(R.id.btn_call);
        }
    }
}
