package com.pt.movieticket.datastore;

import com.google.gson.Gson;
import com.pt.movieticket.model.User;
import com.pt.movieticket.util.StringUtil;

/**
 * Created by phamv on 9/13/2016.
 */

public class DataStoreManager extends BaseDataStore {

    // ============== User ============================
    private static final String PREF_USER = "PREF_USER";
    private static final String PREF_TOKEN_USER = "PREF_TOKEN_USER";
    private static final String PREF_PASSWORD = "PREF_PASSWORD";

    /**
     * save and get user
     */
    public static void saveUser(User user) {
        if (user != null) {
            String jsonUser = user.toJSon();
            getInstance().sharedPreferences.putStringValue(PREF_USER, jsonUser);
        }
    }

    public static void removeUser() {
        getInstance().sharedPreferences.putStringValue(PREF_USER, null);
    }

    public static User getUser() {
        String jsonUser = BaseDataStore.getInstance().sharedPreferences.getStringValue(PREF_USER);
        User user = new Gson().fromJson(jsonUser, User.class);
        return user;
    }

    /**
     * save and get user's token
     */
    public static void saveToken(String token) {
        getInstance().sharedPreferences.putStringValue(PREF_TOKEN_USER, token);
    }

    public static String getToken() {
        return getInstance().sharedPreferences.getStringValue(PREF_TOKEN_USER);
    }

    public static void removeToken() {
        getInstance().sharedPreferences.putStringValue(PREF_TOKEN_USER, null);
    }

    public static void savePassword(String password) {
        getInstance().sharedPreferences.putStringValue(PREF_PASSWORD, password);
    }

    public static String getPassword() {
        return getInstance().sharedPreferences.getStringValue(PREF_PASSWORD);
    }

    public static void removePassword() {
        getInstance().sharedPreferences.putStringValue(PREF_PASSWORD, null);
    }

    /**
     * save and get caching time
     */
    public static String getCaching(String request) {
        return getInstance().dbConnection.getCaching(StringUtil.getAction(request));
    }

    public static void saveCaching(String url, String objectRoot, String timeCaching) {
        getInstance().dbConnection.saveCaching(StringUtil.getAction(url), objectRoot, timeCaching);
    }

}
