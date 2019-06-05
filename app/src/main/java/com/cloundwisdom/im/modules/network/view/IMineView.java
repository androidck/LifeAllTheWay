package com.cloundwisdom.im.modules.network.view;

import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface IMineView {

    TextView getUserNo();//用户编号

    CircleImageView getHeadImgUrl();//头像

    TextView getNickName();//昵称

    TextView getRealName();//真实姓名

    TextView getServicePhone();//客服电话

}
