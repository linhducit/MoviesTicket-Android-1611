package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.paypal.Paypal;
import com.pt.movieticket.widgets.textview.TextFontConfig;

/**
 * Created by TitTit on 25/11/2016.
 */

public class PaymentActivity extends BaseActivity implements BaseRequest.CompleteListener, View.OnClickListener {
    private EditText edtEmail, edtCardNumber, edtPhoneNumber;
    private TextView tvPayment;
    private CheckBox ckbRemember;
    private Typeface typeface;

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NAVI;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_payment;
    }

    @Override
    protected void getExtraData(Intent intent) {

    }

    @Override
    protected void initialize() {
        Paypal.startPaypalService(this);
        typeface = Typeface.createFromAsset(getAssets(), TextFontConfig.FONT_OPEN_SANS_LIGHT);
        setToolbarTitle(R.string.payment);
//        setToolbarSize(16);
    }

    @Override
    protected void initView() {
        edtCardNumber = (EditText) findViewById(R.id.edt_card_number);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        tvPayment = (TextView) findViewById(R.id.tv_payment);
        ckbRemember = (CheckBox) findViewById(R.id.ckb_remember);

        tvPayment.setOnClickListener(this);
    }

    @Override
    protected void onViewCreated() {
//        edtCardNumber.setTypeface(typeface);
//        edtEmail.setTypeface(typeface);
//        edtPhoneNumber.setTypeface(typeface);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Paypal.isConfirm(data);
    }

    public void getRequest() {
        RequestManager.getListProduct(this, "", "", this);
    }

    @Override
    public void onSuccess(ApiResponse response) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_payment:
                Paypal.requestPaypalPayment(this, 90);
                break;
        }
    }
}
