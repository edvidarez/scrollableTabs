package com.edvidarez.scrollabletabs;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.edvidarez.scrollabletabs.beans.Category;
import com.edvidarez.scrollabletabs.beans.ItemProduct;
import com.edvidarez.scrollabletabs.beans.Store;
import com.edvidarez.scrollabletabs.database.CategoryControll;
import com.edvidarez.scrollabletabs.database.DataBaseHandler;
import com.edvidarez.scrollabletabs.database.ItemProductControll;
import com.edvidarez.scrollabletabs.database.StoreControll;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    EditText title;
    Spinner categories,stores, images;
    ArrayList<Category> categoriesArray;
    ArrayList<Store> storesArray;
    ArrayList<String> imagesArray;
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        title = findViewById(R.id.ActivityItemTitle);
        categories = findViewById(R.id.ActivityItemCategory);
        stores = findViewById(R.id.ActivityItemStore);
        images = findViewById(R.id.ActivityItemImage);


        CategoryControll categoryControll = new CategoryControll();
        StoreControll storeControll = new StoreControll();

        dh = DataBaseHandler.getInstance(ActivityItem.this);

        categoriesArray = categoryControll.getCategories(dh);
        storesArray = storeControll.getStores(dh);
        imagesArray = new ArrayList<String>();
        imagesArray.add("Mac");
        imagesArray.add("AlienWare");

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_list_item_1, categoriesArray);
        ArrayAdapter<Store>  storeAdapter = new ArrayAdapter<Store>(getApplicationContext(),android.R.layout.simple_list_item_1,storesArray);
        ArrayAdapter<String> imagesAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,imagesArray);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        categories.setAdapter(categoryAdapter);
        stores.setAdapter(storeAdapter);
        images.setAdapter(imagesAdapter);
        stores.setBackgroundColor(Color.GRAY);
        categories.setBackgroundColor(Color.GRAY);
        images.setBackgroundColor(Color.GRAY);

    }
    public void Save(View view){
        Category category = (Category) categories.getSelectedItem();
        Store store = (Store) stores.getSelectedItem();
        ItemProductControll itemProductControll = new ItemProductControll();
        ArrayList<ItemProduct> products = itemProductControll.getProducts(dh);
        Log.d("debugg","El size es "+products.size());
        int imageIndex = images.getSelectedItemPosition();
        ItemProduct product = new ItemProduct(products.size()+1,title.getText().toString(),"",imageIndex,store,category);
        Log.d("debugg",product.toString());
        itemProductControll.addProduct(product,dh);

        finish();
    }
}
