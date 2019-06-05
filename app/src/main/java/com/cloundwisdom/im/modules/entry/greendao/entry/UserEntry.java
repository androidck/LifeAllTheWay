package com.cloundwisdom.im.modules.entry.greendao.entry;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 保存本地的信息
 */
@Entity
public class UserEntry {

    @Id(autoincrement = true)
    private Long id;//自增id

    private String userId;//用户id

    private String userNo;//用户编号

    private String phone;//手机号

    private String registerState;//注册状态

    @Generated(hash = 891225231)
    public UserEntry(Long id, String userId, String userNo, String phone,
            String registerState) {
        this.id = id;
        this.userId = userId;
        this.userNo = userNo;
        this.phone = phone;
        this.registerState = registerState;
    }

    @Generated(hash = 1412082065)
    public UserEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterState() {
        return this.registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    
}
