package com.cloundwisdom.im.modules.network.presenter;


import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.common.api.ApiRetrofit;
import com.cloundwisdom.im.common.base.BaseObserver;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.constant.Constant;
import com.cloundwisdom.im.common.util.SignUtils;
import com.cloundwisdom.im.modules.entry.request.LoginReq;
import com.cloundwisdom.im.modules.entry.response.CarouselMessageEntity;
import com.cloundwisdom.im.modules.network.view.IRegisterView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 注册逻辑处理
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(MyActivity context) {
        super(context);
    }

    //注册第一步发送验证码
    public void registerSendCode(int type,LoginReq loginReq){
        prContext.showWaitingDialog("加载中");
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,loginReq));
        ApiRetrofit.getInstance().registerSendCode(loginReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<String> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                        if (type==1){
                            ARouter.getInstance().build(ActivityConstant.REGISTER_TWo)
                                    .withString(Constant.PHONE,loginReq.getMobile())
                                    .withString(Constant.CODE_ID,t.getData())
                                    .navigation();
                            prContext.finish();
                        }else {
                            //启动倒计时控件
                            prContext.countDownState(getView().countDown());
                        }
                    }

                    @Override
                    protected void onError(BaseResponse<String> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }

    //验证验证码
    public void checkCode(LoginReq loginReq){
        prContext.showWaitingDialog("加载中");
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,loginReq));
        ApiRetrofit.getInstance().checkCode(loginReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<String> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                        ARouter.getInstance().build(ActivityConstant.REGISTER_Three)
                                .withString(Constant.CODE,loginReq.getCode())
                                .withString(Constant.CODE_ID,t.getData())
                                .navigation();
                        prContext.finish();

                    }

                    @Override
                    protected void onError(BaseResponse<String> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }
}
