package com.cloundwisdom.im.common.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.cloundwisdom.im.common.application.MyApplication;
import com.cloundwisdom.im.common.greendao.SignEntryDao;
import com.cloundwisdom.im.modules.entry.greendao.entry.SignEntry;
import com.cloundwisdom.im.modules.token.EnumService;
import com.cloundwisdom.im.modules.token.TokenUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签名工具类
 */
public class SignUtils {

    public static SignUtils instance;
    public static SignEntryDao signEntryDao;

    //单利模式
    public static SignUtils getInstance(){
        if (instance==null){
            synchronized (SignUtils.class){
                if (instance==null){
                    instance=new SignUtils();
                    //获取签名对象
                    signEntryDao= MyApplication.mInstance.getDaoSession().getSignEntryDao();
                }
            }
        }
        return instance;
    }

    //获取当前时间戳
    public long getTimeStamp(){
        long currentTimeMillis = SystemUtil.getInstance().getCurrentTimeMillis();
        return currentTimeMillis;
    }

    //签名
    public String sign(long timestamp,Object object){
        String sign = TokenUtils.getSign(objectMap(object), EnumService.getEnumServiceByServiceName(1), timestamp);
        return sign;
    }
    //将对象转换为map
    private HashMap<String, Object> objectMap(Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Map<String, String> map1 = (Map<String, String>) JSON.parse(new Gson().toJson(obj));
        for (String key : map1.keySet()) {
            if (!TextUtils.isEmpty(String.valueOf(map1.get(key)))) {
                map.put(key, map1.get(key));
            }
        }
        return map;
    }


    /**
     * 保存签名
     * 判断是否存数据，有数据更新，无数据保存
     */
    public void setSign(long timestamp,String sign){
        SignEntry signEntry=new SignEntry();
        List<SignEntry> list=signEntryDao.loadAll();
        if (list==null||list.size()==0){
            signEntry.setId(null);
            signEntry.setTimestamp(timestamp);
            signEntry.setSign(sign);
            signEntryDao.insert(signEntry);
        }else {
            signEntry.setId((long) 1);
            signEntry.setTimestamp(timestamp);
            signEntry.setSign(sign);
            signEntryDao.update(signEntry);
        }
    }

    //获取签名
    public SignEntry getSign(){
        SignEntry signEntry=signEntryDao.loadAll().get(0);
        return signEntry;
    }

    //清除本地签名 在用户退出的时候调用
    public void removeSign(){
        if (signEntryDao.loadAll()!=null||signEntryDao.loadAll().size()!=0){
            signEntryDao.deleteAll();
        }
    }



}
