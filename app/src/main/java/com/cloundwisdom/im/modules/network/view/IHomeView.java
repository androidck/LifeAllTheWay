package com.cloundwisdom.im.modules.network.view;

import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

public interface IHomeView {

    XBanner getBanner();

    SwipeRecyclerView getSwipeRecycleView();

    SmartRefreshLayout getRefreshLayout();

    TextView getNotice();
}
