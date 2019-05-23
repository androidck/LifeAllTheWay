package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册第三步
 */
@Route(path = ActivityConstant.REGISTER_Three)
public class RegisterThreeActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.ed_pwd)
    ClearEditText edPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reigster_three;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setLeftIcon(null);
        viewTitle.setRightIcon(R.mipmap.login_close);
        viewTitle.setTitle(R.string.title_register);
    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.btn_register)
    public void onViewClicked() {
    }
}
