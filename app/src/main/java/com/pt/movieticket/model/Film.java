package com.pt.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TitTit on 24/11/2016.
 */
public class Film implements Parcelable {
    private String date;
    private String film;
    private String image;
    private String content;
    private String link;
    private int rate;
    @SerializedName("actor")
    private List<Actor> arrActor;
    private List<Rate> arrRate;


    protected Film(Parcel in) {
        date = in.readString();
        film = in.readString();
        image = in.readString();
        content = in.readString();
        link = in.readString();
        rate = in.readInt();
        arrActor = in.createTypedArrayList(Actor.CREATOR);
        arrRate = in.createTypedArrayList(Rate.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(film);
        dest.writeString(image);
        dest.writeString(content);
        dest.writeString(link);
        dest.writeInt(rate);
        dest.writeTypedList(arrActor);
        dest.writeTypedList(arrRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Actor> getArrActor() {
        return arrActor;
    }

    public void setArrActor(List<Actor> arrActor) {
        this.arrActor = arrActor;
    }

    public List<Rate> getArrRate() {
        return arrRate;
    }

    public void setArrRate(List<Rate> arrRate) {
        this.arrRate = arrRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
