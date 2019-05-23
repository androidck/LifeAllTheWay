package com.cloundwisdom.im.modules.ui.view;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public interface IMainView {

    SmartRefreshLayout getRefreshLayout();

    RecyclerView getRecyclerView();


}
