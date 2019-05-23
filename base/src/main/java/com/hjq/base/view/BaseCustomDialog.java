package com.hjq.base.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义BaseDialog
 */
public abstract class BaseCustomDialog extends BaseDialog {


    public BaseCustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Window window = this.getWindow();
        window.setGravity(showType());
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        initView();
        initData();
    }

    public abstract int getLayoutId();

    public abstract int showType();

    public abstract void initView();

    public abstract void initData();
}
