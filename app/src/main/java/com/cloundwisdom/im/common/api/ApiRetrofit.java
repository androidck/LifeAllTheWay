package com.cloundwisdom.im.common.api;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.common.network.BaseApiRetrofit;

import com.cloundwisdom.im.modules.entry.request.HelpReq;
import com.cloundwisdom.im.modules.entry.request.LoginReq;
import com.cloundwisdom.im.modules.entry.response.CarouselMessageEntity;
import com.cloundwisdom.im.modules.entry.response.FindArticleEntity;
import com.cloundwisdom.im.modules.entry.response.PersonalCenterInfo;
import com.cloundwisdom.im.modules.entry.response.QueryBannerEntity;
import com.cloundwisdom.im.modules.entry.response.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @创建者 CSDN_LQR
 * @描述 使用Retrofit对网络请求进行配置
 */
public class ApiRetrofit extends BaseApiRetrofit {

    public MyApi mApi;
    public static ApiRetrofit mInstance;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        mApi = new Retrofit.Builder()
                .baseUrl(HttpConstant.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private RequestBody getRequestBody(Object obj) {
        String route = new Gson().toJson(obj);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        return body;
    }

    //登录
    public Observable<BaseResponse<UserInfo>> login(LoginReq loginReq) {
        return mApi.userLogin(loginReq.getLoginName(),loginReq.getPwd());
    }



    //个人中心
    public Observable<BaseResponse<PersonalCenterInfo>> userCenter(){
        return mApi.userCenter();
    }


    //首页banner 图
    public Observable<BaseResponse<List<QueryBannerEntity>>> getBanner(){
       return mApi.getBanner();
    }

    //文章列表
    public Observable<BaseResponse<List<FindArticleEntity>>> getArticle(HelpReq helpReq){
        return mApi.getArticle(helpReq.getType());
    }

    //轮播消息通知
    public Observable<BaseResponse<List<CarouselMessageEntity>>> getCarouse(){
        return mApi.getCarouse();
    }

    //注册----发送验证码
    public Observable<BaseResponse<String>> registerSendCode(LoginReq loginReq){
        return mApi.registerSendCode(loginReq.getMobile(),loginReq.getCodeUse());
    }

    //注册----验证验证码
    public Observable<BaseResponse<String>> checkCode(LoginReq loginReq){
        return mApi.checkCode(loginReq.getMobile(),loginReq.getCode(),loginReq.getCodeId());
    }




}
