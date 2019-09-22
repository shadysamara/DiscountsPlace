package com.example.discountsplace.customerPage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discountsplace.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerPage extends AppCompatActivity {
    SharedPreferences.Editor editor;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_markets:
                    getSupportFragmentManager().beginTransaction().replace(R.id.customermainContainer,new AllMarkets()).commit();
                    return true;
                case R.id.nav_products:
                    getSupportFragmentManager().beginTransaction().replace(R.id.customermainContainer,new AllProducts()).commit();

                    return true;



            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        editor = getSharedPreferences("customer", MODE_PRIVATE).edit();
        BottomNavigationView navView = findViewById(R.id.nav_customer_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.setSelectedItemId(R.id.nav_products);

    }
}
