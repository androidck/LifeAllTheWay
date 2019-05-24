package com.cloundwisdom.im.common.network;



import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cloundwisdom.im.common.application.MyApplication;
import com.cloundwisdom.im.common.constant.KeyConstant;
import com.cloundwisdom.im.common.greendao.UserEntryDao;
import com.cloundwisdom.im.common.network.cookie.ClearableCookieJar;
import com.cloundwisdom.im.common.network.cookie.PersistentCookieJar;
import com.cloundwisdom.im.common.network.cookie.catche.SetCookieCache;
import com.cloundwisdom.im.common.util.LogUtils;
import com.cloundwisdom.im.common.network.persistence.SharedPrefsCookiePersistor;
import com.cloundwisdom.im.modules.token.EnumHttpHeaderParam;
import com.hjq.base.util.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * @创建者 CSDN_LQR
 * @描述 配置Retrofit（配置网络缓存cache、配置持久cookie免登录）
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class BaseApiRetrofit {

    private final OkHttpClient mClient;
    private UserEntryDao userEntryDao;

    public OkHttpClient getClient() {
        userEntryDao=MyApplication.mInstance.getDaoSession().getUserEntryDao();
        return mClient;
    }

    public BaseApiRetrofit() {
        /*================== common ==================*/

        // Log信息拦截器
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别

        //cache
        File httpCacheDir = new File(MyApplication.getContext().getCacheDir(), "response");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        Cache cache = new Cache(httpCacheDir, cacheSize);

        //cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));

        //OkHttpClient
        mClient = new OkHttpClient.Builder()
                .cache(cache)
                .cookieJar(cookieJar)
                .build();
    }

//    //header配置
//    Interceptor REWRITE_HEADER_CONTROL_INTERCEPTOR = chain -> {
//        Request request=null;
//        if (userEntryDao!=null){
//            request = chain.request()
//                    .newBuilder()
////                     .addHeader(EnumHttpHeaderParam.getHeaderParam(0), userEntryDao.loadAll().get(0).getUserId()==null?"":userEntryDao.loadAll().get(0).getUserId())//用户id*/
//   //                 .addHeader("Content-type", "application/json; charset=utf-8")//时间戳
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(2), "gzip, deflate")//服务名称
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(3), "keep-alive")//签名
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(4), "*/*")//极光生成的token
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(5), "add cookies here")//设备类型
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(6), "add cookies here")//设备编码
////                    .addHeader(EnumHttpHeaderParam.getHeaderParam(7), "add cookies here")//设备型号
//                   // .addHeader(EnumHttpHeaderParam.getHeaderParam(8), KeyConstant.PROJECT_APP_ID)//项目id
//                    .build();
//        }
//
//        return chain.proceed(request);
//    };

    //cache配置
    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

        //通过 CacheControl 控制缓存数据
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
        cacheBuilder.maxStale(365, TimeUnit.DAYS);//这个是控制缓存的过时时间
        CacheControl cacheControl = cacheBuilder.build();

        //设置拦截器
        Request request = chain.request();
        if (!NetUtils.isNetworkAvailable(MyApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        if (NetUtils.isNetworkAvailable(MyApplication.getContext())) {
            int maxAge = 0;//read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;//tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Prama")
                    .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

    /*class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            LogUtils.sf(String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            LogUtils.sf(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
            return response;
        }
    }*/

}
