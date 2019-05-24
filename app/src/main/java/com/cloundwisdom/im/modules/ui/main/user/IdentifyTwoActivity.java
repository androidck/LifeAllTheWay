package com.cloundwisdom.im.modules.ui.main.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.hjq.bar.TitleBar;
import com.hjq.base.util.SingleClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实名认证 第一步
 */
@Route(path = ActivityConstant.IDENTIFY_TWO)
public class IdentifyTwoActivity extends MyActivity {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.img_id_card_hand)
    ImageView imgIdCardHand;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identify_two;
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



    @OnClick({R.id.img_id_card_hand, R.id.btn_next})
    @SingleClick
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_id_card_hand:
                break;
            case R.id.btn_next:
                break;
        }
    }
}
