package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.listener.ICallbackBooking;
import com.pt.movieticket.model.Booking;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.view.adapter.ArraySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TitTit on 16/01/2017.
 */

public class OneFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spType, spAddress, spTime, spDate;
    private TextView tvInfo, tvCinemas, tvBack, tvNext;
    private Film mFilm;
    private ArraySpinnerAdapter mAdapterAddress, mAdapterTime, mAdapterType, mAdapterDate;
    private List<Cinemas> mDataAddress, mDataTime, mDataType, mDataDate;
    private ICallbackBooking listener;
    private String cinemas, address, type, time, date;

    public static OneFragment newInstance(Film mFilm) {

        Bundle args = new Bundle();
        args.putParcelable(Constant.KEY_FILM, mFilm);
        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (ICallbackBooking) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFilm = getArguments().getParcelable(Constant.KEY_FILM);
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_one;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        spAddress = (Spinner) view.findViewById(R.id.sp_address);
        spType = (Spinner) view.findViewById(R.id.sp_type);
        spTime = (Spinner) view.findViewById(R.id.sp_time);
        spDate = (Spinner) view.findViewById(R.id.sp_date);

        tvCinemas = (TextView) view.findViewById(R.id.tv_cinemas);
        tvInfo = (TextView) view.findViewById(R.id.tv_info);
        tvNext = (TextView) view.findViewById(R.id.tv_next);
        tvBack = (TextView) view.findViewById(R.id.tv_back);

        tvBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        spAddress.setOnItemSelectedListener(this);
        spType.setOnItemSelectedListener(this);
        spTime.setOnItemSelectedListener(this);
        spDate.setOnItemSelectedListener(this);

    }

    @Override
    protected void getData() {
        getDataAdapter();
        setAdapter();
        cinemas = AppController.getInstance().getCinemas();
        tvCinemas.setText(cinemas);
        tvInfo.setText(tvInfo.getText() + " " + mFilm.getFilm());

    }

    public void getDataAdapter() {

        mDataAddress = new ArrayList<>();
        mDataAddress.add(new Cinemas("CGV Long Biên"));
        mDataAddress.add(new Cinemas("CGV Thái Hà"));
        mDataAddress.add(new Cinemas("CGV Cầu Giấy"));

        mDataType = new ArrayList<>();
        mDataType.add(new Cinemas("2D"));
        mDataType.add(new Cinemas("3D"));

        mDataDate = new ArrayList<>();
        mDataDate.add(new Cinemas("15/02/2017"));
        mDataDate.add(new Cinemas("16/02/2017"));
        mDataDate.add(new Cinemas("17/02/2017"));

        mDataTime = new ArrayList<>();
        mDataTime.add(new Cinemas("9:00"));
        mDataTime.add(new Cinemas("13:00"));
        mDataTime.add(new Cinemas("15:00"));
        mDataTime.add(new Cinemas("17:00"));

        mAdapterAddress = new ArraySpinnerAdapter(self, mDataAddress);
        mAdapterTime = new ArraySpinnerAdapter(self, mDataTime);
        mAdapterType = new ArraySpinnerAdapter(self, mDataType);
        mAdapterDate = new ArraySpinnerAdapter(self, mDataDate);

    }

    public void setAdapter() {
        spType.setAdapter(mAdapterType);
        spTime.setAdapter(mAdapterTime);
        spAddress.setAdapter(mAdapterAddress);
        spDate.setAdapter(mAdapterDate);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                ArrayList booking = new ArrayList<>();
                booking.add(new Booking(mFilm.getFilm(), cinemas, address, type, date, time));
                AppController.getInstance().setmData(booking);
                listener.next(Constant.FRAGMENT_TWO);
                break;
            case R.id.tv_back:
                listener.back(Constant.FRAGMENT_NULL);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_time:
                Cinemas cinemasTime = (Cinemas) parent.getAdapter().getItem(position);
                time = cinemasTime.getCinemas();
                break;
            case R.id.sp_type:
                Cinemas cinemasType = (Cinemas) parent.getAdapter().getItem(position);
                type = cinemasType.getCinemas();
                break;
            case R.id.sp_address:
                Cinemas cinemasAddress = (Cinemas) parent.getAdapter().getItem(position);
                address = cinemasAddress.getCinemas();
                break;
            case R.id.sp_date:
                Cinemas cinemasDate = (Cinemas) parent.getAdapter().getItem(position);
                date = cinemasDate.getCinemas();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
