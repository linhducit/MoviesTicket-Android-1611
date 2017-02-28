package com.pt.movieticket.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;

/**
 * Created by TitTit on 28/11/2016.
 */

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }
}
