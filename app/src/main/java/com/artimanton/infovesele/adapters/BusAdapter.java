package com.artimanton.infovesele.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.model.BusModel;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {
    private List<BusModel> list;

    public BusAdapter(List<BusModel> list) {
        this.list = list;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final BusViewHolder holder, int position) {
        BusModel bus = list.get(position);
        holder.etTimeGo.setText(bus.time_go);
        holder.etTimeFinish.setText(bus.time_finish);
        holder.etPhone.setText(bus.phone);

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

        public BusViewHolder(View itemView) {
            super(itemView);
            etTimeGo = (EditText) itemView.findViewById(R.id.et_time_go);
            etTimeFinish = (EditText) itemView.findViewById(R.id.et_time_finish);
            etPhone = (EditText) itemView.findViewById(R.id.et_phone);
        }
    }
}
