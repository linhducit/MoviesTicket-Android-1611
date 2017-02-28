package com.pt.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pt.movieticket.base.model.BaseModel;

/**
 * Created by TitTit on 06/12/2016.
 */

public class Booking extends BaseModel implements Parcelable {
    private String film;
    private String cinemas;
    private String address;
    private String type;
    private String date;
    private String time;
    private String row;
    private String pay;
    private int seats1;
    private int seats2;
    private int seats3;
    private int seats4;
    private int seats5;
    private int seats6;
    private int seats7;

    public Booking(String row, int seats1, int seats2, int seats3, int seats4, int seats5, int seats6, int seats7) {
        this.row = row;
        this.setSeats1(seats1);
        this.setSeats2(seats2);
        this.setSeats3(seats3);
        this.setSeats4(seats4);
        this.setSeats5(seats5);
        this.setSeats6(seats6);
        this.setSeats7(seats7);

    }


    public Booking(String film, String cinemas, String address, String type, String date, String time) {
        this.film = film;
        this.cinemas = cinemas;
        this.address = address;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public Booking(String pay) {
        this.setPay(pay);
    }

    protected Booking(Parcel in) {
        setFilm(in.readString());
        setCinemas(in.readString());
        setAddress(in.readString());
        setType(in.readString());
        setDate(in.readString());
        setTime(in.readString());
        setRow(in.readString());
        setSeats1(in.readInt());
        setSeats2(in.readInt());
        setSeats3(in.readInt());
        setSeats4(in.readInt());
        setSeats5(in.readInt());
        setSeats6(in.readInt());
        setSeats7(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getFilm());
        dest.writeString(getCinemas());
        dest.writeString(getAddress());
        dest.writeString(getType());
        dest.writeString(getDate());
        dest.writeString(getTime());
        dest.writeString(getRow());
        dest.writeInt(getSeats1());
        dest.writeInt(getSeats2());
        dest.writeInt(getSeats3());
        dest.writeInt(getSeats4());
        dest.writeInt(getSeats5());
        dest.writeInt(getSeats6());
        dest.writeInt(getSeats7());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getCinemas() {
        return cinemas;
    }

    public void setCinemas(String cinemas) {
        this.cinemas = cinemas;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }



    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public int getSeats1() {
        return seats1;
    }

    public void setSeats1(int seats1) {
        this.seats1 = seats1;
    }

    public int getSeats2() {
        return seats2;
    }

    public void setSeats2(int seats2) {
        this.seats2 = seats2;
    }

    public int getSeats3() {
        return seats3;
    }

    public void setSeats3(int seats3) {
        this.seats3 = seats3;
    }

    public int getSeats4() {
        return seats4;
    }

    public void setSeats4(int seats4) {
        this.seats4 = seats4;
    }

    public int getSeats5() {
        return seats5;
    }

    public void setSeats5(int seats5) {
        this.seats5 = seats5;
    }

    public int getSeats6() {
        return seats6;
    }

    public void setSeats6(int seats6) {
        this.seats6 = seats6;
    }

    public int getSeats7() {
        return seats7;
    }

    public void setSeats7(int seats7) {
        this.seats7 = seats7;
    }
}
