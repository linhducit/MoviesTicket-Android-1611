package com.pt.movieticket.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by TitTit on 04/01/2017.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Tit", "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String token) {

    }
}
