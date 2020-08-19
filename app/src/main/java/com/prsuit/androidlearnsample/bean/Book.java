package com.prsuit.androidlearnsample.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/19
 */
public class Book implements Parcelable {
    private String name;
    private int price;

    public Book(){

    }

    public Book(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    // 该方法不是Parcelable自动生成的，需要自己手动添加，
    // 如果不添加，则在使用AIDL时只支持 in 的定向tag
    // 如果添加了，则支持 in、out、inout
    public void readFromParcel(Parcel dest){
        name = dest.readString();
        price = dest.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
