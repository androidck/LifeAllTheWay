package com.cloundwisdom.im.modules.entry.greendao.entry;

import org.greenrobot.greendao.annotation.Id;

/**
 * 系统相关参数
 */
public class SystemEntry {

    @Id(autoincrement = true)
    private Long id;

    private String deviceType;//设备类型

    private String deviceName;//设备名称

    private String deviceModel;//设备型号
}
