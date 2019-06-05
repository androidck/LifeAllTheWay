package com.cloundwisdom.im.modules.network.presenter;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.api.ApiRetrofit;
import com.cloundwisdom.im.common.base.BaseObserver;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.util.SignUtils;
import com.cloundwisdom.im.modules.entry.greendao.utils.UserInfoUtils;
import com.cloundwisdom.im.modules.entry.response.PersonalCenterInfo;
import com.cloundwisdom.im.modules.entry.response.UserInfo;
import com.cloundwisdom.im.modules.network.view.IMineView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(MyActivity context) {
        super(context);
    }

    //获取个人中心资料
    public void getUserCenterInfo(){
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,null));
        prContext.showWaitingDialog("加载中");
        ApiRetrofit.getInstance().userCenter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PersonalCenterInfo>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<PersonalCenterInfo> t){
                        prContext.hideWaitingDialog();
                        Log.d("asdasd",t.getData().toString());
                        //{userNo='000335',
// userHead='http://img.minmai1688.com/img_1557238774392',
// nickName='null', realName='吴栋林', integralCalculus='0', membersLevelId='111',
// levelName='普通会员', refereeUser='贾彦平', extendOne='1',
// refereeExtendOne='1', refereePhone='15588837763',
// debitCardCount='7', creditCardCount='0',
// telephone='17116296448', needTo='0',
// extendTwo='2', registerState='null',
// isMerchant='0', identityTime='null'}
                        getView().getUserNo().setText("ID："+t.getData().getUserNo());
                        getView().getNickName().setText(t.getData().getNickName());
                        getView().getRealName().setText(t.getData().getRealName());
                        Glide.with(mContext).load(t.getData().getUserHead()).into(getView().getHeadImgUrl());
                        getView().getServicePhone().setText(t.getData().getTelephone());
                    }

                    @Override
                    protected void onError(BaseResponse<PersonalCenterInfo> t){
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
}
