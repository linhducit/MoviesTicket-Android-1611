package com.pt.movieticket.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pt.movieticket.R;
import com.pt.movieticket.datastore.DataStoreManager;

import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.view.activity.MainActivity;


/**
 * Created by TitTit on 30/12/2016.
 */

public class ChangePasswordDialog extends Dialog implements View.OnClickListener {
    private TextView tvSave;
    private EditText edtNewPass, edtOldPass;
    private Context mContext;

    public ChangePasswordDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_password);
        initView();
    }

    public void initView() {
        tvSave = (TextView) findViewById(R.id.tv_save);
        edtNewPass = (EditText) findViewById(R.id.edt_new_pass);
        edtOldPass = (EditText) findViewById(R.id.edt_old_pass);
        tvSave.setOnClickListener(this);
    }

    public void changePassword() {
        final String oldPass, newPass;
        oldPass = edtOldPass.getText().toString();
        newPass = edtNewPass.getText().toString();

        RequestManager.changePassword(mContext, oldPass, newPass, new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                DataStoreManager.savePassword(newPass);
                ((MainActivity) mContext).showSnackBar(R.string.msg_update_successfully);
                dismiss();
            }

            @Override
            public void onError(String message) {
                ((MainActivity) mContext).showSnackBar(R.string.msg_update_fail);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                changePassword();
                break;
        }
    }
}
