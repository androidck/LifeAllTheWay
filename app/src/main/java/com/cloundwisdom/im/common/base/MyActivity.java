package com.cloundwisdom.im.common.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.application.MyApplication;
import com.cloundwisdom.im.common.greendao.SignEntryDao;
import com.cloundwisdom.im.common.greendao.UserEntryDao;
import com.cloundwisdom.im.common.view.CustomDialog;
import com.cloundwisdom.im.common.web.BrowserActivity;
import com.cloundwisdom.im.modules.entry.greendao.entry.UserEntry;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.base.helper.ActivityStackManager;
import com.hjq.base.web.SonicJavaScriptInterface;
import com.hjq.toast.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.materialdialog.MaterialDialog;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目中的 Activity 基类
 */
public abstract class MyActivity<V, T extends BasePresenter<V>> extends UIActivity
        implements OnTitleBarListener {

    private CustomDialog mDialogWaiting;
    private MaterialDialog mMaterialDialog;
    public T mPresenter;
    public SignEntryDao signEntryDao;
    public UserEntryDao userEntryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().onActivityCreated(this);

    }

    private Unbinder mButterKnife;//View注解

    @Override
    protected void initLayout() {
        super.initLayout();
        // 初始化标题栏的监听
        if (getTitleBarId() > 0) {
            if (findViewById(getTitleBarId()) instanceof TitleBar) {
                ((TitleBar) findViewById(getTitleBarId())).setOnTitleBarListener(this);
            }
        }
        mButterKnife = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        signEntryDao= MyApplication.mInstance.getDaoSession().getSignEntryDao();
        userEntryDao=MyApplication.mInstance.getDaoSession().getUserEntryDao();
        initOrientation();
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        // 当前 Activity 不能是透明的并且没有指定屏幕方向，默认设置为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(int titleId) {
        setTitle(getText(titleId));
    }

    //验证输入框内容
    public void requestVerification(){

    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.setTitle(title);
        }
    }

    @Nullable
    public TitleBar getTitleBar() {
        if (getTitleBarId() > 0 && findViewById(getTitleBarId()) instanceof TitleBar) {
            return findViewById(getTitleBarId());
        }
        return null;
    }

    @Override
    public boolean statusBarDarkFont() {
        //返回true表示黑色字体
        return true;
    }

    /**
     * {@link OnTitleBarListener}
     */

    // 标题栏左边的View被点击了
    @Override
    public void onLeftClick(View v) {
        onBackPressed();
    }

    // 标题栏中间的View被点击了
    @Override
    public void onTitleClick(View v) {}

    // 标题栏右边的View被点击了
    @Override
    public void onRightClick(View v) {}

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mButterKnife != null) mButterKnife.unbind();
        ActivityStackManager.getInstance().onActivityDestroyed(this);
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }

    public void toast(int id) {
        ToastUtils.show(id);
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    /**
     * 显示等待提示框
     */
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(this, view, R.style.MyDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }


    /**
     * 显示MaterialDialog
     */
    public MaterialDialog showMaterialDialog(String title, String message, String positiveText, String negativeText, View.OnClickListener positiveButtonClickListener, View.OnClickListener negativeButtonClickListener) {
        hideMaterialDialog();
        mMaterialDialog = new MaterialDialog(this);
        if (!TextUtils.isEmpty(title)) {
            mMaterialDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mMaterialDialog.setMessage(message);
        }
        if (!TextUtils.isEmpty(positiveText)) {
            mMaterialDialog.setPositiveButton(positiveText, positiveButtonClickListener);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            mMaterialDialog.setNegativeButton(negativeText, negativeButtonClickListener);
        }
        mMaterialDialog.show();
        return mMaterialDialog;
    }

    /**
     * 隐藏MaterialDialog
     */
    public void hideMaterialDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }


    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //是否登录
    public boolean isLogin(){
        List<UserEntry> list=userEntryDao.loadAll();
        if (list==null){
            //登录之前的判断

            return false;
        }else {
            return true;
        }
    }

    /**
     * 不同状态处理
     * @param state
     * @return
     */
    public boolean isRegisterState(int state){
        if (state==1){
            //实名认证第一步
            return false;
        }else if (state==2){
            //实名认证第二步
            return false;
        }else if (state==3){
            //实名认证第三步
            return false;
        }else {
            return true;
        }
    }

    //跳转到浏览器
    public void startBrowserActivity(Context context, int mode, String url) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(BrowserActivity.PARAM_URL, url);
        intent.putExtra(BrowserActivity.PARAM_MODE, mode);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivityForResult(intent, -1);
    }

    //倒计时
    public void countDownState(TextView textView){
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText(millisUntilFinished / 1000 + "秒后重新获取");
                textView.setTextColor(Color.parseColor("#8d8d8d"));
            }
            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText("重新获取");
                textView.setTextColor(Color.parseColor("#0096ff"));
            }
        }.start();
    }
}