package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.listener.ICallbackBooking;
import com.pt.movieticket.model.Booking;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.view.activity.BookingActivity;
import com.pt.movieticket.view.adapter.BookingAdapter;
import com.pt.movieticket.widgets.textview.TextViewBold;
import com.pt.movieticket.widgets.textview.TextViewOpenSansRegular;

import java.util.List;

/**
 * Created by TitTit on 16/01/2017.
 */

public class TwoFragment extends BaseFragment implements View.OnClickListener, BookingAdapter.IData {
    private TextView tvType, tvAddress, tvCinemas, tvTime, tvDate, tvFilm, tvBack, tvNext, tvChoosing, tvTotal;
    private RecyclerView rcvBooking;
    private BookingAdapter mBookingAdapter;
    private ICallbackBooking listener;
    private int seats, total = 0;
    private List<Booking> mData;

    public static TwoFragment newInstance() {

        Bundle args = new Bundle();

        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (ICallbackBooking) activity;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_two;
    }

    @Override
    protected void init() {
        mBookingAdapter = new BookingAdapter(self, this);
    }

    @Override
    protected void initView(View view) {
        tvFilm = (TextView) view.findViewById(R.id.tv_film);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvAddress = (TextView) view.findViewById(R.id.tv_address);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvType = (TextView) view.findViewById(R.id.tv_type);
        tvCinemas = (TextView) view.findViewById(R.id.tv_cinemas);
        tvChoosing = (TextView) view.findViewById(R.id.tv_choosing);
        tvTotal = (TextView) view.findViewById(R.id.tv_total);
        tvNext = (TextView) view.findViewById(R.id.tv_next);
        tvBack = (TextView) view.findViewById(R.id.tv_back);

        tvBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        rcvBooking = (RecyclerView) view.findViewById(R.id.rcv_booking);
        rcvBooking.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));
        rcvBooking.setHasFixedSize(true);

    }

    @Override
    protected void getData() {
        mData = AppController.getInstance().getmData();
        tvCinemas.setText(mData.get(0).getCinemas());
        tvAddress.setText(mData.get(0).getAddress());
        tvType.setText(mData.get(0).getType());
        tvDate.setText(mData.get(0).getDate());
        tvTime.setText(mData.get(0).getTime());
        tvFilm.setText(mData.get(0).getFilm());

        rcvBooking.setAdapter(mBookingAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                if (seats == 0) {
                    ((BookingActivity) self).showSnackBar(R.string.msg_no_seat);
                } else {
                    listener.next(Constant.FRAGMENT_THREE);
                    AppController.getInstance().setTotal(total);
                }
                break;
            case R.id.tv_back:
                listener.back(Constant.FRAGMENT_ONE);
                break;
            default:
                break;
        }
    }


    @Override
    public void getChoosing(boolean isCheck) {
        if (isCheck) {
            seats++;
            total += 100;
        } else {
            seats--;
            total -= 100;
        }
        tvChoosing.setText("Seats : " + seats);
        tvTotal.setText("Total : " + total);

    }
}
