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
import com.example.discountsplace.models.Product;
import com.example.discountsplace.productDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.marketviewHolder> {
    ArrayList<Product> data = new ArrayList<>();
    Activity activity;

    public productAdapter(ArrayList<Product> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public marketviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.productitem, parent, false);

        return new marketviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull marketviewHolder holder, int position) {


        final Product product = data.get(position);
        String image_url = product.getImage64();
        double ratio2 = 100-(((double)product.getNew_price()/(double) product.getOld_price())*100);
        float ratio = (float) ((double)Math.round(ratio2 * 10d) / 10d);
//        holder.product_image.setImageBitmap(utilities.base64toImage(product.getImage64()));
        Picasso.get().load(image_url).into(holder.product_image);

        holder.productdisscount.setText("-"+ratio+"%");
        holder.product_old_price.setText(product.getOld_price()+"");
        holder.product_new_price.setText(product.getNew_price()+"");
        holder.product_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, productDetails.class);
                intent.putExtra("id",product.getId());
                activity.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class marketviewHolder extends RecyclerView.ViewHolder{
        ImageView product_image;
        TextView productdisscount;
        TextView product_old_price;
        TextView product_new_price;
        LinearLayout product_layout;
        public marketviewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_item_image);
            productdisscount = itemView.findViewById(R.id.product_discount_ratio);
            product_old_price = itemView.findViewById(R.id.product_old_price);
            product_new_price = itemView.findViewById(R.id.product_new_price);
            product_layout = itemView.findViewById(R.id.product_item_layout);

        }
    }
}
