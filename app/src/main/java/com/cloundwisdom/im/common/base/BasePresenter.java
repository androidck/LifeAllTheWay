package com.cloundwisdom.im.common.base;

import com.hjq.base.view.BaseActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    /*================== 以下是网络请求接口 ==================*/

    public MyActivity prContext;

    public BasePresenter(MyActivity context) {
        this.prContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public int page=1;
    public int pageSize=10;

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }


    /**
     * Toast 提示
     * @param msg
     */
    public void toast(String msg){

    }

}
