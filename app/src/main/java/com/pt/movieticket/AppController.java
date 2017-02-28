package com.pt.movieticket;

import android.app.Application;

import com.pt.movieticket.datastore.BaseDataStore;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.model.Booking;

import java.util.List;


/**
 * Created by Trang on 1/8/2016.
 */
public class AppController extends Application {

    // TODO: 7/11/2016 Need comment
    private static AppController instance;
    private String token;
    private String cinemas;
    private int total;
    private List<Booking> mData;

    private int global;

    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BaseDataStore.init(getApplicationContext());
       /* VolleyRequestManager.init(getApplicationContext());
        BaseRequest.init(this);*/

    }

    public String getToken() {
        if (token == null)
            token = DataStoreManager.getToken();
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public String getCinemas() {
        return cinemas;
    }

    public void setCinemas(String cinemas) {
        this.cinemas = cinemas;
    }

    public List<Booking> getmData() {
        return mData;
    }

    public void setmData(List<Booking> mData) {
        this.mData = mData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
