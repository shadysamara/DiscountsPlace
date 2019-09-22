package com.example.discountsplace.main_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discountsplace.R;
import com.example.discountsplace.customerPage.CustomerPage;
import com.example.discountsplace.marketPage.MarketPage;

public class Splach extends AppCompatActivity {
Button login;
TextView guest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_splach);
        login = findViewById(R.id.button);
        guest = findViewById(R.id.textView);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splach.this, Login.class);
                startActivity(intent);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splach.this, CustomerPage.class);
                startActivity(intent);
            }
        });
    }
}
