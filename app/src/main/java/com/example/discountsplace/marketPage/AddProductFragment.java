package com.example.discountsplace.marketPage;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.discountsplace.R;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.utilities;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment implements IPickResult {
    ImageView product_image;
    EditText product_name_tv;
    EditText product_desc_tv;
    List<String> catData = new ArrayList<>();

    EditText product_oprice_tv;
    EditText product_nprice_tv;
    EditText product_rating_tv;
    Button add_product;
    Spinner catagories;
    Bitmap bitmap;
    String image64;
    SharedPreferences prefs;
    int market_id;



    String product_name;
    String product_desc;
    String old_price;
    String new_price;
    String product_category;

    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        Allcategories();
        product_image = view.findViewById(R.id.product_image);
        product_name_tv = view.findViewById(R.id.product_name);
        product_oprice_tv = view.findViewById(R.id.product_oprice);
        product_nprice_tv = view.findViewById(R.id.product_nprice);
        add_product = view.findViewById(R.id.btn_add_discount);
        catagories =  view.findViewById(R.id.product_category);








        product_desc_tv= view.findViewById(R.id.product_description);
        prefs = getActivity().getSharedPreferences("market",MODE_PRIVATE);
        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                if (r.getError() == null) {

                                    product_image.setImageBitmap(r.getBitmap());
                                    product_image.setScaleType(ImageView.ScaleType.CENTER_CROP);


                                }else{
                                    Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }).show(getFragmentManager());

            }
        });








        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_name = product_name_tv.getText().toString();
                product_desc = product_desc_tv.getText().toString();
                old_price = product_oprice_tv.getText().toString();
                new_price=product_nprice_tv.getText().toString();
                product_category=catagories.getSelectedItem().toString();

                bitmap = ((BitmapDrawable) product_image.getDrawable()).getBitmap();
                image64 = utilities.imagetobase64(bitmap);
                market_id = prefs.getInt("market_id",0);
                getJSONString();
            }
        });
        return view;
    }



////////////////////////////////////////////////

    public void Allcategories(){
        catData = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, utilities.ip + "display_cat", null, new Response.Listener<JSONArray>() {
            JSONObject jsonObject;
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0;i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);

                        String name = jsonObject.getString("name");


                        catData.add(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, catData);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                catagories.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    ////////////////////////////////////////////////////
    public void getJSONString(){



        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"product_control",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        // imageView2.setImageBitmap(utilities.base64toImage(response));



                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {

            //
//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", product_name);
                map.put("old_price", old_price);
                map.put("new_price", new_price);
                map.put("market_id", String.valueOf(market_id));
                map.put("category", product_category);
                map.put("description", product_desc);
                map.put("image_base64",image64);


                return map;
            }



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                return map;
            }



        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    //////////////////////////////////////////////////
    @Override
    public void onPickResult(PickResult r) {

    }





    private class LongOperation extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            Allcategories();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);




        }
    }

}
