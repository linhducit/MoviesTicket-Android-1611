package com.pt.movieticket.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;

/**
 * Created by TitTit on 29/12/2016.
 */

public class AboutFragment extends BaseFragment {

    public static AboutFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_about;
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
