package com.edvidarez.scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edvidarez.scrollabletabs.beans.ItemProduct;
import com.edvidarez.scrollabletabs.beans.Store;
import com.edvidarez.scrollabletabs.beans.StoreProduct;

import java.util.ArrayList;

/**
 * Created by edvidarez on 3/18/18.
 */

public class StoreProductControll {
    public void addStoreProduct(StoreProduct storeProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", storeProduct.getId());
        values.put("idproduct", storeProduct.getIdproduct());
        values.put("idstore", storeProduct.getIdstore());
        db.insert("StoreProduct", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }



}
