package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rucafe.Model.StoreOrders;

public class StoredOrdersActivity extends AppCompatActivity {

    private static StoreOrders orders = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_orders);
    }

    /**
     * Getter for the store orders object to add new orders.
     * @return StoreOrders object that represents all the orders that are
     *         currently stored that have not been removed.
     */
    public static StoreOrders getOrders() {
        return orders;
    }

}