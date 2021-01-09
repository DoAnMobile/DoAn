package com.chau.phimplus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaiKhoan {

    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}