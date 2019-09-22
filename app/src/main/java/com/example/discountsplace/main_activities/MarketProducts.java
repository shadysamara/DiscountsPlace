package com.example.discountsplace.main_activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.discountsplace.R;
import com.example.discountsplace.adapters.productAdapter;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.models.Product;
import com.example.discountsplace.utilities;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MarketProducts extends AppCompatActivity {
    productAdapter productAdapter;

    RecyclerView rvData;
    ArrayList<Product> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_products);
        String flag = getIntent().getStringExtra("flag");

        if(flag.equals("market")){
            int id = getIntent().getIntExtra("market_id",0);
            marketProducts(id);



        }
        else{
            int cat_id = getIntent().getIntExtra("cat_id",0);
            Log.e("recieved_id",cat_id+"");
            catProducts(cat_id);


        }
        rvData = findViewById(R.id.mprvData);
        RecyclerView.LayoutManager manager =  new GridLayoutManager(MarketProducts.this,2);
        rvData.setLayoutManager(manager);







    }



    public void marketProducts(final int id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"showMarketProducts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responceRes",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            data = new ArrayList<>();
                            for(int i = 0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                int market_id = Integer.parseInt(jsonObject.getString("market_id"));
                                String catagory = jsonObject.getString("catagory");
                                double old_price = Double.parseDouble(jsonObject.getString("old_price"));
                                double new_price = Double.parseDouble(jsonObject.getString("new_price"));
                                double rating = Double.parseDouble(jsonObject.getString("rating"));
                                String image_url = jsonObject.getString("image");
                                data.add(new Product(name,old_price,new_price,market_id,catagory,description,image_url,rating));
//

                            }











                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        productAdapter = new productAdapter(data,MarketProducts.this);
                        rvData.setAdapter(productAdapter);


                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketProducts.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("market_id", String.valueOf(id));



                return map;
            }


        };


        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public void catProducts(final int id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"showCatProducts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            data = new ArrayList<>();
                            for(int i = 0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                int market_id = Integer.parseInt(jsonObject.getString("market_id"));
                                String catagory = jsonObject.getString("catagory");
                                double old_price = Double.parseDouble(jsonObject.getString("old_price"));
                                double new_price = Double.parseDouble(jsonObject.getString("new_price"));
                                double rating = Double.parseDouble(jsonObject.getString("rating"));
                                String image_url = jsonObject.getString("image");
                                data.add(new Product(name,old_price,new_price,market_id,catagory,description,image_url,rating));
//

                            }











                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        productAdapter = new productAdapter(data,MarketProducts.this);
                        rvData.setAdapter(productAdapter);


                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketProducts.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("cat_id", String.valueOf(id));



                return map;
            }


        };


        AppController.getInstance().addToRequestQueue(stringRequest);

    }


}
