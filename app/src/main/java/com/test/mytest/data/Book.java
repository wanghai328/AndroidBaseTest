package com.test.mytest.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name;
    private String classify;
    private int id;

    public  Book(){

    }
    protected Book(Parcel in) {
        name = in.readString();
        classify = in.readString();
        id = in.readInt();
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
        dest.writeString(classify);
        dest.writeInt(id);
    }
}
