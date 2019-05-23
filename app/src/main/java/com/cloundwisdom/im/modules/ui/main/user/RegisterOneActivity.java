package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.watcher.PhoneWatcher;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册第一步
 */
@Route(path = ActivityConstant.REGISTER_ONE)
public class RegisterOneActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

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
    protected void initData() {

    }



    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        ARouter.getInstance().build(ActivityConstant.REGISTER_TWo).navigation();
    }
}
