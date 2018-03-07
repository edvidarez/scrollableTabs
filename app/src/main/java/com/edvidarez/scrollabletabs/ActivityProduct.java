package com.edvidarez.scrollabletabs;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityProduct extends AppCompatActivity {

    ImageView image;
    TextInputEditText title, store,location,phone;
    Button save, cancel;


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




    }
}
