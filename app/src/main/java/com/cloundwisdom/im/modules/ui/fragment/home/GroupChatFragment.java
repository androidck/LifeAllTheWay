package com.cloundwisdom.im.modules.ui.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyLazyFragment;
import com.cloundwisdom.im.modules.adapter.MyViewPageAdapter;
import com.cloundwisdom.im.modules.ui.fragment.groupchat.FriendFragment;
import com.cloundwisdom.im.modules.ui.fragment.groupchat.GroupFragment;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupChatFragment extends MyLazyFragment {
    @BindView(R.id.view_title)
    TitleBar viewTitle;
    @BindView(R.id.viewPage)
    ViewPager viewPage;

    List<String> titleList;//标题列表
    List<Fragment> fragmentList;

    MyViewPageAdapter adapter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    Unbinder unbinder;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.view_title;
    }

    @Override
    protected void initView() {
        viewTitle.setTitle(R.string.fragment_group);
        viewTitle.setLeftIcon(null);
        viewTitle.setRightIcon(R.mipmap.title_add);
        titleList = new ArrayList<>();
        titleList.add("会话");
        titleList.add("群组");
        titleList.add("好友");
        fragmentList = new ArrayList<>();
        fragmentList.add(new GroupFragment());
        fragmentList.add(new FriendFragment());
        fragmentList.add(new FriendFragment());
        adapter = new MyViewPageAdapter(getChildFragmentManager(), mContext, fragmentList, titleList);
        viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPage);


    }

    @Override
    public boolean isStatusBarEnabled() {
        return !super.isStatusBarEnabled();
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
    }

    @Override
    protected void initData() {

    }


}
