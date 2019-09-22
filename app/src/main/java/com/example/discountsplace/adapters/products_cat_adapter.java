package com.example.discountsplace.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.discountsplace.R;
import com.example.discountsplace.main_activities.MarketProducts;
import com.example.discountsplace.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class products_cat_adapter extends RecyclerView.Adapter<products_cat_adapter.catsviewHolder> {
    Activity activity;
    ArrayList<Category> data;

    public products_cat_adapter(Activity activity, ArrayList<Category> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public products_cat_adapter.catsviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_cats_products,parent,false);
        return new products_cat_adapter.catsviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull products_cat_adapter.catsviewHolder holder, int position) {
        final Category category = data.get(position);
        Picasso.get().load(category.getImage_url()).into(holder.imageView);
        holder.textView.setText(category.getName());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.textView.setBackgroundColor(color);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = category.getName();
                Log.e("cat_name",name);
                Log.e("cat_id",String.valueOf(category.getId()));
                Intent intent = new Intent(activity, MarketProducts.class);
                intent.putExtra("cat_id",category.getId());
                intent.putExtra("flag","cat");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class catsviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
        public catsviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.card_view_image);
            textView = itemView.findViewById(R.id.card_view_image_title);
            linearLayout = itemView.findViewById(R.id.catLl);
        }
    }
}
