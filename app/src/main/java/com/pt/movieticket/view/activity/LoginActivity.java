package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.listener.ICallbackUser;
import com.pt.movieticket.model.User;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.network.VolleyRequestManager;
import com.pt.movieticket.social.facebook.FaceBookManager;
import com.pt.movieticket.social.facebook.FbUser;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.DialogUtil;

import com.pt.movieticket.view.fragment.ForgotPasswordFragment;
import com.pt.movieticket.view.fragment.LoginFragment;
import com.pt.movieticket.view.fragment.RegisterFragment;
import com.pt.movieticket.fcm.MyFirebaseMessagingService;

public class LoginActivity extends BaseActivity implements FaceBookManager.ICallbackLoginFacebook, ICallbackUser {
    private Fragment mCurrentFragment;
    private FragmentPosition mCurrentSelected = FragmentPosition.FRAGMENT_LOGIN;


    private enum FragmentPosition {
        FRAGMENT_LOGIN,
        FRAGMENT_REGISTER,
        FRAGMENT_FORGOT_PASSWORD
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FaceBookManager.initSdk(self);
        VolleyRequestManager.init(this);
        BaseRequest.setActivity(this);
    }

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NONE;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected void getExtraData(Intent intent) {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(this, MyFirebaseMessagingService.class);
        startService(intent);
    }

    @Override
    protected void onViewCreated() {
        switchScreen();
    }

    public void switchScreen() {
        String tag = "frag_" + mCurrentSelected;
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (mCurrentFragment == null) {
            if (mCurrentSelected == FragmentPosition.FRAGMENT_LOGIN) {
                mCurrentFragment = LoginFragment.newInstance();
            } else if (mCurrentSelected == FragmentPosition.FRAGMENT_FORGOT_PASSWORD) {
                mCurrentFragment = ForgotPasswordFragment.newInstance();
            } else if (mCurrentSelected == FragmentPosition.FRAGMENT_REGISTER) {
                mCurrentFragment = RegisterFragment.newInstance();
            }
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frl_content, mCurrentFragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void loginFacebook() {
        FaceBookManager.login(this, this);
    }

    @Override
    public void onLoginFbSuccess(final FbUser fbUser) {

        if (fbUser.getEmail() == null) {
            showSnackBar(R.string.msg_no_email);
            return;
        }
        RequestManager.loginSocial(this, fbUser.getEmail(), new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                AppUtil.startActivityLTR(LoginActivity.this, MainActivity.class);
                DataStoreManager.saveToken(response.getToken());
                DataStoreManager.saveUser(response.getDataObject(User.class));
                finish();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onLoginFbError() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FaceBookManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLogin() {
        mCurrentSelected = FragmentPosition.FRAGMENT_LOGIN;
        switchScreen();
    }

    @Override
    public void showRegister() {
        mCurrentSelected = FragmentPosition.FRAGMENT_REGISTER;
        switchScreen();
    }

    @Override
    public void showForgotPassword() {
        mCurrentSelected = FragmentPosition.FRAGMENT_FORGOT_PASSWORD;
        switchScreen();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentSelected.equals(FragmentPosition.FRAGMENT_LOGIN)) {
            DialogUtil.showAlertDialog(this, R.string.msg_confirm_exit, new DialogUtil.IDialogConfirm() {
                @Override
                public void onClickOk() {
                    finish();
                }
            });
        } else {
            getSupportFragmentManager().popBackStack();
            mCurrentSelected = FragmentPosition.FRAGMENT_LOGIN;
        }
    }
}
