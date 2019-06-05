package com.cloundwisdom.im.modules.entry.greendao.utils;

import com.cloundwisdom.im.common.application.MyApplication;
import com.cloundwisdom.im.common.greendao.UserEntryDao;
import com.cloundwisdom.im.modules.entry.greendao.entry.UserEntry;
import com.cloundwisdom.im.modules.entry.response.UserInfo;

/**
 * 本地信息处理Utils
 */
public class UserInfoUtils {

    public static UserInfoUtils instance;
    public static UserEntryDao userEntryDao;
    public static int login=0;

    public static UserInfoUtils getInstance(){
        if (instance==null){
            synchronized (UserInfoUtils.class){
                if (instance==null){
                    instance=new UserInfoUtils();
                    userEntryDao= MyApplication.mInstance.getDaoSession().getUserEntryDao();
                    if (login!=1){
                        initialize();
                    }
                }
            }
        }
        return instance;
    }

    //初始化数据
    public static void initialize(){
        UserEntry userEntry=new UserEntry();
        userEntry.setId(null);
        userEntry.setUserId("");
        userEntryDao.insert(userEntry);
        login=1;
    }

    //保存用户信息
    public void setUserInfo(UserInfo userInfo){
        UserEntry userEntry=new UserEntry();
        userEntry.setId((long) 1);
        userEntry.setUserId(userInfo.getId());
        userEntry.setPhone(userInfo.getPhone());
        userEntry.setRegisterState(String.valueOf(userInfo.getRegisterState()));
        userEntry.setUserNo(userInfo.getUserNo());
        userEntryDao.update(userEntry);
    }

    //更新用户状态
    public void updateRegisterState(String state){
        UserEntry userEntry=new UserEntry();
        userEntry.setId((long) 1);
        userEntry.setRegisterState(state);
        userEntryDao.update(userEntry);
    }


    //获取用户id
    public String getUserId(){
        String userId=userEntryDao.loadAll().get(0).getUserId();
        return userId;
    }

    //删除本地存储信息
    public void removeUserInfo(){
        userEntryDao.deleteAll();
        login=0;
    }
}
