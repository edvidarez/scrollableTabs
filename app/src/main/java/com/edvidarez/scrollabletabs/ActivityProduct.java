package com.edvidarez.scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.edvidarez.scrollabletabs.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    ImageView image;
    TextInputEditText title, store,location,phone;
    Button save, cancel;
    ItemProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_product);

        image = findViewById(R.id.image_edit);
        title = findViewById(R.id.title_edit);
        store = findViewById(R.id.store_edit);
        location = findViewById(R.id.location_edit);
        phone = findViewById(R.id.phone_edit);

        save = findViewById(R.id.save_edit);
        cancel = findViewById(R.id.cancel_edit);

        if(getIntent().getExtras() != null) {
             product = getIntent().getParcelableExtra("Producto");
            if(product != null) {
                switch (product.getImage()) {

                    case 1:
                        image.setImageResource(R.drawable.alienware);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.mac);
                        break;
                    default:
                        image.setImageResource(R.drawable.bestbuy);
                        break;
                }
                title.setText(product.getTitle());
                store.setText(product.getStore());
                location.setText(product.getLocation());
                phone.setText(product.getNumber());
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setTitle(title.getText().toString());
                product.setStore(store.getText().toString());
                product.setLocation(location.getText().toString());
                product.setNumber(phone.getText().toString());


                Log.d(ActivityMain.class.getSimpleName(),"Looog");

                Intent intent = new Intent();
                intent.putExtra("Producto",product);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
