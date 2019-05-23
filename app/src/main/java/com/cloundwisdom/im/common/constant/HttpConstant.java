package com.cloundwisdom.im.common.constant;

import retrofit2.http.PUT;

public class HttpConstant{

    public static final String BASE_HOST="";

    public static final String BASE_PJ="";

    public static final String BASE_URL = "";

    /* 请求基础地址：外网正式服务器地址：商城*/
    private static final String BASE_SHOP_URL = "http://shop.minmai1688.com/";

    private static final String MALL = "shoppingmall/";



    /*********************************************h5链接*******************************************************/
    /**
     * 商城H5链接
     */
    public static final String H5_MALL = BASE_SHOP_URL + MALL + "shoppingmall/homePage.html?openId=%s";

    /**
     * 商城订单信息
     * All  全部订单
     * -2 待付款
     * 0 待发货
     * -1 待收货
     * toEvaluate  待评价
     */
    public static final String H5_MALL_ORDER_INFORMATION = BASE_SHOP_URL + MALL + "shoppingmall/myordermall.html?openId=%1$s&type=%2$s";
    /**
     * 商城订单信息：购物车
     */
    public static final String H5_MALL_SHOPPING_CART = BASE_SHOP_URL + MALL + "shoppingmall/buycar.html?openId=%s";
    /**
     * 商城订单信息：退款/售后
     */
    public static final String H5_MALL_REFUND_OR_AFTER_SALE = BASE_SHOP_URL + MALL + "shoppingmall/afterservice.html?openId=%s";
    /**
     * 商城订单信息：我的收藏
     */
    public static final String H5_MALL_PRODUCT_COLLECTION = BASE_SHOP_URL + MALL + "shoppingmall/goodscollect.html?openId=%s";
    /**
     * 商城订单信息：浏览历史
     */
    public static final String H5_MALL_BROWSING_HISTORY = BASE_SHOP_URL + MALL + "shoppingmall/browsehistory.html?openId=%s";
    /**
     * 商城订单信息：收货地址管理
     */
    public static final String H5_MALL_HARVEST_ADDRESS = BASE_SHOP_URL + MALL + "shoppingmall/paddress.html?openId=%s";
}