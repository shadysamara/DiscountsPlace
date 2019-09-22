package com.example.discountsplace.customerPage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.discountsplace.R;
import com.example.discountsplace.adapters.marketAdapter;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.models.Market;
import com.example.discountsplace.adapters.marketAdapter;
import com.example.discountsplace.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllMarkets extends Fragment {
    ArrayList<Market> data = new ArrayList<>();
    com.example.discountsplace.adapters.marketAdapter marketAdapter;

    RecyclerView rvData;


    public AllMarkets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all_markets, container, false);
        getJSONString();
        rvData = view.findViewById(R.id.rvData);

        RecyclerView.LayoutManager manager =  new LinearLayoutManager(getActivity());
        rvData.setLayoutManager(manager);
        return view;

    }

    public void getJSONString() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, utilities.ip+"market_display", null, new Response.Listener<JSONArray>() {
            JSONObject jsonObject;

            @Override

            public void onResponse(JSONArray response) {

//                Toast.makeText(getActivity(), response.length()+"", Toast.LENGTH_SHORT).show();
                for(int i = 0;i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String city = jsonObject.getString("city");
                        String address = jsonObject.getString("address");
                        String phone = jsonObject.getString("phone");
                        String catagory = jsonObject.getString("catagory");
                        String rating = jsonObject.getString("rating");
                        String image = jsonObject.getString("image");
                     data.add(new Market(id,name,city,address,phone,catagory,image,Double.parseDouble(rating)));
//
//



                        //Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                marketAdapter = new marketAdapter(data,getActivity());
                rvData.setAdapter(marketAdapter);

//                productAdapter = new productAdapter(data,getActivity());
//                rvData.setAdapter(productAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}



