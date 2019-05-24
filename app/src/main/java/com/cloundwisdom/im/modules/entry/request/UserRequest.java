package com.cloundwisdom.im.modules.entry.request;

import retrofit2.http.Field;

/**
 * 登录请求
 */
public class UserRequest {

    /**
     * 手机号
     */

    public String phone;

    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
