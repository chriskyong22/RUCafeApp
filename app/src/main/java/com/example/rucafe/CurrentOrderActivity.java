package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rucafe.Model.Order;

public class CurrentOrderActivity extends AppCompatActivity {

    private static Order currentOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
    }

    /**
     * Getter for the Current Order object to add the menu items.
     * @return Order object which represents the current order
     */
    public static Order getCurrentOrder() {
        return currentOrder;
    }

}