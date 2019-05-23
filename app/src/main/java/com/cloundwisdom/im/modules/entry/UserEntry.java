package com.cloundwisdom.im.modules.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 用户信息
 */
@Entity
public class UserEntry {


    @Id(autoincrement = true)
    private Long id;

    private String userId;

    @Generated(hash = 884401097)
    public UserEntry(Long id, String userId) {
        this.id = id;
        this.userId = userId;
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

    
}
