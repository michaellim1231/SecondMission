package com.example.mission2.model;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    public String name;
    public String age;
    public String address;


    public User(String name, String address, String age) {
        this.name = name;
        this.age = age;
        this.address = address;


    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readString();
        address = in.readString();

    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel rcel, int flags) {
        rcel.writeString(name);
        rcel.writeString(age);
        rcel.writeString(address);

    }


}