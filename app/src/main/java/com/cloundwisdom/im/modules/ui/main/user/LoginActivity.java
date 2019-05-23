package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.util.RegexValidateUtil;
import com.cloundwisdom.im.common.watcher.PhoneWatcher;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ActivityConstant.LOGIN)
public class LoginActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.ed_pwd)
    ClearEditText edPwd;
    @BindView(R.id.tv_forgot_pwd)
    TextView tvForgotPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_login_type)
    TextView tvLoginType;

    private String phone;
    private String pwd;

    public int loginType=0;//默认登录类型 密码登录

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setLeftIcon(null);
        viewTitle.setTitle(R.string.title_login);
        viewTitle.setRightIcon(R.mipmap.login_close);
        edPhone.addTextChangedListener(new PhoneWatcher(edPhone));
    }

    @Override
    public void requestVerification() {
        super.requestVerification();
        phone=edPhone.getText().toString().trim().replace(" ","");
        pwd=edPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)|| !RegexValidateUtil.isPhone(phone)){
            toast(R.string.phone_tip);
        }else if (TextUtils.isEmpty(pwd)){
            toast(R.string.pwd_tip);
        }else {
            //这里调用网络请求的方法
        }
    }

    public void updateLoginType(){
        if (loginType==0){
            //切换为验证码登录
            //切换为密码登录
            edPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            edPwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});//限制输入6位
            tvLoginType.setText(R.string.pwd_login);
            tvForgotPwd.setText(R.string.forgot_pwd);
            edPwd.setHint(R.string.hint_pwd);
        }else if (loginType==1){
            edPwd.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            edPwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});//限制输入6位
            tvLoginType.setText(R.string.code_login);
            tvForgotPwd.setText(R.string.send_msg);
            edPwd.setHint(R.string.hint_code);

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        finish();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }




    @OnClick({R.id.tv_forgot_pwd, R.id.btn_login, R.id.tv_register, R.id.tv_login_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot_pwd:
                break;
            case R.id.btn_login:
                requestVerification();
                break;
            case R.id.tv_register:
                ARouter.getInstance().build(ActivityConstant.REGISTER_ONE).navigation();
                break;
            case R.id.tv_login_type:
                if (loginType==1){
                    loginType=0;
                    updateLoginType();
                }else if (loginType==0){
                    loginType=1;
                    updateLoginType();
                }
                break;
        }
    }
}
