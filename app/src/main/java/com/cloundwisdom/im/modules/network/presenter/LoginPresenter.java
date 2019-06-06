package com.cloundwisdom.im.modules.network.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.api.ApiRetrofit;
import com.cloundwisdom.im.common.base.BaseObserver;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.util.MD5Utils;
import com.cloundwisdom.im.common.util.SignUtils;
import com.cloundwisdom.im.modules.entry.greendao.utils.UserInfoUtils;
import com.cloundwisdom.im.modules.entry.request.LoginReq;
import com.cloundwisdom.im.modules.entry.response.UserInfo;
import com.cloundwisdom.im.modules.network.view.ILoginView;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录逻辑处理
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(MyActivity context) {
        super(context);
    }

    //密码登录
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void userPwdLogin(LoginReq loginReq){
        long timeStamp=SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,loginReq));
        prContext.showWaitingDialog("加载中");
        ApiRetrofit.getInstance().login(loginReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserInfo>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<UserInfo> t){
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                        //登录成功保存用户信息
                        UserInfoUtils.getInstance().setUserInfo(t.getData());
                        //跳转到登录页面
                        ARouter.getInstance().build(ActivityConstant.MAIN).navigation();
                    }

                    @Override
                    protected void onError(BaseResponse<UserInfo> t){
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError){
                        if (isNetWorkError){
                            toast(String.valueOf(R.string.net_work_error));
                        }
                    }
                });
    }

    //短信登录
    public void userCodeLogin(){

    }
}
