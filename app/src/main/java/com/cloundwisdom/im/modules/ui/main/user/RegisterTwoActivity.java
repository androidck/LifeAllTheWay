package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.view.PhoneCode;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 注册第二部
 */
@Route(path = ActivityConstant.REGISTER_TWo)
public class RegisterTwoActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.ed_code)
    PhoneCode edCode;
    @BindView(R.id.btn_next)
    TextView btnNext;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

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
        ARouter.getInstance().build(ActivityConstant.REGISTER_Three).navigation();
    }
}
