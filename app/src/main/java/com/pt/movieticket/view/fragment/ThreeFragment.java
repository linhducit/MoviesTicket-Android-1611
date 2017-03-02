package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.listener.ICallbackBooking;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.model.User;
import com.pt.movieticket.paypal.Paypal;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.view.activity.BookingActivity;
import com.pt.movieticket.view.adapter.ArraySpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by TitTit on 16/01/2017.
 */

public class ThreeFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {
    private TextView tvBack, tvNext;
    private ICallbackBooking mICallbackBooking;
    private ArraySpinnerAdapter mAdapter;
    private Spinner spPay;
    private EditText edtName, edtMail, edtPhone, edtAddress;
    private ArrayList<Cinemas> mData;
    private RadioButton rdMale, rdFemale;
    private RadioGroup rgRender;
    private User mUser;
    private String gender;
    private ViewGroup viewGroup;

    public static ThreeFragment newInstance() {

        Bundle args = new Bundle();

        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mICallbackBooking = (ICallbackBooking) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paypal.startPaypalService(self);
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_three;
    }

    @Override
    protected void init() {
        mUser = DataStoreManager.getUser();
    }

    @Override
    protected void initView(View view) {
        tvNext = (TextView) view.findViewById(R.id.tv_next);
        tvBack = (TextView) view.findViewById(R.id.tv_back);
        spPay = (Spinner) view.findViewById(R.id.sp_pay);
        rgRender = (RadioGroup) view.findViewById(R.id.rd_gender);
        rdMale = (RadioButton) view.findViewById(R.id.rd_male);
        rdFemale = (RadioButton) view.findViewById(R.id.rd_female);
        edtMail = (EditText) view.findViewById(R.id.edt_email);
        edtAddress = (EditText) view.findViewById(R.id.edt_address);
        edtName = (EditText) view.findViewById(R.id.edt_name);
        edtPhone = (EditText) view.findViewById(R.id.edt_phone);
        viewGroup = (ViewGroup) view.findViewById(R.id.rll_booking);

        tvBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        viewGroup.setOnTouchListener(this);

    }

    public void checkGender() {
        int check = rgRender.getCheckedRadioButtonId();
        switch (check) {
            case R.id.rd_male:
                rdMale.setChecked(true);
                break;
            case R.id.rd_female:
                rdFemale.setChecked(true);
                break;
        }
    }

    @Override
    protected void getData() {
        if (mUser != null) {
            edtName.setText(mUser.getName());
            edtMail.setText(mUser.getEmail());
            gender = mUser.getGender();
            if (gender.equals("female")) {
                rdFemale.setChecked(true);
            } else {
                rdMale.setChecked(true);
            }
        }
        mData = new ArrayList<>();
        mData.add(new Cinemas("Paypal"));
        mData.add(new Cinemas("Delivery"));

        mAdapter = new ArraySpinnerAdapter(self, mData);
        spPay.setAdapter(mAdapter);
        checkGender();

    }

    public void checkStartPay() {
        int total = AppController.getInstance().getTotal();
        if (edtName.getText().toString().equals("") || edtMail.getText().toString().equals("")
                || edtAddress.getText().toString().equals("") || edtPhone.getText().toString().equals("")) {
            ((BookingActivity) self).showSnackBar(R.string.msg_no_more_data);
        } else {
            Paypal.requestPaypalPayment(this, total);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Paypal.isConfirm(data)) {

            mICallbackBooking.finishActivity();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                checkStartPay();
                break;
            case R.id.tv_back:
                mICallbackBooking.back(Constant.FRAGMENT_TWO);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        AppUtil.closeKeyboardWhenTouchOutside((Activity) self, viewGroup);
        return false;
    }
}