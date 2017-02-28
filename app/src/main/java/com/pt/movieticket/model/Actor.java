package com.pt.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TitTit on 24/11/2016.
 */

public class Actor implements Parcelable {
    private String image;
    private String name;


    public Actor(String image, String name) {
        this.setImage(image);
        this.setName(name);
    }

    protected Actor(Parcel in) {
        setImage(in.readString());
        setName(in.readString());
    }

    public static Creator<Actor> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getImage());
        dest.writeString(getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

