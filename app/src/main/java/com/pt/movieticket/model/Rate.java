package com.pt.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pt.movieticket.base.model.BaseModel;

/**
 * Created by TitTit on 14/12/2016.
 */

public class Rate extends BaseModel implements Parcelable {
    private String content;
    private float rating;

    public Rate(String image, String name, String content, float rating) {
        this.setImage(image);
        this.setName(name);
        this.setContent(content);
        this.setRating(rating);
    }

    protected Rate(Parcel in) {
        image = in.readString();
        name = in.readString();
        content = in.readString();
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rate> CREATOR = new Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
