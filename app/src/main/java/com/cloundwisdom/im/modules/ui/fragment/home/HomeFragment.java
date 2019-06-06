package com.cloundwisdom.im.modules.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.base.MyLazyFragment;
import com.cloundwisdom.im.modules.entry.request.HelpReq;
import com.cloundwisdom.im.modules.network.presenter.HomePresenter;
import com.cloundwisdom.im.modules.network.view.IHomeView;
import com.cloundwisdom.im.modules.ui.main.MainActivity;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends MyLazyFragment<IHomeView, HomePresenter> implements IHomeView {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    public XBanner banner;
    public TextView tvNotice;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    public boolean isStatusBarEnabled() {
        return !super.isStatusBarEnabled();
    }

    @Override
    protected void initView() {
        viewTitle.setLeftIcon(null);
        viewTitle.setTitle(R.string.fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //添加头布局
        View headView= getLayoutInflater().inflate(R.layout.fragment_home_head, recyclerView, false);
        banner=headView.findViewById(R.id.banner);
        tvNotice=headView.findViewById(R.id.tv_advert);
        recyclerView.addHeaderView(headView);
        mPresenter.initView();
    }

    @Override
    protected void initData() {
        mPresenter.getBanner();
        getArticle();
        mPresenter.getCarouse();
    }

    //获取文章列表
    private void getArticle(){
        HelpReq helpReq=new HelpReq();
        helpReq.setType("1");
        mPresenter.getArticle(helpReq);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter((MainActivity) getContext());
    }

    @Override
    public XBanner getBanner() {
        return banner;
    }

    @Override
    public SwipeRecyclerView getSwipeRecycleView() {
        return recyclerView;
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    @Override
    public TextView getNotice() {
        return tvNotice;
    }


}
