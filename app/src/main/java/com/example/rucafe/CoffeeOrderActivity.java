package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class CoffeeOrderActivity extends AppCompatActivity {
    Spinner coffeeSize;
    CheckBox cream, syrup, milk, caramel, whippedCream;
    TextView subTotal;
    Button addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        coffeeSize = findViewById(R.id.coffeeSize);
        cream = findViewById(R.id.creamCheck);
        syrup = findViewById(R.id.syrupCheck);
        milk = findViewById(R.id.milkCheck);
        caramel = findViewById(R.id.caramelCheck);
        whippedCream = findViewById(R.id.whippedCreamCheck);
        subTotal = findViewById(R.id.coffeeSubTotal);
        addToCart = findViewById(R.id.addToCartButton);
    }




}