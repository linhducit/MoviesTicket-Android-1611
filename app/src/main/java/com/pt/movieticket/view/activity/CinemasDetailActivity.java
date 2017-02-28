package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.model.Actor;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.view.adapter.FilmAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TitTit on 28/12/2016.
 */
public class CinemasDetailActivity extends BaseActivity {

    private RecyclerView rcvProduct;
    private FilmAdapter mFilmAdapter;
    private List<Film> arrFilm;
    private String cinemas;


    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NAVI;
    }

    @Override
    protected void getExtraData(Intent intent) {
        cinemas = AppController.getInstance().getCinemas();
        arrFilm = new ArrayList<>();
        mFilmAdapter = new FilmAdapter(self, arrFilm, cinemas);

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

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void initView() {
        rcvProduct = (RecyclerView) findViewById(R.id.rcv_product);
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onViewCreated() {
        setToolbarTitle(cinemas);
        rcvProduct.setAdapter(mFilmAdapter);
    }
}
