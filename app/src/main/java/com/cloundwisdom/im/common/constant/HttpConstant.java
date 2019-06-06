package com.cloundwisdom.im.common.constant;

import retrofit2.http.PUT;

public class HttpConstant{



    /* 请求基础地址：外网正式服务器地址：商城*/
    private static final String BASE_SHOP_URL = "http://shop.minmai1688.com/";

    private static final String MALL = "shoppingmall/";

    // public static String BASE_HOST="http://192.168.1.254:8080/";

    public static String BASE_HOST="http://middle.minmai1688.com/";



    //   public static String PROJECT="qike/app/";
    public static String PROJECT="app/";

    //访问URL
    public static String BASE_URL=BASE_HOST+PROJECT;



    /**
     * 七牛云域名
     */
    public static final String QINIU_URL = "http://img.minmai1688.com/";

    public static final String QINIU_AK = "XN8gu4Dzj4k120ld30f4SDmGHdEoDti0g1f3Yoez";

    public static final String QIUNIU_SK = "DiDnfUGK52u3tWlnYKhlJRtm3vt_kp7HflGloWvu";


    /********************************************* 接口地址 *******************************************************/

    /**
     * 密码登录接口
     */
    public static final String USER_LOGIN="user/userLogin";

    /**
     * 用户个人中心
     */
    public static final String USER_PERSONAL_CENTER = "user/getUserPersonalCenter";

    /**
     * 首页banner 查询
     */
    public static final String BANNER_QUERY_BANNER = "banner/queryBanner";

    /**
     * 获取全部文章列表
     */
    public static final String QUERY_APP_INTRODUCE_CMS_LIST ="appIntroduceCms/queryAppIntroduceCmsList";

    /**
     * 查询轮播消息
     */
    public static final String TRADE_QUERY_APP_ROLL_MESSAGE = "trade/queryAppRollMessage";

    /**
     * 请求注册时验证码接口
     */
    public static final String URL_BIND_SEND_CODE = "code/bindSendCode";

    /**
     * 验证验证码接口
     */
    public static final String URL_AJAX_VALIDATE_CODE = "code/ajaxValidateCode";


    /********************************************* h5链接 *******************************************************/
    /**
     * 商城H5链接
     */
    public static final String H5_MALL = BASE_SHOP_URL + MALL + "shoppingmall/homePage.html?openId=%s";
    /**
     * 验证码登录接口
     */
  //  public static final String URL_PHONE_USER_LOGIN = BASE_URL + BASE_PJ + "user/phoneUserLogin";
    /**
     * 退出登录接口
     */
    //public static final String URL_USER_SIGN_OUT = BASE_URL + BASE_PJ + "user/userSignOut";

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