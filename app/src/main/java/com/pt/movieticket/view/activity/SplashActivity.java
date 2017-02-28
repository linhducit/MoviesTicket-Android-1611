package com.pt.movieticket.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pt.movieticket.R;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.util.AppUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (DataStoreManager.getToken().equals("")) {
                    AppUtil.startActivityLTR(SplashActivity.this, LoginActivity.class);
                } else {
                    AppUtil.startActivity(SplashActivity.this, MainActivity.class);
                }
                finish();
            }
        }, 2000);
    }
}
