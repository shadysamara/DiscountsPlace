package com.example.discountsplace.marketPage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.discountsplace.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MarketPage extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_add:
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new AddProductFragment()).commit();
                    return true;
                case R.id.nav_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new ProductsFragment()).commit();

                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_page);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.setSelectedItemId(R.id.nav_add);
    }

}
