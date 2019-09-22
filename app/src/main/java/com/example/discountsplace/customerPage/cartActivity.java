package com.example.discountsplace.customerPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discountsplace.R;
import com.example.discountsplace.adapters.cartAdapter;
import com.example.discountsplace.adapters.sqliteAdapter;
import com.example.discountsplace.models.cart;


import java.util.ArrayList;

public class cartActivity extends AppCompatActivity {
RecyclerView recyclerView;
sqliteAdapter sqliteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cartrv);
        sqliteAdapter = new sqliteAdapter(cartActivity.this);
        ArrayList<cart> data = sqliteAdapter.getallProductsdata();
        LinearLayoutManager manager = new LinearLayoutManager(cartActivity.this);
        recyclerView.setLayoutManager(manager);
        cartAdapter cartAdapter = new cartAdapter(cartActivity.this,data);
        recyclerView.setAdapter(cartAdapter);

    }
}
