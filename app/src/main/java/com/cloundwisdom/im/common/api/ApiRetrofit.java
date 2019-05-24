package com.cloundwisdom.im.common.api;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.common.network.BaseApiRetrofit;
import com.cloundwisdom.im.modules.entry.response.UserInfoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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



    //登录
    public Observable<BaseResponse<UserInfoResponse>> userPwdLogin(String phone,String pwd){
        return mApi.userPwdLogin(phone,pwd);
    }
}
