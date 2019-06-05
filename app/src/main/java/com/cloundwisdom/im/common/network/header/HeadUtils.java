package com.cloundwisdom.im.common.network.header;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;

/**
 * Header 工具类
 */
public class HeadUtils {

    public static HeadUtils instance;
    public static Headers  headers;

    public static HeadUtils getInstance(){
        if (instance==null){
            synchronized (HeadUtils.class){
                if (instance==null){
                    instance=new HeadUtils();

                }
            }
        }
        return instance;
    }


}
