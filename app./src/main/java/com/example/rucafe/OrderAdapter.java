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

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<MenuItem> items;
    private int selected = RecyclerView.NO_POSITION;

    public OrderAdapter(Order order) {
        this.items = order.getItems();
    }

    public OrderAdapter(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public int getSelected() {
        return selected;
    }

    public void resetSelection() {
        selected = RecyclerView.NO_POSITION;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemDescription.setText(item.toString());
        if(selected == position) {
            holder.itemDescription.setBackgroundColor(Color.parseColor("#F6BDD1"));
        } else {
            holder.itemDescription.setBackgroundColor(Color.parseColor("#FBFBFF"));
        }
        holder.itemDescription.setOnClickListener(view -> {
            int oldSelected = selected;
            selected = holder.getAdapterPosition();
            Log.d("SELECTED POSITION", selected + " | Previous " + oldSelected);
            if (oldSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(oldSelected, items.get(oldSelected));
            }
            notifyItemChanged(selected);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView itemDescription;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.itemInformation);
        }
    }

}
