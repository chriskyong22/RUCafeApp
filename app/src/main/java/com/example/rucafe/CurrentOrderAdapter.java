package com.example.rucafe;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rucafe.Model.MenuItem;
import com.example.rucafe.Model.Order;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder> {
    private Order currentOrder;
    private int selected = RecyclerView.NO_POSITION;

    public CurrentOrderAdapter(Order order) {
        this.currentOrder = order;
    }

    public int getSelected() {
        return selected;
    }

    public void resetSelection() {
        selected = RecyclerView.NO_POSITION;
    }

    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new CurrentOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
        MenuItem item = currentOrder.getItem(position);
        if(selected == position) {
            holder.itemDescription.setBackgroundColor(Color.parseColor("#73D2DE"));
        } else {

            holder.itemDescription.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.itemDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int oldSelected = selected;
                selected = holder.getAdapterPosition();

                Log.d("SELECTED POSITION", String.valueOf(selected) + " | Previous " + oldSelected);
                if (oldSelected != RecyclerView.NO_POSITION) {
                    notifyItemChanged(oldSelected, currentOrder.getItem(oldSelected));
                }
                notifyItemChanged(selected);
            }
        });
        holder.itemDescription.setText(item.toString());
    }

    @Override
    public int getItemCount() {
        return currentOrder.getNumberOfMenuItems();
    }

    public class CurrentOrderViewHolder extends RecyclerView.ViewHolder {
        public TextView itemDescription;
        public CurrentOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.itemInformation);
        }
    }

}
