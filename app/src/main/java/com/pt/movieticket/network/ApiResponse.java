package com.pt.movieticket.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Trang Pham
 */

public class ApiResponse {
    public static final String KEY_NAME_ERROR = "error";
    public static final String KEY_NAME_CODE = "code";
    public static final String KEY_NAME_MESSAGE = "message";
    public static final String KEY_NAME_STATUS = "status";
    public static final String KEY_NAME_SUCCESS = "Success";
    public static final String KEY_NAME_DATA = "data";
    public static final String KEY_NAME_TOKEN = "login_token";

    public static final int ERROR_CODE_NOERROR = 0;
    public static final int ERROR_CODE_UNKOWN = -9000;
    public static final int ERROR_CODE_JSON_ERROR = -9001;
    public static final int ERROR_CODE_INVALID_RESPONSE_FORMAT = -9002;
    public static final int ERROR_CODE_REQUEST_TIMEOUT = -9003;
    public static final int ERROR_CODE_NOT_ENOUGH_MONEY = 412;

    private int code;
    private boolean isError;
    private String message;
    private String token;
    private JSONObject data;

    public ApiResponse(int code, String message) {
        this.setCode(code);
        this.message = message != null ? message : "";
        this.isError = code != ERROR_CODE_NOERROR;
    }

    public ApiResponse(JSONObject json) {
        if (json == null) {
            this.isError = true;
            this.message = "Empty json";
            this.code = ERROR_CODE_JSON_ERROR;
        } else {
            try {
                if (json.getString(KEY_NAME_STATUS).equalsIgnoreCase(KEY_NAME_SUCCESS)) {
                    this.message = json.optString(KEY_NAME_MESSAGE, "Success");
                    this.data = json;
                    this.token = json.optString(KEY_NAME_TOKEN);
                    Log.e("Tit",token);
                } else {
                    this.isError = true;
                    this.message = json.optString(KEY_NAME_MESSAGE, "Error");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method get object from data if data responde is a object
     *
     * @param tClass
     * @return Object
     */

    public <T> T getDataObject(Class<T> tClass) {
        JSONObject object = getDataObject();
        T obj = new GsonBuilder().create().fromJson(object.toString(), tClass);
        return obj;
    }

    /**
     * This get list object from data if responde is array
     *
     * @param tClass
     * @return List<Object>
     */
    public <T> List<T> getDataList(Class<T> tClass) {
        List<T> listObj = new ArrayList<>();
        JSONArray arr = getDataArray();
        if (data != null) {
            JSONObject jo;
            T object;
            int size = arr.length();
            Gson gson = new GsonBuilder().create();
            for (int i = 0; i < size; i++) {
                jo = arr.optJSONObject(i);
                if (jo != null) {
                    object = gson.fromJson(jo.toString(), tClass);
                    listObj.add(object);
                }

            }
        }
        return listObj;
    }


    public JSONObject getDataObject() {
        return this.data != null ? this.data.optJSONObject(KEY_NAME_DATA) : null;
    }

    public JSONArray getDataArray() {
        return this.data != null ? this.data.optJSONArray(KEY_NAME_DATA) : null;
    }

    public String getValueFromRoot(String key) {
        try {
            return this.data.getString(key);
        } catch (JSONException e) {
            Log.e("getValueFromRoot()", "error: the key not exist");
            return "";
        }
    }

    public JSONObject getRoot() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return this.isError;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
