package com.pt.movieticket.base.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.NetworkUtility;
import com.pt.movieticket.widgets.textview.TextViewUTMAircona;

/**
 * Created by Trang on 7/10/2016.
 */
public abstract class AbstractActivity extends AppCompatActivity {

    public enum ToolbarType {
        MENU_LEFT, // get menu left
        NAVI,    // get button back
        NONE   // none
    }

    protected Context self;

    protected FrameLayout contentLayout;
    // toolbar
    protected Toolbar toolbar;
    protected TextView tvTitle,tvNetwork;

    protected ProgressBar progressBar;

    protected abstract ToolbarType getToolbarType();

    protected abstract void getExtraData(Intent intent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        getExtraData(getIntent());
        setView();
        initViewBase();
        regisNetworkListener();
    }

    private void setView() {
        if (getToolbarType() == ToolbarType.MENU_LEFT) {
            setContentView(R.layout._base_drawer);
            initToolbar();
        } else if (getToolbarType() == ToolbarType.NAVI) {
            setContentView(R.layout._base_frame);
            initToolbar();
        } else if (getToolbarType() == ToolbarType.NONE) {
            setContentView(R.layout.base_content);
        }
    }

    private void initViewBase() {
        contentLayout = (FrameLayout) findViewById(R.id.content_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        tvNetwork = (TextView) findViewById(R.id.tv_network);
        //BaseRequest.getInstance().setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //BaseRequest.getInstance().setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastNetwork);

    }

    public void showProgress(boolean isShow) {
        if (isShow) {
            if (!progressBar.isShown())
                progressBar.setVisibility(View.VISIBLE);
        } else {
            if (progressBar.isShown())
                progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * initialize toolbar
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextViewUTMAircona) toolbar.findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getToolbarType() == ToolbarType.NAVI) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * set title for toolbar
     */
    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    public void setToolbarTitle(int title) {
        tvTitle.setText(getString(title));
    }

    public void setToolbarTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    public void setToolbarSize(float size) {
        tvTitle.setTextSize(size);
    }

    /**
     * start activity
     *
     * @param clz class name
     */
    public void startActivity(Class<?> clz) {
        AppUtil.startActivity(self, clz);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        AppUtil.startActivity(self, clz, bundle);
    }

    /**
     * show toast message
     *
     * @param message
     */
    public void showToast(String message) {
        AppUtil.showToast(self, message);
    }

    public void showToast(int message) {
        AppUtil.showToast(self, getString(message));
    }

    /**
     * show snack bar message
     *
     * @param message
     */
    public void showSnackBar(int message) {
        Snackbar.make(contentLayout, getString(message), Snackbar.LENGTH_LONG).show();
    }

    public void showSnackBar(String message) {
        Snackbar.make(contentLayout, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Listener network state
     */
    private BroadcastReceiver broadCastNetwork = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtility.isNetworkAvailable()) {
            } else {
                tvNetwork.setSelected(false);
                tvNetwork.setVisibility(View.GONE);
                tvNetwork.setSelected(true);
                tvNetwork.setVisibility(View.VISIBLE);
            }
        }
    };

    private void regisNetworkListener() {
        registerReceiver(broadCastNetwork, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

}
