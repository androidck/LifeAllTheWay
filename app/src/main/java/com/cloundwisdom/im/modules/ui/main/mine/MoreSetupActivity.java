package com.cloundwisdom.im.modules.ui.main.mine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 更多设置
 */
@Route(path = ActivityConstant.MORE_SETUP)
public class MoreSetupActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.tv_account_security)
    TextView tvAccountSecurity;
    @BindView(R.id.is_switch_phone)
    Switch isSwitchPhone;
    @BindView(R.id.tv_dump)
    TextView tvDump;
    @BindView(R.id.btn_login_out)
    Button btnLoginOut;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_setup;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setTitle(R.string.more_setup);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_login_out)
    public void onViewClicked() {
    }
}
