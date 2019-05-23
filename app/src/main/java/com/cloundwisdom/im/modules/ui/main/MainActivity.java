package com.cloundwisdom.im.modules.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.modules.adapter.MyFragmentPagerAdapter;
import com.cloundwisdom.im.modules.ui.fragment.home.FindFragment;
import com.cloundwisdom.im.modules.ui.fragment.home.GroupChatFragment;
import com.cloundwisdom.im.modules.ui.fragment.home.HomeFragment;
import com.cloundwisdom.im.modules.ui.fragment.home.MineFragment;
import com.cloundwisdom.im.modules.ui.fragment.home.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ActivityConstant.MAIN)
public class MainActivity extends MyActivity {


    public static final int MODE_DEFAULT = 0;

    public static final int MODE_SONIC = 1;

    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 2;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_find)
    RadioButton rbFind;
    @BindView(R.id.rb_group_chat)
    RadioButton rbGroupChat;
    @BindView(R.id.rb_extension)
    RadioButton rbExtension;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    @BindView(R.id.menu_group)
    RadioGroup menuGroup;
    @BindView(R.id.viewPage)
    ViewPager viewPage;

    //fragment 集合
    List<Fragment> fragmentList;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        //禁止viewPage 滑动

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new GroupChatFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MineFragment());
        viewPage.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        menuGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        menuGroup.check(R.id.rb_find);
                        break;
                    case 2:
                        menuGroup.check(R.id.rb_group_chat);
                        break;
                    case 3:
                        menuGroup.check(R.id.rb_extension);
                        break;
                    case 4:
                        menuGroup.check(R.id.rb_my);
                        break;
                    default:
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }



    @OnClick({R.id.rb_home, R.id.rb_find, R.id.rb_group_chat, R.id.rb_extension, R.id.rb_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                viewPage.setCurrentItem(0, false);
                break;
            case R.id.rb_find:
                viewPage.setCurrentItem(1, false);
                break;
            case R.id.rb_group_chat:
                viewPage.setCurrentItem(2, false);
                break;
            case R.id.rb_extension:
                viewPage.setCurrentItem(3, false);
                break;
            case R.id.rb_my:
                viewPage.setCurrentItem(4, false);
                break;
        }
    }
}
