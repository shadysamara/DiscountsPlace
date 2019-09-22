package com.example.discountsplace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.discountsplace.adapters.marketAdapter;
import com.example.discountsplace.adapters.sqliteAdapter;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.models.Comment;
import com.example.discountsplace.models.Market;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.discountsplace.adapters.commentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class productDetails extends AppCompatActivity {
    ArrayList<Comment> data;
    int id;
    int product_id;
    String name;
    String description;
    String market_name;
    String catagory;
    String old_price;
    String new_price;
    String image_url;
    String rating;
    ImageView imageView;
    byte[] image;
    sqliteAdapter sqliteAdapter;
commentAdapter commentAdapter;
    TextView txtoldprice;
    TextView txtratio;
    TextView txtnewprice;
    TextView txtdescription;
    EditText commenttxt;
    Button add_comment;
    Button cart_btn;
    String comment;
    RecyclerView rvData;
    SharedPreferences prefs;
    int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
         imageView = findViewById(R.id.img_detail);
         id = getIntent().getIntExtra("id",0);
         Log.e("product_id",id+"");
         sqliteAdapter = new sqliteAdapter(productDetails.this);
        prefs = getSharedPreferences("customer",MODE_PRIVATE);

        getJSONString();
         rvData = findViewById(R.id.commentsrvdata);

        RecyclerView.LayoutManager manager =  new LinearLayoutManager(productDetails.this);
        rvData.setLayoutManager(manager);

         txtoldprice = findViewById(R.id.product_old_price);
         txtratio = findViewById(R.id.product_ratio);
         txtnewprice = findViewById(R.id.product_new_price);
         txtdescription = findViewById(R.id.product_discription);
        commenttxt = findViewById(R.id.txt_comment);
        add_comment = findViewById(R.id.add_comment);
        cart_btn = findViewById(R.id.add_to_cart);
        getJSONString();
        getComments();
        add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 user_id = prefs.getInt("customer_id",0);
                if(user_id==0){







                }
                else{
                    comment = commenttxt.getText().toString();
                    addComment();}

            }
        });
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = utilities.imageViewToByte(imageView);


                sqliteAdapter.insertProductData(product_id,name,Float.parseFloat(new_price),market_name,image);
            }
        });

    }
    public void getJSONString(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"product_details",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("product_details",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            product_id = jsonObject.getInt("id");
                             name = jsonObject.getString("name");
                             description= jsonObject.getString("description");
                             market_name= jsonObject.getString("market_name");
                             catagory= jsonObject.getString("catagory");
                             old_price= jsonObject.getString("old_price");
                             new_price= jsonObject.getString("new_price");
                             image_url= jsonObject.getString("image");

                            rating= jsonObject.getString("rating");
                             double ratio = (Double.parseDouble(new_price)/Double.parseDouble(old_price))*100;

                             txtoldprice.setText(old_price);
                             txtratio.setText(ratio+"");
                             txtnewprice.setText(new_price);
                             txtdescription.setText(description);
                            Picasso.get().load(image_url).into(imageView);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // imageView2.setImageBitmap(utilities.base64toImage(response));



                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(productDetails.this, error.toString(), Toast.LENGTH_SHORT).show();


            }
        }) {

            //
//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));



                return map;
            }



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                return map;
            }



        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    public void addComment(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"add_comment",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        // imageView2.setImageBitmap(utilities.base64toImage(response));



                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(productDetails.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            //
//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
//                'content'=>$input['content'],
//                        'product_id'=>$input['product_id'],
//                        'rating'=>$input['rating']
                map.put("user_id", String.valueOf(user_id));
                map.put("content", comment);
                map.put("product_id", String.valueOf(id));
                map.put("rating", "2.5");



                return map;
            }



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                return map;
            }



        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    public void getComments(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"Approved_comments",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;
                            data = new ArrayList<>();
                            for(int i = 0 ; i < jsonArray.length() ; i++){
                                jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String content = jsonObject.getString("content");
                                String username = jsonObject.getString("username");
                                String rating = jsonObject.getString("rating");
                                int product_id = jsonObject.getInt("product_id");
                                String product_name = jsonObject.getString("product_name");
                                data.add(new Comment(id,content,username,rating,product_id,product_name));

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        commentAdapter = new commentAdapter(productDetails.this,data);
                        rvData.setAdapter(commentAdapter);

                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            //
//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));



                return map;
            }



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                return map;
            }



        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }








}
