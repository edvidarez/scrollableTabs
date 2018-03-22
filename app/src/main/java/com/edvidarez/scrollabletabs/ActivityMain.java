package com.edvidarez.scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.edvidarez.scrollabletabs.beans.Category;
import com.edvidarez.scrollabletabs.beans.City;
import com.edvidarez.scrollabletabs.beans.ItemProduct;
import com.edvidarez.scrollabletabs.beans.Store;
import com.edvidarez.scrollabletabs.database.CategoryControll;
import com.edvidarez.scrollabletabs.database.CityController;
import com.edvidarez.scrollabletabs.database.DataBaseHandler;
import com.edvidarez.scrollabletabs.database.ItemProductControll;
import com.edvidarez.scrollabletabs.database.StoreControll;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private fragment_tecnology fragmentTecnology;
    private fragment_electronics fragmentElectronics;
    private fragment_home fragmentHome;
    CityController cityController;
    StoreControll storeControll;
    CategoryControll categoryControll;
    ItemProductControll productControll;
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = DataBaseHandler.getInstance(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMain.this,ActivityItem.class);
                startActivityForResult(intent,12);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(ActivityMain.class.getSimpleName(),"result");
        switch (requestCode){
            case 9999:
                if(resultCode == Activity.RESULT_OK){
                    ItemProduct product = data.getParcelableExtra("Producto");
                    if(product != null){
                        Log.d(ActivityMain.class.getSimpleName(),product.getTitle());
                        fragmentTecnology.onChange(product);
                    }
                }
                break;
            case 12:
                if(resultCode == Activity.RESULT_OK){
                    ArrayList<ItemProduct> products ;
                    ItemProductControll itemProductControll = new ItemProductControll();
                    switch (data.getIntExtra("category",-1)){

                        case 0:
                            if(fragmentTecnology!= null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentTecnology.onChange(product);
                            }
                            break;
                        case 1:
                            if(fragmentHome != null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentHome.onChange(product);
                            }
                            break;
                        case 2:
                            if(fragmentElectronics != null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentElectronics.onChange(product);
                            }
                            break;
                    }
                }
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {

            Intent intent = new Intent(ActivityMain.this,ActivityLogin.class);
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("LOGGED",false);
            editor.apply();
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.privacy_policy){
            Intent intent = new Intent(ActivityMain.this,ActivityPrivacyPolicy.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:return (fragmentTecnology==null)?fragmentTecnology = new fragment_tecnology():fragmentTecnology;
                case 1: return (fragmentHome==null)?fragmentHome = new fragment_home():fragmentHome;
                case 2: return (fragmentElectronics==null)?fragmentElectronics = new fragment_electronics():fragmentElectronics;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0: return "Technology";
                case 1: return "Home";
                case 2: return "Electronics";
            }
            return null;
        }
    }
}
