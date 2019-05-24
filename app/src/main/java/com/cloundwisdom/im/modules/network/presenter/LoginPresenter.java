package com.cloundwisdom.im.modules.network.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.cloundwisdom.im.common.api.ApiRetrofit;
import com.cloundwisdom.im.common.base.BaseObserver;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.modules.entry.response.UserInfoResponse;
import com.cloundwisdom.im.modules.network.view.ILoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 登录逻辑处理
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(MyActivity context) {
        super(context);
    }

    //密码登录
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void userPwdLogin(String username,String password){
        prContext.showWaitingDialog("加载中");
        ApiRetrofit
                .getInstance()
                .userPwdLogin(username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserInfoResponse>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<UserInfoResponse> t){
                        prContext.hideWaitingDialog();
                        Log.d("loginRequest",t.toString());
                    }

                    @Override
                    protected void onError(BaseResponse<UserInfoResponse> t){
                        prContext.hideWaitingDialog();
                        Log.d("loginRequest",t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError){
                        prContext.hideWaitingDialog();
                        e.printStackTrace();
                    }
                });
    }

    //短信登录
    public void userCodeLogin(){

    }
}
