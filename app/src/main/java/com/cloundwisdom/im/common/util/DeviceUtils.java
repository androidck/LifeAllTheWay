package com.cloundwisdom.im.common.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * 设备工具类
 */
public class DeviceUtils {

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
