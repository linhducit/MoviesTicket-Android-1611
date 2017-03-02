package com.pt.movieticket.model;

import com.pt.movieticket.base.model.BaseModel;


/**
 * Created by TitTit on 27/12/2016.
 */

public class User extends BaseModel {
    private String phone;
    private String avatar;
    private int total;
    public User() {

    }

    public User(String name, String email, String gender, String avatar) {
        this.setName(name);
        this.setEmail(email);
        this.setGender(gender);
        this.setAvatar(avatar);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
