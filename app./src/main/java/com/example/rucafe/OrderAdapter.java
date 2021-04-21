package com.example.rucafe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rucafe.Model.MenuItem;
import com.example.rucafe.Model.Order;

import java.util.ArrayList;

/**
 * Adapter for the RecyclerView, used to bind each item in the RecyclerView
 * to the MenuItem ArrayList.
 * @author Christopher Yong, Maya Ravichandran
 */
public class OrderAdapter extends RecyclerView
        .Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<MenuItem> items;
    private int selected = RecyclerView.NO_POSITION;

    /**
     * Constructor used to set the list of menu items in the adapter to
     * the model's menu items passed in.
     * @param order the order passed in to link the adapter's menu items to
     */
    public OrderAdapter(Order order) {
        this.items = order.getItems();
    }

    /**
     * Constructor used to set the list of menu items in the adapter to
     * the one passed in.
     * @param items ArrayList of Menu Items
     */
    public OrderAdapter(ArrayList<MenuItem> items) {
        this.items = items;
    }

    /**
     * Gets the selected index in the array.
     * @return the selected index
     */
    public int getSelected() {
        return selected;
    }

    /**
     * Resets the selected index.
     */
    public void resetSelection() {
        selected = RecyclerView.NO_POSITION;
    }

    /**
     * Creates a new view holder object that holds a view that has its
     * IDs associated.
     * @param parent parent context (parent view)
     * @param viewType viewType (not used)
     * @return the OrderViewHolder object
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

    /**
     * Binds the view holder object to one of the positions in the array and
     * allows certain actions to be done with that view holder object.
     * @param holder the view being passed in
     * @param position the position in the array
     */
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder,
                                 int position) {
        MenuItem item = items.get(position);
        holder.itemDescription.setText(item.toString());
        if (selected == position) {
            holder.itemDescription.setBackgroundColor(
                    Color.parseColor("#F6BDD1"));
        } else {
            holder.itemDescription.setBackgroundColor(
                    Color.parseColor("#FBFBFF"));
        }

        //Event listener for item clicker on the recycler view.
        //The parameter view is the recycler view.
        holder.itemDescription.setOnClickListener(view -> {
            int oldSelected = selected;
            selected = holder.getAdapterPosition();
            if (oldSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(oldSelected, items.get(oldSelected));
            }
            notifyItemChanged(selected);
        });
    }

    /**
     * Retrieves the number of menu items stored in the adapter.
     * @return number of menu items (item count)
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Class used to store the layout of each RecyclerView item (view holder).
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        protected TextView itemDescription;

        /**
         * Constructor to initialize and link the views inside the layout.
         * @param itemView the view passed in to link
         */
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.itemInformation);
        }
    }

}
