package com.cloundwisdom.im.common.api;


import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.modules.entry.response.PersonalCenterInfo;
import com.cloundwisdom.im.modules.entry.response.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @创建者 CSDN_LQR
 * @描述 server端api
 */

public interface MyApi {






    @POST(HttpConstant.USER_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<UserInfo>> userLogin(@Field("loginName") String loginName, @Field("pwd")String pwd);

    /**
     * 个人中心
     * @return
     */
    @POST(HttpConstant.USER_PERSONAL_CENTER)
    Observable<BaseResponse<PersonalCenterInfo>> userCenter();

}
