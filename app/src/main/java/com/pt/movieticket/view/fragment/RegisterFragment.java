package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterFragment extends BaseFragment implements View.OnClickListener, BaseRequest.CompleteListener, View.OnTouchListener {
    private EditText edtUserName, edtPassword, edtEmail, edtName, edtAddress, edtPhone;
    private ImageView imvShowPass;
    private TextView tvRegister;
    private ICallbackUser mICallbackUser;
    private Typeface typeface;
    private RadioButton rdMale, rdFemale;
    private RadioGroup rgRender;
    private ViewGroup viewGroup;
    private boolean isChecked = false;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_register;
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
        edtUserName = (EditText) view.findViewById(R.id.edt_user_name);
        edtPassword = (EditText) view.findViewById(R.id.edt_pass_word);
        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        edtName = (EditText) view.findViewById(R.id.edt_name);
        edtPhone = (EditText) view.findViewById(R.id.edt_phone);
        edtAddress = (EditText) view.findViewById(R.id.edt_address);
        imvShowPass = (ImageView) view.findViewById(R.id.imv_show_password);
        tvRegister = (TextView) view.findViewById(R.id.tv_register);
        rgRender = (RadioGroup) view.findViewById(R.id.rd_gender);
        rdMale = (RadioButton) view.findViewById(R.id.rd_male);
        rdFemale = (RadioButton) view.findViewById(R.id.rd_female);
        viewGroup = (ViewGroup) view.findViewById(R.id.rll_register);

        edtEmail.setTypeface(typeface);
        edtUserName.setTypeface(typeface);
        edtPassword.setTypeface(typeface);

        tvRegister.setOnClickListener(this);
        viewGroup.setOnTouchListener(this);
        imvShowPass.setOnClickListener(this);

    }

    public String checkGender() {
        int check = rgRender.getCheckedRadioButtonId();
        String gender = null;
        switch (check) {
            case R.id.rd_male:
                rdMale.setChecked(true);
                gender = "male";
                break;
            case R.id.rd_female:
                rdFemale.setChecked(true);
                gender = "female";
                break;
        }
        return gender;
    }

    @Override
    protected void getData() {
    }

    public void checkRegister() {
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String name = edtName.getText().toString();
        String gender = checkGender();
        if (!StringUtil.isGoodField(username)) {
            ((LoginActivity) self).showSnackBar(R.string.msg_username_required);
            edtUserName.requestFocus();
            return;
        } else if (!StringUtil.isValidEmail(email)) {
            ((LoginActivity) self).showSnackBar(R.string.msg_email_required);
            edtEmail.requestFocus();
            return;
        } else if (!StringUtil.isGoodField(password)) {
            ((LoginActivity) self).showSnackBar(R.string.msg_password_not_match);
            edtPassword.requestFocus();
            return;
        } else {
            RequestManager.register(self, username, password, name, email, phone, address, gender, this);
        }
    }

    public void showHidePassword() {
        if (isChecked) {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isChecked = false;
        } else {
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isChecked = true;
        }
    }

    @Override
    public void onSuccess(ApiResponse response) {
        mICallbackUser.showLogin();
    }

    @Override
    public void onError(String message) {
        ((LoginActivity) self).showSnackBar(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                checkRegister();
                break;
            case R.id.imv_show_password:
                showHidePassword();
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
