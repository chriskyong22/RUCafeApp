package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.menu_title);
    }

    public void launchCoffeeActivity(View v) {
        Intent intent = new Intent(this, CoffeeOrderActivity.class);
        startActivity(intent);
    }

    public void launchDonutActivity(View v) {
        Intent intent = new Intent(this, DonutOrderActivity.class);
        startActivity(intent);
    }

    public void launchCurrentOrderActivity(View v) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    public void launchStoredOrdersActivity(View v) {
        Intent intent = new Intent(this, StoredOrdersActivity.class);
        startActivity(intent);
    }
}