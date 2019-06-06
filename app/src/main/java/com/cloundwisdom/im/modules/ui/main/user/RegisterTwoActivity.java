package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.view.PhoneCode;
import com.cloundwisdom.im.modules.entry.request.LoginReq;
import com.cloundwisdom.im.modules.network.presenter.RegisterPresenter;
import com.cloundwisdom.im.modules.network.view.IRegisterView;
import com.cloundwisdom.im.modules.token.EnumCodeUse;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 注册第二部
 */
@Route(path = ActivityConstant.REGISTER_TWo)
public class RegisterTwoActivity extends MyActivity<IRegisterView, RegisterPresenter> implements IRegisterView {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.ed_code)
    PhoneCode edCode;
    @BindView(R.id.btn_next)
    TextView btnNext;

    @Autowired
    String phone;
    @Autowired
    String codeId;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_reigster_two;
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
        tvTip.setText("我们已向"+phone+"发送验证码短信");
        countDownState(btnNext);
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
        String code=edCode.getPhoneCode();
        //判断输入的验证码大于三位的时候进行验证
        if (code.length()>3){
            LoginReq loginReq=new LoginReq();
            loginReq.setCodeId(codeId);
            loginReq.setCode(code);
            mPresenter.checkCode(loginReq);
        }
    }



    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        LoginReq loginReq=new LoginReq();
        loginReq.setMobile(phone);
        loginReq.setCodeUse(EnumCodeUse.getEnumCodeUse(R.string.registered));
        mPresenter.registerSendCode(2,loginReq);
    }

    @Override
    public TextView countDown() {
        return btnNext;
    }
}
