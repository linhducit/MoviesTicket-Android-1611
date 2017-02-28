package com.pt.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TitTit on 28/11/2016.
 */

public class Promotion implements Parcelable {
    private int id;
    private String cinemas;
    @SerializedName("list")
    private List<Content> mDatas;


    protected Promotion(Parcel in) {
        id = in.readInt();
        cinemas = in.readString();
        mDatas = in.createTypedArrayList(Content.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cinemas);
        dest.writeTypedList(mDatas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Promotion> CREATOR = new Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };

    public String getCinemas() {
        return cinemas;
    }

    public void setCinemas(String cinemas) {
        this.cinemas = cinemas;
    }

    public List<Content> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Content> mDatas) {
        this.mDatas = mDatas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
