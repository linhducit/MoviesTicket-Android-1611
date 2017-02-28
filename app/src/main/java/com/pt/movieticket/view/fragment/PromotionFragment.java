package com.pt.movieticket.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.model.Content;
import com.pt.movieticket.model.Promotion;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.view.adapter.PromotionAdapter;
import com.pt.movieticket.view.adapter.ArraySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TitTit on 28/11/2016.
 */

public class PromotionFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    private PromotionAdapter mPromotionAdapter;
    private RecyclerView rcvPromotion;
    private Spinner spCinemas;
    private ArraySpinnerAdapter mArraySpinnerAdapter;
    private List<Promotion> arrPromotion;
    private List<Content> arrContent;
    private List<Cinemas> arrCinemas;

    public static PromotionFragment newInstance() {

        Bundle args = new Bundle();

        PromotionFragment fragment = new PromotionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_promotion;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initView(View view) {
        spCinemas = (Spinner) view.findViewById(R.id.sp_cinemas);
        rcvPromotion = (RecyclerView) view.findViewById(R.id.rcv_promotion);
        rcvPromotion.setHasFixedSize(true);
        rcvPromotion.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));
        spCinemas.setOnItemSelectedListener(this);
    }

    @Override
    protected void getData() {
        arrPromotion = new ArrayList<>();
        arrContent = new ArrayList<>();
        arrCinemas = new ArrayList<>();

        mPromotionAdapter = new PromotionAdapter(self, arrContent);
        mArraySpinnerAdapter = new ArraySpinnerAdapter(self, arrCinemas);
        rcvPromotion.setAdapter(mPromotionAdapter);
        spCinemas.setAdapter(mArraySpinnerAdapter);

//        if (arrContent.size() > 0) {
//            mPromotionAdapter.notifyDataSetChanged();
//            mArraySpinnerAdapter.notifyDataSetChanged();
//            return;
//        }
        RequestManager.getListPromotion(self, new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                if (!response.isError()) {
                    arrPromotion.addAll(response.getDataList(Promotion.class));
                    arrContent.addAll(arrPromotion.get(0).getmDatas());
                    mPromotionAdapter.notifyDataSetChanged();
                    for (int i = 0; i < arrPromotion.size(); i++) {
                        arrCinemas.add(new Cinemas(arrPromotion.get(i).getCinemas()));
                    }
                    mArraySpinnerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        arrContent = arrPromotion.get(position).getmDatas();
        mPromotionAdapter.updateAdapter(arrContent);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
