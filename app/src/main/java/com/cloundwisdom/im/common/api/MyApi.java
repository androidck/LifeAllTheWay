package com.cloundwisdom.im.common.api;


import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.modules.entry.response.CarouselMessageEntity;
import com.cloundwisdom.im.modules.entry.response.FindArticleEntity;
import com.cloundwisdom.im.modules.entry.response.PersonalCenterInfo;
import com.cloundwisdom.im.modules.entry.response.QueryBannerEntity;
import com.cloundwisdom.im.modules.entry.response.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @创建者 CSDN_LQR
 * @描述 server端api
 */

public interface MyApi {


    /**
     * 用户登录
     * @param loginName 用户名
     * @param pwd 密码
     * @return
     */
    @POST(HttpConstant.USER_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<UserInfo>> userLogin(@Field("loginName") String loginName, @Field("pwd")String pwd);

    /**
     * 个人中心
     * @return
     */
    @POST(HttpConstant.USER_PERSONAL_CENTER)
    Observable<BaseResponse<PersonalCenterInfo>> userCenter();

    /**
     * 首页banner 轮播图
     * @return
     */
    @POST(HttpConstant.BANNER_QUERY_BANNER)
    Observable<BaseResponse<List<QueryBannerEntity>>> getBanner();

    /**
     * 获取文章列表
     * @param type 1 推荐 2全部
     * @return
     */
    @POST(HttpConstant.QUERY_APP_INTRODUCE_CMS_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<List<FindArticleEntity>>> getArticle(@Field("type") String type);

    /**
     * 获取轮播通知
     * @return
     */
    @POST(HttpConstant.TRADE_QUERY_APP_ROLL_MESSAGE)
    Observable<BaseResponse<List<CarouselMessageEntity>>> getCarouse();

    /**
     * 注册发送验证码
     * @param mobile
     * @return
     */
    @POST(HttpConstant.URL_BIND_SEND_CODE)
    @FormUrlEncoded
    Observable<BaseResponse<String>> registerSendCode(@Field("mobile") String mobile,@Field("codeUse") String codeUse);


    /**
     * 注册发送验证码
     * @param mobile
     * @return
     */
    @POST(HttpConstant.URL_AJAX_VALIDATE_CODE)
    @FormUrlEncoded
    Observable<BaseResponse<String>> checkCode(@Field("mobile") String mobile,
                                               @Field("code") String codeUse,
                                               @Field("codeId") String codeId);
}
