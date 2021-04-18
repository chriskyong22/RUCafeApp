package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * TODO
 * @author Christopher Yong, Maya Ravichandran
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.menu_title));
    }

    /**
     * Handles the opening of the coffee menu.
     * @param v View being used
     */
    public void launchCoffeeActivity(View v) {
        Intent intent = new Intent(this, CoffeeOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the opening of the donut menu.
     * @param v View being used
     */
    public void launchDonutActivity(View v) {
        Intent intent = new Intent(this, DonutOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the opening of the current orders menu.
     * @param v View being used
     */
    public void launchCurrentOrderActivity(View v) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the opening of the stored orders menu.
     * @param v View being used
     */
    public void launchStoredOrdersActivity(View v) {
        Intent intent = new Intent(this, StoredOrdersActivity.class);
        startActivity(intent);
    }
}