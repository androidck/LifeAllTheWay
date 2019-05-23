package com.cloundwisdom.im.modules.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyLazyFragment;
import com.hjq.bar.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 发现
 */
public class FindFragment extends MyLazyFragment {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ly_friend)
    AutoLinearLayout lyFriend;
    @BindView(R.id.ly_spread)
    AutoLinearLayout lySpread;
    @BindView(R.id.ly_drift)
    AutoLinearLayout lyDrift;



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }


    @Override
    protected void initView() {
        viewTitle.setLeftIcon(null);
        viewTitle.setTitle(R.string.fragment_find);
    }

    @Override
    public boolean isStatusBarEnabled() {
        return !super.isStatusBarEnabled();
    }

    @Override
    protected void initData() {

    }




    @OnClick({R.id.btn_add, R.id.ly_friend, R.id.ly_spread, R.id.ly_drift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.ly_friend:
                break;
            case R.id.ly_spread:
                break;
            case R.id.ly_drift:
                break;
        }
    }
}
