package com.pt.movieticket.base.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Trang on 6/17/2016.
 */
public abstract class BaseActivity extends AbstractActivity {

    protected abstract ToolbarType getToolbarType();

    protected abstract int getLayoutInflate();

    protected abstract void getExtraData(Intent intent);

    protected abstract void initialize();

    protected abstract void initView();

    protected abstract void onViewCreated();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(getLayoutInflate(), contentLayout);
        initialize();
        initView();
        onViewCreated();
    }
}
