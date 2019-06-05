package com.cloundwisdom.im.modules.entry.greendao.entry;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 签名实体类
 */
@Entity
public class SignEntry {

    @Id(autoincrement = true)
    private Long id;

    private long timestamp;

    private String sign;

    @Generated(hash = 1420651860)
    public SignEntry(Long id, long timestamp, String sign) {
        this.id = id;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    @Generated(hash = 251892224)
    public SignEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    
}
