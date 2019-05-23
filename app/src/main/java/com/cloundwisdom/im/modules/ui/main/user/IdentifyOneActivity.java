package com.cloundwisdom.im.modules.ui.main.user;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;

/**
 * 实名认证 第一步
 */
@Route(path = ActivityConstant.IDENTIFY_ONE)
public class IdentifyOneActivity  extends MyActivity {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
