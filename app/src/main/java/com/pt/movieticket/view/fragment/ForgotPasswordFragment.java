package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.listener.ICallbackUser;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.StringUtil;
import com.pt.movieticket.view.activity.LoginActivity;
import com.pt.movieticket.widgets.textview.TextFontConfig;

/**
 * Created by TitTit on 22/11/2016.
 */

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {
    private EditText edtEmail;
    private TextView tvChangePassword;
    private ICallbackUser mICallbackUser;
    private Typeface typeface;
    private ViewGroup viewGroup;

    public static ForgotPasswordFragment newInstance() {

        Bundle args = new Bundle();

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mICallbackUser = (ICallbackUser) activity;
    }

    @Override
    protected void init() {
        typeface = Typeface.createFromAsset(self.getAssets(), TextFontConfig.FONT_OPEN_SANS_REGULAR);
    }

    @Override
    protected void initView(View view) {
        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        tvChangePassword = (TextView) view.findViewById(R.id.tv_change_password);
        viewGroup = (ViewGroup) view.findViewById(R.id.rll_forgot_password);
        tvChangePassword.setOnClickListener(this);

        edtEmail.setTypeface(typeface);
        viewGroup.setOnTouchListener(this);
    }

    @Override
    protected void getData() {

    }

    public void changePassword() {
        String email = edtEmail.getText().toString();
        if (!StringUtil.isValidEmail(email)) {
            ((LoginActivity) self).showSnackBar(R.string.msg_no_email);
            edtEmail.requestFocus();
            return;
        } else {
            RequestManager.resetPassword(self, email, new BaseRequest.CompleteListener() {
                @Override
                public void onSuccess(ApiResponse response) {
                    mICallbackUser.showLogin();
                }

                @Override
                public void onError(String message) {
                    ((LoginActivity) self).showSnackBar(message);

                }
            });
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_password:
                changePassword();
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
