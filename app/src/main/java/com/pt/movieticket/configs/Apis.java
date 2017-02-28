package com.pt.movieticket.configs;

/**
 * Created by TrangPV
 */
public class Apis {

    // Domain
    private static final String APP_DOMAIN1 = "http://projectemplate.info/movie-ticket/backend/web/index.php/api/";
    private static final String APP_DOMAIN = "https://jsonblob.com/api/";
    // Urls
    public static final String URL_REGISTER_DEVICE = APP_DOMAIN + "device";
    public static final String URL_LOGIN = APP_DOMAIN1 + "user/login";
    public static final String URL_LOGOUT = APP_DOMAIN + "user/logout";
    public static final String URL_REGISTER = APP_DOMAIN1 + "user/register";
    public static final String URL_FORGET_PASSWORD = APP_DOMAIN1 + "user/forget-password";
    public static final String URL_GET_ALL = APP_DOMAIN1 + "browseTask";
    public static final String URL_PROFILE = APP_DOMAIN1 + "user/profile";
    public static final String URL_UPDATE_PROFILE = APP_DOMAIN1 + "user/update-profile";
    public static final String URL_CHANGE_PASSWORD = APP_DOMAIN1 + "user/change-password";

    //temp
    public static final String API_BROWTASK = "browseTask";
    public static final String URL_TASK_DETAIL = APP_DOMAIN + "taskDetail";

    //URL
    public static final String URL_FILM = APP_DOMAIN + "dc71fc26-ef48-11e6-90ab-0332225c6986";
    public static final String URL_CINEMAS = APP_DOMAIN + "03f998fc-ef75-11e6-90ab-a550f7d8d68f";
    public static final String URL_PROMOTION = APP_DOMAIN + "09ef99e2-ef78-11e6-90ab-2f552551056f";

}
