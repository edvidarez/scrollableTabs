package com.edvidarez.scrollabletabs;

/**
 * Created by edvidarez on 3/1/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edvidarez.scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;


public class fragment_tecnology extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<ItemProduct> products = new ArrayList<>();
    AdapterProduct adapterProduct;

    public fragment_tecnology() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static fragment_tecnology newInstance(int sectionNumber) {
        fragment_tecnology fragment = new fragment_tecnology();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_recycler_view);
        products.add(new ItemProduct("mac","Best Buy" , "1234567890", 2 , "Zapopan",0));
        products.add(new ItemProduct("Alienware","Best Buy" , "1234567890", 1 , "Guadalajara",1));
        products.add(new ItemProduct("mac","Best Buy" , "1234567890", 2 , "Zapopan",2));
        adapterProduct = new AdapterProduct(products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterProduct);
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    public void onChange(ItemProduct product){
        Integer code = product.getCode();
        Log.d(ActivityMain.class.getSimpleName(),"Looog");
        products.set(code,product);
        adapterProduct.notifyDataSetChanged();
    }

}