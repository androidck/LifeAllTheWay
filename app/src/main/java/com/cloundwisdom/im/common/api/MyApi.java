package com.cloundwisdom.im.common.api;


import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.modules.entry.response.UserInfoResponse;

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
     * @return
     */
    @POST(HttpConstant.URL_USER_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<UserInfoResponse>> userPwdLogin(@Field("loginName") String phone,
                                                            @Field("pwd") String pwd);

}
