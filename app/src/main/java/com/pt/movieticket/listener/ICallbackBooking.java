package com.pt.movieticket.listener;

/**
 * Created by TitTit on 17/01/2017.
 */

public interface ICallbackBooking {
    void next(int key);

    void back(int key);

    void finishActivity();
}
