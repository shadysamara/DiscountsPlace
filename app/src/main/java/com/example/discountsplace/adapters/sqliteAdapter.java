package com.example.discountsplace.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.discountsplace.models.cart;


import java.util.ArrayList;

public class sqliteAdapter extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public sqliteAdapter(@Nullable Context context) {
        super(context, "gazaDiscount_db", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(cart.create_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+cart.table_name);
        onCreate(sqLiteDatabase);
    }



    public boolean insertProductData(int product_id,String product_name,float product_price,String market,byte[] img){
        ContentValues contentValues = new ContentValues();
        contentValues.put(cart.col_id,product_id);
        contentValues.put(cart.col_product_name,product_name);
        contentValues.put(cart.col_product_price,product_price);
        contentValues.put(cart.col_market_name,market);
        contentValues.put(cart.col_image,img);
        return db.insert(cart.table_name,null,contentValues)>0;
    }






    public ArrayList<cart> getallProductsdata(){
        ArrayList<cart> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+cart.table_name,null);
        while (cursor.moveToNext()){
            int product_id = cursor.getInt(cursor.getColumnIndex(cart.col_id));
            String name = cursor.getString(cursor.getColumnIndex(cart.col_product_name));
            float price = cursor.getFloat(cursor.getColumnIndex(cart.col_product_price));
            String market = cursor.getString(cursor.getColumnIndex(cart.col_market_name));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(cart.col_image));

            cart cart = new cart(product_id,name,price,image,market);
            list.add(cart);
        }
        return list;
    }


    public boolean deleteproduct(int id){
        return db.delete(cart.table_name,"product_id = ?",new String[]{String.valueOf(id)})>0;

    }

}
