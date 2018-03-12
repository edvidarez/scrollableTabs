package com.edvidarez.scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
