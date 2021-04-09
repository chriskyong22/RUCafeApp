package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rucafe.Model.MenuItem;

import java.util.ArrayList;

public class DonutOrderActivity extends AppCompatActivity {

    ArrayList<MenuItem> donuts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
    }
}