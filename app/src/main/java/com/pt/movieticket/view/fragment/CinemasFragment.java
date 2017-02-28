package com.pt.movieticket.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.AbstractActivity;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.VolleyRequestManager;
import com.pt.movieticket.view.adapter.CinemasAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TitTit on 29/11/2016.
 */

public class CinemasFragment extends BaseFragment {
    private RecyclerView rcvCinemas;
    private CinemasAdapter mCinemasAdapter;
    private List<Cinemas> mDatas = new ArrayList<>();

    public static CinemasFragment newInstance() {

        Bundle args = new Bundle();

        CinemasFragment fragment = new CinemasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_cinemas;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        rcvCinemas = (RecyclerView) view.findViewById(R.id.rcv_cinemas);
        rcvCinemas.setHasFixedSize(true);
        rcvCinemas.setLayoutManager(new GridLayoutManager(self, 2));
    }

    @Override
    protected void getData() {
        mCinemasAdapter = new CinemasAdapter(self, mDatas);
        rcvCinemas.setAdapter(mCinemasAdapter);
        if (mDatas.size() > 0) {
            mCinemasAdapter.notifyDataSetChanged();
            return;
        }
        RequestManager.getListCinemas(self, new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                if (!response.isError()) {
                    mDatas.addAll(response.getDataList(Cinemas.class));
                    mCinemasAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onError(String message) {

            }
        });

    }


}
