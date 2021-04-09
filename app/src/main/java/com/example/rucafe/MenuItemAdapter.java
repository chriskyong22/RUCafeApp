package com.example.rucafe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rucafe.Model.MenuItem;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>{
    ArrayList<MenuItem> menuItems;

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new MenuItemAdapter.MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.itemDescription.setText(item.toString());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        public TextView itemDescription;
        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.itemInformation);
        }
    }

}
