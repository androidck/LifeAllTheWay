package com.cloundwisdom.im.common.constant;

import retrofit2.http.PUT;

public class HttpConstant{

    public static final String BASE_HOST="";

    public static final String BASE_PJ="";

    public static final String BASE_URL = "";

    /* 请求基础地址：外网正式服务器地址：商城*/
    private static final String BASE_SHOP_URL = "http://shop.minmai1688.com/";

    private static final String MALL = "shoppingmall/";

    /**
     * 商城H5链接
     */
    public static final String H5_MALL = BASE_SHOP_URL + MALL + "shoppingmall/homePage.html?openId=";
}