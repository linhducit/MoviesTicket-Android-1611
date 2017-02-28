package com.pt.movieticket.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.view.adapter.FilmAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Trang on 6/29/2016.
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView rcvProduct;
    private FilmAdapter mFilmAdapter;
    private List<Film> arrFilm = new ArrayList<>();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        rcvProduct = (RecyclerView) view.findViewById(R.id.rcv_product);
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void getData() {
        mFilmAdapter = new FilmAdapter(self, arrFilm, "");
        rcvProduct.setAdapter(mFilmAdapter);
        if (arrFilm.size() > 0) {
            mFilmAdapter.notifyDataSetChanged();
            return;
        }

        RequestManager.getListFilm(self, new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                if (!response.isError()) {
                    arrFilm.addAll(response.getDataList(Film.class));
                    mFilmAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
