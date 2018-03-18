package com.edvidarez.scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edvidarez.scrollabletabs.beans.City;
import com.edvidarez.scrollabletabs.beans.Store;
import com.edvidarez.scrollabletabs.database.CityController;
import com.edvidarez.scrollabletabs.database.DataBaseHandler;
import com.edvidarez.scrollabletabs.database.StoreControll;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /*
        Al	 momento	 de	 crear	 la	 actividad	 ActivitySplashScreen	 cargar	 las    preferencias	y	validar	la	existencia	de	un	usuario	en	memoria.
        iii. En	 caso	 de	 estar	 el	 atributo	 logged = true	 llamar	 a	 la	 actividad	 principal
        ActivityMain,	en	caso	contrario	a	ActivityLogin
        iv. Borrar	del	stack ActivitySplashScreen
        */

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                Intent mainIntent;
                if(user.isLogged()){
                    mainIntent = new Intent().setClass(ActivitySplashScreen.this,ActivityMain.class);
                }
                else{
                    mainIntent = new Intent().setClass(ActivitySplashScreen.this,ActivityLogin.class);
                }
                DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplashScreen.this);
                StoreControll storeControll = new StoreControll();
                CityController cityController = new CityController();
                ArrayList<Store> stores =  storeControll.getStores(dh);
                if(stores.size()==0) {
                    //add 3
                    City guadalajara = new City(1, "Guadalajara");
                    City zapopan = new City(2, "Zapopan");
                    cityController.addCity(guadalajara, dh);
                    cityController.addCity(zapopan, dh);

                    Store laWalmartGdl = new Store(1, "La Walmart GDL", "33 33 33 33 33", 1, 3.1416, 2.17, guadalajara);
                    Store laWalmartZpn = new Store(2, "La Walmart Zapopan", "33 33 33 33 33", 1, 3.1416, 2.17, zapopan);
                    Store ebay = new Store(3, "E Bay", "33 33 33 33 33", 2, 3.1416, 2.17, guadalajara);
                    storeControll.addStore(laWalmartGdl, dh);
                    storeControll.addStore(laWalmartZpn, dh);
                    storeControll.addStore(ebay, dh);

                }
                startActivity(mainIntent);

                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);

    }
    public User loadPreferences(){
        User user = new User();
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        user.setName(sharedPreferences.getString("USER",null));
        user.setPassword(sharedPreferences.getString("PWD",null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED",false));
        sharedPreferences = null;
        return user;
    }
}
