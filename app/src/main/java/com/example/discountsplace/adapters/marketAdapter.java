package com.example.discountsplace.adapters;

import android.app.Activity;
import android.content.Intent;
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
import com.example.discountsplace.models.Market;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class marketAdapter extends RecyclerView.Adapter<marketAdapter.marketviewHolder> {
    ArrayList<Market> data = new ArrayList<>();
    Activity activity;

    public marketAdapter(ArrayList<Market> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }
    @NonNull
    @Override
    public marketviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.market_item,parent,false);
        return new marketviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull marketviewHolder holder, int position) {
        final Market market = data.get(position);
        /// change this to picasso when you get the url
        Picasso.get().load(market.getImage_url()).into(holder.imageView);
        holder.name.setText(market.getName());
        holder.city.setText(market.getCity());
        holder.address.setText(market.getAddress());
        holder.phone.setText(market.getPhone());
        holder.cat.setText(market.getCagtegory());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(activity, market.getId()+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, MarketProducts.class);
                intent.putExtra("market_id",market.getId());
                intent.putExtra("flag","market");
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class marketviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView city;
        TextView address;
        TextView phone;
        TextView cat;
        LinearLayout linearLayout;
        public marketviewHolder(@NonNull View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.market_item_imageView);
             name = itemView.findViewById(R.id.market_item_name);
             city = itemView.findViewById(R.id.market_item_city);
             address = itemView.findViewById(R.id.market_item_address);
             phone = itemView.findViewById(R.id.market_item_phone);
             cat = itemView.findViewById(R.id.market_item_cat);
             linearLayout = itemView.findViewById(R.id.market_item_layout);
        }
    }
}
