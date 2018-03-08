package com.edvidarez.scrollabletabs.beans;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edvidarez on 2/26/18.
 */

public class ItemProduct implements Parcelable {
    private String title;
    private String store;
    private String number;
    private Integer image;
    private String location;
    private Integer code;

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
    public ItemProduct(String title, String store, String number, Integer image, String location, Integer code) {
        this.title = title;
        this.store = store;
        this.number = number;
        this.image = image;
        this.location = location;
        this.code = code;
    }

    public ItemProduct(Parcel in){
        title = in.readString();
        store = in.readString();
        number = in.readString();
        image = in.readInt();
        location = in.readString();
        code = in.readInt();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(store);
        dest.writeString(number);
        dest.writeInt(image);
        dest.writeString(location);
        dest.writeInt(code);
    }
    public static final Parcelable.Creator<ItemProduct> CREATOR = new Parcelable.Creator<ItemProduct>(){
        @Override
        public ItemProduct createFromParcel(Parcel source){
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size){
            return new ItemProduct[size];
        }
    };

}

