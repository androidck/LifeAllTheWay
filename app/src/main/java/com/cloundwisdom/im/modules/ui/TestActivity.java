package com.cloundwisdom.im.modules.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.widget.Button;

import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.MyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends MyActivity {
    @BindView(R.id.btn_seed)
    Button btnSeed;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.btn_seed)
    public void onViewClicked() {
        List<String> list=new ArrayList<>();
        list.add("13127158259");
        list.add("15588837763");
        sendSms(this,"批量短信发送",list);
    }


    public static void sendSms(Context context, String text, List<String> numbers) {
        String numbersStr = "";
        String symbol = "Samsung".equalsIgnoreCase(Build.MANUFACTURER) ? ",": ";";
        if (numbers != null && !numbers.isEmpty()) {
            numbersStr = TextUtils.join(symbol, numbers);
        }
        Uri uri = Uri.parse("smsto:" + numbersStr);

        Intent intent = new Intent();
        intent.setData(uri);
        intent.putExtra("sms_body", text);
        intent.setAction(Intent.ACTION_SENDTO);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context);
            if (defaultSmsPackageName != null) {
                intent.setPackage(defaultSmsPackageName);
            }
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
