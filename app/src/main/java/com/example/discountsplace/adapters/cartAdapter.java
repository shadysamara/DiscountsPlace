package com.example.discountsplace.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discountsplace.R;
import com.example.discountsplace.models.cart;
import com.example.discountsplace.utilities;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartviewHolder> {
Activity activity;
ArrayList<cart> data = new ArrayList<>();
float totalprice;

    public cartAdapter(Activity activity, ArrayList<cart> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public cartAdapter.cartviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.item_cart, parent, false);

        return new cartAdapter.cartviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.cartviewHolder holder, int position) {
        final cartAdapter.cartviewHolder gholder= holder;
        cart cart = data.get(position);
        int productId = cart.getProduct_id();
        String productName = cart.getProduct_name();
        String MarketName = cart.getMrket_name();
        final float productPrice = cart.getProduct_price();
        totalprice+=productPrice;
        byte[] image = cart.getImage();

        holder.productNametv.setText(productName);
        holder.marketNametv.setText(MarketName);
        holder.productPricetv.setText(productPrice+"");
        holder.imageView.setImageBitmap(utilities.byteToImageview(image));
        final String x = holder.counttxt.getText().toString();
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity, "1", Toast.LENGTH_SHORT).show();
              int i = Integer.parseInt(gholder.counttxt.getText().toString());
              totalprice+=(productPrice);
              i++;

              gholder.counttxt.setText(String.valueOf(i));
                Toast.makeText(activity, totalprice+"", Toast.LENGTH_SHORT).show();

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(gholder.counttxt.getText().toString());
                if(i>1){ i--;
                    totalprice-=(productPrice);
                    Toast.makeText(activity, totalprice+"", Toast.LENGTH_SHORT).show();}

                gholder.counttxt.setText(String.valueOf(i));

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class cartviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productNametv;
        TextView marketNametv;
        TextView productPricetv;
        Button plus;
        Button minus;
        EditText counttxt;

        public cartviewHolder(@NonNull View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.cart_image_item);
             productNametv = itemView.findViewById(R.id.cart_product_name);
             marketNametv = itemView.findViewById(R.id.cart_market_name);
             productPricetv = itemView.findViewById(R.id.cart_product_price);
             plus = itemView.findViewById(R.id.cartIncrease);
             minus = itemView.findViewById(R.id.cartReduce);
             counttxt = itemView.findViewById(R.id.counttxt);
        }
    }
}
