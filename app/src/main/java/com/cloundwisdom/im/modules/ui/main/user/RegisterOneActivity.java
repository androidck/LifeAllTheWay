package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.util.RegexValidateUtil;
import com.cloundwisdom.im.common.watcher.PhoneWatcher;
import com.cloundwisdom.im.modules.entry.request.LoginReq;
import com.cloundwisdom.im.modules.network.presenter.RegisterPresenter;
import com.cloundwisdom.im.modules.network.view.IRegisterView;
import com.cloundwisdom.im.modules.token.EnumCodeUse;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册第一步
 */
@Route(path = ActivityConstant.REGISTER_ONE)
public class RegisterOneActivity extends MyActivity<IRegisterView, RegisterPresenter> {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String phone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reigster_one;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setLeftIcon(null);
        viewTitle.setTitle(R.string.title_register);
        viewTitle.setRightIcon(R.mipmap.login_close);
        edPhone.addTextChangedListener(new PhoneWatcher(edPhone));
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        finish();
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void requestVerification() {
        super.requestVerification();
        phone=edPhone.getText().toString().trim().replace(" ","");
        if (TextUtils.isEmpty(phone)|| !RegexValidateUtil.isPhone(phone)){
            toast(R.string.phone_tip);
        }else {
            //网络请求
            LoginReq loginReq=new LoginReq();
            loginReq.setMobile(phone);
            loginReq.setCodeUse(EnumCodeUse.getEnumCodeUse(R.string.registered));
            mPresenter.registerSendCode(1,loginReq);
        }
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        requestVerification();
    }
}
