package com.cloundwisdom.im.modules.ui.main.mine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.watcher.PhoneWatcher;
import com.hjq.bar.TitleBar;
import com.hjq.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ActivityConstant.SHOPPING_MALL_APPLY)
public class ShoppingMallApplyActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.ed_recommend_phone)
    ClearEditText edRecommendPhone;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.is_check_pay)
    CheckBox isCheckPay;
    @BindView(R.id.btn_pay)
    Button btnPay;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shoppingmall_apply;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setTitle("申请");
        edRecommendPhone.addTextChangedListener(new PhoneWatcher(edRecommendPhone));
    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
    }
}
