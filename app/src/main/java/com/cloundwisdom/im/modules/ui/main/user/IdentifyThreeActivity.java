package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
@Route(path = ActivityConstant.IDENTIFY_THREE)
public class IdentifyThreeActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.img_bank_card)
    ImageView imgBankCard;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_no)
    ClearEditText tvBankNo;
    @BindView(R.id.tv_open_city)
    TextView tvOpenCity;
    @BindView(R.id.tv_branch_bank)
    TextView tvBranchBank;
    @BindView(R.id.tv_phone)
    ClearEditText tvPhone;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identify_three;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setTitle("实名认证");
    }

    @Override
    protected void initData() {

    }


}
