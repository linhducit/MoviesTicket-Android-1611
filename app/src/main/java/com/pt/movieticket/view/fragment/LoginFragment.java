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

import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.listener.ICallbackUser;
import com.pt.movieticket.model.User;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.StringUtil;
import com.pt.movieticket.view.activity.MainActivity;
import com.pt.movieticket.view.activity.LoginActivity;
import com.pt.movieticket.widgets.textview.TextFontConfig;


/**
 * Created by TitTit on 22/11/2016.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {
    private EditText edtPassword, edtEmail;
    private TextView tvForgot, tvRegister, tvStartNow, tvSignIn, tvSignFacebook;
    private ICallbackUser mICallbackUser;
    private ViewGroup viewGroup;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mICallbackUser = (ICallbackUser) activity;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_login;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initView(View view) {
        edtPassword = (EditText) view.findViewById(R.id.edt_password);
        edtEmail = (EditText) view.findViewById(R.id.edt_email);

        tvSignIn = (TextView) view.findViewById(R.id.tv_sign_in);
        tvSignFacebook = (TextView) view.findViewById(R.id.tv_sign_facebook);
        tvForgot = (TextView) view.findViewById(R.id.tv_forgot_password);
        tvRegister = (TextView) view.findViewById(R.id.tv_register);
        tvStartNow = (TextView) view.findViewById(R.id.tv_start_now);

        viewGroup = (ViewGroup) view.findViewById(R.id.rll_login);

        tvSignIn.setOnClickListener(this);
        tvSignFacebook.setOnClickListener(this);
        tvForgot.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvStartNow.setOnClickListener(this);
        viewGroup.setOnTouchListener(this);

        Typeface typeface = Typeface.createFromAsset(self.getAssets(), TextFontConfig.FONT_OPEN_SANS_REGULAR);
        edtPassword.setTypeface(typeface);
        edtEmail.setTypeface(typeface);

    }

    @Override
    protected void getData() {

    }

    public void checkLogin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (!StringUtil.isGoodField(password)) {
            ((LoginActivity) self).showSnackBar(R.string.msg_password_not_match);
            edtPassword.requestFocus();
            return;
        } else {
            RequestManager.login(self,email, password, new BaseRequest.CompleteListener() {
                @Override
                public void onSuccess(ApiResponse response) {
                    AppUtil.startActivityLTR((Activity) getContext(), MainActivity.class);
                    ((LoginActivity) self).finish();
                    User user = response.getDataObject(User.class);
                    DataStoreManager.saveUser(user);
                    DataStoreManager.saveToken(response.getToken());
                    DataStoreManager.savePassword(edtPassword.getText().toString());
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
            case R.id.tv_sign_in:
                checkLogin();
                break;
            case R.id.tv_sign_facebook:
                mICallbackUser.loginFacebook();
                break;
            case R.id.tv_register:
                mICallbackUser.showRegister();
                break;
            case R.id.tv_forgot_password:
                mICallbackUser.showForgotPassword();
                break;
            case R.id.tv_start_now:
                AppUtil.startActivityLTR((Activity) getContext(), MainActivity.class);
                ((LoginActivity) self).finish();
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
