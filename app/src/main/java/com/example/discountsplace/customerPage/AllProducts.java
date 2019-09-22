package com.example.discountsplace.customerPage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.discountsplace.R;
import com.example.discountsplace.adapters.productAdapter;
import com.example.discountsplace.adapters.products_cat_adapter;
import com.example.discountsplace.adapters.sqliteAdapter;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.models.Category;
import com.example.discountsplace.models.Product;
import com.example.discountsplace.utilities;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllProducts extends Fragment {

    productAdapter productAdapter;
    products_cat_adapter products_cat_adapter;
    RecyclerView rvData;
    RecyclerView catrvData;
    ArrayList<Product> data = new ArrayList<>();
    ArrayList<Category> catdata = new ArrayList<>();


    Product product;
    public AllProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_all_products, container, false);
        sqliteAdapter sqliteAdapter = new sqliteAdapter(getActivity());

         getJSONString();
        Allcategories();


        catrvData = view.findViewById(R.id.catrvData);
        LinearLayoutManager manager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        catrvData.setLayoutManager(manager2);




        rvData = view.findViewById(R.id.rvData);
        RecyclerView.LayoutManager manager =  new GridLayoutManager(getActivity(),2);
        rvData.setLayoutManager(manager);
         return view;
    }


    public void getJSONString() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, utilities.ip+"products_display", null, new Response.Listener<JSONArray>() {


            @Override

            public void onResponse(JSONArray response) {

//                Toast.makeText(getActivity(), response.length()+"", Toast.LENGTH_SHORT).show();
                for(int i = 0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int product_id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                       int market_id = Integer.parseInt(jsonObject.getString("market_id"));
                        String catagory = jsonObject.getString("catagory");
                        double old_price = Double.parseDouble(jsonObject.getString("old_price"));
                        double new_price = Double.parseDouble(jsonObject.getString("new_price"));
                        double rating = Double.parseDouble(jsonObject.getString("rating"));
                        String image_url = jsonObject.getString("image");
                        data.add(new Product(product_id,name,old_price,new_price,market_id,catagory,description,image_url,rating));
//



                        //Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                productAdapter = new productAdapter(data,getActivity());
                rvData.setAdapter(productAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }


    public void Allcategories(){
        data = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, utilities.ip + "display_cat", null, new Response.Listener<JSONArray>() {
            JSONObject jsonObject;
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0;i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String image_url = jsonObject.getString("image");

                        catdata.add(new Category(id,name,image_url));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                products_cat_adapter = new products_cat_adapter(getActivity(),catdata);
                catrvData.setAdapter(products_cat_adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
