package com.example.discountsplace.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.discountsplace.R;
import com.example.discountsplace.app.AppController;
import com.example.discountsplace.models.Category;
import com.example.discountsplace.utilities;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class categoriAdapter extends RecyclerView.Adapter<categoriAdapter.catsviewHolder> implements IPickResult {
    ArrayList<Category> data;
    Activity activity;
    Button save;
    Button cancle;
    Dialog MyDialog;
    ImageView cat_image;
    EditText cat_name_tv;
    Bitmap bitmap;
    String image64;
    String cat_name;

    public categoriAdapter(ArrayList<Category> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public categoriAdapter.catsviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_cat,parent,false);
        return new categoriAdapter.catsviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoriAdapter.catsviewHolder holder, int position) {
        Category category = data.get(position);
        final int id = category.getId();
        final String name = category.getName();
        final String url = category.getImage_url();

        Picasso.get().load(category.getImage_url()).into(holder.imageView);
        holder.cat_name.setText(category.getName());

        holder.editcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomAlertDialog(id,name,url);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class catsviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView cat_name;


        Button editcat;

        public catsviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.market_admin_item);
            cat_name= itemView.findViewById(R.id.product_name);

            editcat= itemView.findViewById(R.id.edit);

        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    public void updateCategory(final int id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, utilities.ip+"update_cat",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("responce",response);
                        Log.e("data", String.valueOf(data));
                        activity.finish();
                        activity.startActivity(activity.getIntent());
                            //notifyDataSetChanged();



                        try {
                            JSONObject jsonObject = new JSONObject(response);

                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("name");
                                String image = jsonObject.getString("image");
                                data.get(id).setName(name);
                                data.get(id).setImage_url(image);
                                Toast.makeText(activity, data.get(id).getName(), Toast.LENGTH_SHORT).show();






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



//
//
//
//
//
//


                        MyDialog.hide();
                        // imageView2.setImageBitmap(utilities.base64toImage(response));



                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id",String.valueOf(id));
                map.put("name", cat_name);
                map.put("image_base64",image64);


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




    //////////////////////////////////////////////////////////////////////////////////////////
    public void MyCustomAlertDialog(final int id,String name,String url){
        Log.e("dialougId",String.valueOf(id));
        MyDialog = new Dialog(activity);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.dialoge_custome);
        MyDialog.setTitle("Update  Category");
        MyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cat_image = MyDialog.findViewById(R.id.custome_img);
        cat_name_tv = MyDialog.findViewById(R.id.cat_name);
        Picasso.get().load(url).into(cat_image);
        cat_name_tv.setText(name);

        save = (Button)MyDialog.findViewById(R.id.save);
        cancle = (Button)MyDialog.findViewById(R.id.cancle);
        cat_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                if (r.getError() == null) {

                                    cat_image.setImageBitmap(r.getBitmap());

                                }else{
                                    Toast.makeText(activity, r.getError().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }).show((FragmentActivity) activity);

            }
        });

        save.setEnabled(true);
        cancle.setEnabled(true);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitmap = ((BitmapDrawable) cat_image.getDrawable()).getBitmap();
                image64 = utilities.imagetobase64(bitmap);
                cat_name = cat_name_tv.getText().toString();

                updateCategory(id);







            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }



    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show();
            cat_image.setImageBitmap(r.getBitmap());

        }else{
            Toast.makeText(activity, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

