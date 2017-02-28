package com.pt.movieticket.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseFragment;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.model.Order;
import com.pt.movieticket.model.User;
import com.pt.movieticket.network.ApiResponse;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.RequestManager;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.view.activity.MainActivity;
import com.pt.movieticket.view.adapter.OrderAdapter;
import com.pt.movieticket.view.dialog.ChangePasswordDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by TitTit on 22/11/2016.
 */

public class ProfileFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {

    public static final int PICK_IMAGE_REQUEST = 1;
    private EditText edtAddress, edtEmail, edtPhone, edtPassword, edtDescription;
    private LevelListDrawable levelListDrawable;
    private ImageView ivmEdit, ivmAvatar, imvChangePass;
    private TextView tvName;
    private ViewGroup viewGroup;
    private List<Order> mDatas;
    private RecyclerView rcvOrder;
    private OrderAdapter mOrderAdapter;
    private ChangePasswordDialog mChangePasswordDialog;
    private User mUser;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void init() {
        mChangePasswordDialog = new ChangePasswordDialog(self);
    }

    @Override
    protected void initView(View view) {
        edtAddress = (EditText) view.findViewById(R.id.edt_address);
        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        edtPassword = (EditText) view.findViewById(R.id.edt_password);
        edtPhone = (EditText) view.findViewById(R.id.edt_phone);
        edtDescription = (EditText) view.findViewById(R.id.edt_description);
        ivmEdit = (ImageView) view.findViewById(R.id.ivm_edit);
        ivmAvatar = (ImageView) view.findViewById(R.id.ivm_avatar);
        imvChangePass = (ImageView) view.findViewById(R.id.ivm_change_pass);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        viewGroup = (ViewGroup) view.findViewById(R.id.view_group_profile);
        rcvOrder = (RecyclerView) view.findViewById(R.id.rcv_order);
        rcvOrder.setHasFixedSize(true);
        rcvOrder.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));

        levelListDrawable = (LevelListDrawable) ivmEdit.getDrawable();
        levelListDrawable.setLevel(Constant.STATE_EDIT);

        ivmEdit.setOnClickListener(this);
        ivmAvatar.setOnClickListener(this);
        imvChangePass.setOnClickListener(this);
        viewGroup.setOnTouchListener(this);
    }

    @Override
    protected void getData() {
        mDatas = new ArrayList<>();
        mOrderAdapter = new OrderAdapter(self, mDatas);
        rcvOrder.setAdapter(mOrderAdapter);

        RequestManager.getProfile(self, new BaseRequest.CompleteListener() {
            @Override
            public void onSuccess(ApiResponse response) {
                if (!response.isError()) {
                    User user = response.getDataObject(User.class);
                    DataStoreManager.saveUser(user);
                }
            }
            @Override
            public void onError(String message) {

            }
        });


        getAdapter();
        setAdapter();
        if (mUser == null) {

        } else {
            ImageUtil.setImage(ivmAvatar, mUser.getAvatar());
            tvName.setText(mUser.getName());
            edtEmail.setText(mUser.getEmail());
            edtPhone.setText(mUser.getPhone());
            edtAddress.setText(mUser.getAddress());
            edtDescription.setText(mUser.getDescription());
        }
    }

    public void getAdapter() {
        mDatas = new ArrayList<>();
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));
        mDatas.add(new Order("Anh", "Room 1", "Hương Ga", "01/01/2017", "A1-A2", "$ 20"));

    }

    public void setAdapter() {
        mOrderAdapter = new OrderAdapter(self, mDatas);
        rcvOrder.setAdapter(mOrderAdapter);
    }


    public void updateProfile() {
        String description, phone, address;
        description = edtDescription.getText().toString();
        phone = edtPhone.getText().toString();
        address = edtAddress.getText().toString();
        if (levelListDrawable.getLevel() == Constant.STATE_EDIT) {
            levelListDrawable.setLevel(Constant.STATE_SAVE);
            edtAddress.setEnabled(true);
            edtPhone.setEnabled(true);
            edtPassword.setEnabled(true);
            edtDescription.setEnabled(true);
            edtPhone.requestFocus();
        } else if (levelListDrawable.getLevel() == Constant.STATE_SAVE) {
            if (edtPassword.getText().toString().equals(DataStoreManager.getPassword())) {
                levelListDrawable.setLevel(Constant.STATE_EDIT);
                edtPassword.setEnabled(false);
                RequestManager.upDateProfile(self, description, phone, address, new BaseRequest.CompleteListener() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        ((MainActivity) self).showSnackBar(R.string.successfully);
                    }

                    @Override
                    public void onError(String message) {
                        ((MainActivity) self).showSnackBar(R.string.msg_update_fail);

                    }
                });
            } else if (DataStoreManager.getPassword().equals("")) {
                edtPassword.setEnabled(true);
                RequestManager.upDateProfile(self, description, phone, address, new BaseRequest.CompleteListener() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        ((MainActivity) self).showSnackBar(R.string.successfully);
                    }

                    @Override
                    public void onError(String message) {
                        ((MainActivity) self).showSnackBar(R.string.msg_update_fail);

                    }
                });
                levelListDrawable.setLevel(Constant.STATE_EDIT);
                edtAddress.setEnabled(false);
                edtPhone.setEnabled(false);
                edtDescription.setEnabled(false);
                ((MainActivity) self).showSnackBar(R.string.msg_password_not_match);
            } else {
                ((MainActivity) self).showSnackBar(R.string.msg_password_not_match);
            }

        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(self.getContentResolver(), filePath);
                ivmAvatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivm_edit:
                updateProfile();
                break;
            case R.id.ivm_avatar:
                showFileChooser();
                break;
            case R.id.ivm_change_pass:
                mChangePasswordDialog.show();
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
