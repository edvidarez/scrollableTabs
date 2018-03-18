package com.edvidarez.scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edvidarez.scrollabletabs.beans.City;

import java.util.ArrayList;

/**
 * Created by edvidarez on 3/17/18.
 */

public class CityController {
    public void addCity(City city, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", city.getId());
        values.put("name", city.getName());
        db.insert("City", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }

    public void deleteCity(int idCity, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete("City", "id = ?",
                new String[]{String.valueOf(idCity)});
        try{
            db.close();
        }catch(Exception e){
        }
        db = null;
    }

    public ArrayList<City> getCities(DataBaseHandler dh){
        ArrayList<City> cities = new ArrayList<>();
        String select = "SELECT id, name FROM City";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(0));
            city.setName(cursor.getString(1));

            cities.add(city);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return cities;
    }
}
