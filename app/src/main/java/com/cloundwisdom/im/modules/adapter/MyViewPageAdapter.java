package com.cloundwisdom.im.modules.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 */
public class MyViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment;
    private List<String> mListTitle;
    private Context mContext;

    public MyViewPageAdapter(FragmentManager fm,Context context,List<Fragment> mFragment,List<String> mListTitle) {
        super(fm);
        this.mContext=context;
        this.mFragment=mFragment;
        this.mListTitle=mListTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mListTitle==null?0:mListTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
