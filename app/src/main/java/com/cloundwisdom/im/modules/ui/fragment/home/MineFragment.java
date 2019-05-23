package com.cloundwisdom.im.modules.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyLazyFragment;
import com.cloundwisdom.im.common.constant.ActivityConstant;
import com.cloundwisdom.im.common.constant.HttpConstant;
import com.cloundwisdom.im.common.constant.KeyConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心
 */
public class MineFragment extends MyLazyFragment {
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.nick_name)
    TextView nickName;
    @BindView(R.id.edit_nick_name)
    TextView editNickName;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_user_no)
    TextView tvUserNo;
    @BindView(R.id.collect)
    TextView collect;
    @BindView(R.id.ly_collection)
    AutoLinearLayout lyCollection;
    @BindView(R.id.history)
    TextView history;
    @BindView(R.id.ly_history)
    AutoLinearLayout lyHistory;
    @BindView(R.id.evaluate)
    TextView evaluate;
    @BindView(R.id.ly_evaluate)
    AutoLinearLayout lyEvaluate;
    @BindView(R.id.tv_my_order)
    TextView tvMyOrder;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_shop_cart)
    TextView tvShopCart;
    @BindView(R.id.tv_stay_pay)
    TextView tvStayPay;
    @BindView(R.id.tv_stay_goods)
    TextView tvStayGoods;
    @BindView(R.id.tv_stay_collect)
    TextView tvStayCollect;
    @BindView(R.id.tv_stay_retreat)
    TextView tvStayRetreat;
    @BindView(R.id.ly_apply)
    AutoLinearLayout lyApply;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.ly_service_phone)
    AutoLinearLayout lyServicePhone;
    @BindView(R.id.tv_shopping_apply)
    TextView tvShoppingApply;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected boolean statusBarDarkFont() {
        return !super.statusBarDarkFont();
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.img_head, R.id.edit_nick_name,  R.id.ly_collection, R.id.ly_history, R.id.ly_evaluate,R.id.tv_all_order, R.id.tv_shop_cart, R.id.tv_stay_pay, R.id.tv_stay_goods, R.id.tv_stay_collect, R.id.tv_stay_retreat, R.id.ly_apply, R.id.tv_address, R.id.tv_service, R.id.ly_service_phone, R.id.tv_shopping_apply, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.edit_nick_name:
                break;
            case R.id.ly_collection:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_PRODUCT_COLLECTION, KeyConstant.USER_ID));
                break;
            case R.id.ly_history:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_BROWSING_HISTORY, KeyConstant.USER_ID));
                break;
            case R.id.ly_evaluate:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_ORDER_INFORMATION, KeyConstant.USER_ID, "toEvaluate"));
                break;
            case R.id.tv_all_order:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_ORDER_INFORMATION, KeyConstant.USER_ID, "all"));
                break;
            case R.id.tv_shop_cart:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_SHOPPING_CART, KeyConstant.USER_ID));
                break;
            case R.id.tv_stay_pay:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_ORDER_INFORMATION, KeyConstant.USER_ID, "-2"));
                break;
            case R.id.tv_stay_goods:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_ORDER_INFORMATION, KeyConstant.USER_ID, "0"));
                break;
            case R.id.tv_stay_collect:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_ORDER_INFORMATION, KeyConstant.USER_ID, "-1"));
                break;
            case R.id.tv_stay_retreat:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_REFUND_OR_AFTER_SALE, KeyConstant.USER_ID));
                break;
            case R.id.ly_apply:
                ARouter.getInstance().build(ActivityConstant.ON_LINE_CARD).navigation();
                break;
            case R.id.tv_address:
                startBrowserActivity(mContext,1, String.format(HttpConstant.H5_MALL_HARVEST_ADDRESS, KeyConstant.USER_ID));
                break;
            case R.id.tv_service:
                break;
            case R.id.ly_service_phone:
                break;
            case R.id.tv_shopping_apply:
                ARouter.getInstance().build(ActivityConstant.SHOPPING_MALL_APPLY).navigation();
                break;
            case R.id.tv_setting:
                ARouter.getInstance().build(ActivityConstant.MORE_SETUP).navigation();
                break;
        }
    }
}
