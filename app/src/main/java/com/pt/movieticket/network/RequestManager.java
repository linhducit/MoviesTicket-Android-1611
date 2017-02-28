package com.pt.movieticket.network;

import android.content.Context;

import com.google.android.gms.auth.TokenData;
import com.google.android.gms.common.api.Api;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pt.movieticket.configs.Apis;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.util.AppUtil;

import java.util.HashMap;

/**
 */
public class RequestManager extends BaseRequest {

    private static final String TAG = RequestManager.class.getSimpleName();

    // Params
    private static final String PARAM_GCM_ID = "gcm_id";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_STATUS = "status";
    private static final String PARAM_IMEI = "ime";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_IMAGE = "image";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_USER_ID = "user_id";
    private static final String PARAM_MESSAGE = "message";
    private static final String PARAM_GENDER = "gender";
    private static final String PARAM_CURRENT_PASSWORD = "current_password";
    private static final String PARAM_NEW_PASSWORD = "new_password";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_LOGIN_TYPE = "login_type";


    ///////////////////////////
    // Add functions connecting to server
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";

    //temp
    private static final String PARAM_TASK_ID = "task_id";

    ////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public static void registerDevice(String gcm, String ime, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_IMEI, ime);
        params.put(PARAM_GCM_ID, gcm);
        params.put(PARAM_TYPE, "1");// 1 is android

        get(Apis.URL_REGISTER_DEVICE, params, false, completeListener);
    }

    public static void register(Context context, String username, String password, String name, String email,
                                String phone, String address, String gender, final CompleteListener completeListener) {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_USERNAME, username);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_NAME, name);
        params.put(PARAM_EMAIL, email);
        params.put(PARAM_PHONE, phone);
        params.put(PARAM_ADDRESS, address);
        params.put(PARAM_GENDER, gender);
        get(Apis.URL_REGISTER, params, completeListener);

    }

    public static void login(Context context, String username, String password, String email, final CompleteListener completeListener) {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_USERNAME, username);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_EMAIL, email);
        params.put(PARAM_IMEI, AppUtil.getIMEI(context));
        params.put(PARAM_GCM_ID, FirebaseInstanceId.getInstance().getToken());
        params.put(PARAM_TYPE, "1");
        params.put(PARAM_LOGIN_TYPE, "n");

        get(Apis.URL_LOGIN, params, completeListener);
    }

    public static void loginSocial(Context context, String username, String email, final CompleteListener completeListener) {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_USERNAME, username);
        params.put(PARAM_EMAIL, email);
        params.put(PARAM_IMEI, AppUtil.getIMEI(context));
        params.put(PARAM_GCM_ID, FirebaseInstanceId.getInstance().getToken());
        params.put(PARAM_TYPE, "1");
        params.put(PARAM_LOGIN_TYPE, "s");

        get(Apis.URL_LOGIN, params, completeListener);
    }

    public static void resetPassword(Context context, String email, final CompleteListener completeListener) {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_EMAIL, email);
        get(Apis.URL_FORGET_PASSWORD, params, completeListener);
    }

    public static void changePassword(Context context, String currentPass, String newPass, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_CURRENT_PASSWORD, currentPass);
        params.put(PARAM_NEW_PASSWORD, newPass);
        get(Apis.URL_CHANGE_PASSWORD, params, completeListener);
    }

    public static void getProfile(Context context, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        get(Apis.URL_PROFILE, params, completeListener);
    }

    public static void upDateProfile(Context context, String description, String phone, String address, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_DESCRIPTION, description);
        params.put(PARAM_PHONE, phone);
        params.put(PARAM_ADDRESS, address);

        get(Apis.URL_UPDATE_PROFILE, params, completeListener);
    }

    public static void getListProduct(Context context, String type, String page, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_TYPE, type);
        params.put(PARAM_PAGE, page);

        get(Apis.URL_GET_ALL, params, true, true, completeListener);
    }

    public static void getListFilm(Context context, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        get(Apis.URL_FILM, params, true, false, completeListener);
    }

    public static void getListCinemas(Context context, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        get(Apis.URL_CINEMAS, params, true, false, completeListener);
    }

    public static void getListPromotion(Context context, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        get(Apis.URL_PROMOTION, params, true, false, completeListener);
    }

    public static void getDetail(Context context, String id, final CompleteListener completeListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_TASK_ID, id);

        get(Apis.URL_TASK_DETAIL, params, completeListener);
    }

}
