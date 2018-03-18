package com.edvidarez.scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edvidarez.scrollabletabs.beans.Category;

import java.util.ArrayList;

/**
 * Created by edvidarez on 3/17/18.
 */

public class CategoryControll {
    public void addCategory(Category category, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        db.insert("Category", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }
    public ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        String select = "SELECT id, name FROM Category";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));

            categories.add(category);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return categories;
    }
    public Category getCategoryByID(int id,DataBaseHandler dh){
        Category category = new Category();
        String select = "SELECT id, name FROM Category where id = "+id;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){

            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));


        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return category;
    }
}
