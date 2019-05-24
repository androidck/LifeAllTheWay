package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 实名认证 第一步
 */
@Route(path = ActivityConstant.IDENTIFY_ONE)
public class IdentifyOneActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.img_font)
    ImageView imgFont;
    @BindView(R.id.ed_name)
    ClearEditText edName;
    @BindView(R.id.ed_id_number)
    ClearEditText edIdNumber;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.ed_vail_date)
    ClearEditText edVailDate;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identify_one;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
