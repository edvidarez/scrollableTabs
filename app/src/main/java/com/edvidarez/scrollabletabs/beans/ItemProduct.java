package com.edvidarez.scrollabletabs.beans;

import android.content.Intent;

/**
 * Created by edvidarez on 2/26/18.
 */

public class ItemProduct {
    private String title;
    private String store;
    private String number;
    private Integer image;
    private String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


    public ItemProduct(String title, String store, String number, Integer image, String location) {
        this.title = title;
        this.store = store;
        this.number = number;
        this.image = image;
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", number='" + number + '\'' +
                ", image=" + image +
                ", location='" + location + '\'' +
                '}';
    }
}
