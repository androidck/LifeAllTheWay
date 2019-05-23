package com.cloundwisdom.im.common.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.constant.KeyConstant;
import com.hjq.base.util.ThreadManager;
import com.hjq.base.util.Util;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享工具类
 */
public class ShareUtils {

    private Tencent mTencent;// 新建Tencent实例用于调用分享方法
    private static volatile ShareUtils instance=null;
    public static Activity mContext;
    public MyIUiListener iUiListener;
    public OnQQShareResultListener qqShareResultListener;

    public ShareUtils(Activity context){
        this.mContext=context;
        //创建QQ 分享实例
        mTencent = Tencent.createInstance(KeyConstant.QQ_APP_KEY,mContext.getApplicationContext());
        iUiListener=new MyIUiListener();
    }

    //单利模式
    public static ShareUtils getInstance(Activity activity){
        if (instance==null){
            synchronized (ShareUtils.class){
                if (instance==null){
                    instance=new ShareUtils(activity);
                }
            }
        }
        return instance;
    }

    /******************************************************QQ分享*********************************************************/

    /**
     * QQ 默认分享
     *
     * @param title 标题
     * @param desc 描述
     * @param countUrl 内容url
     * @param imgUrl 网络图片URL
     * @param applicationName 应用名称
     */
    public void shareDefaultQQ(String title,String desc,String countUrl,String imgUrl,String applicationName){
        final Bundle params = new Bundle();
        //分享方式
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE,title);//标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);// 描述
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,countUrl);// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,imgUrl);// 网络图片地址　
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, applicationName);//应用名称
        //分享在主线程完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(mContext,params,iUiListener);
            }
        });
    }

    /**
     * 分享纯图片到QQ
     * @param imgUrl
     */
    public void shareImgQQ(String imgUrl){
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);// 设置分享类型为纯图片分享
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);// 需要分享的本地图片URL
        //分享在主线程完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(mContext,params,iUiListener);
            }
        });
    }

    /**
     * 分享到QQ 控件
     *
     * @param title 标题
     * @param desc 描述
     * @param countUrl 内容地址
     * @param imgUrl 图片列表
     */
    public void shareToZone(String title, String desc, String countUrl, List<String> imgUrl){
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);// 标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, desc);// 摘要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,countUrl);// 内容地址
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.addAll(imgUrl);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,imgUrlList);// 图片地址
        //分享在主线程完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(mContext,params,iUiListener);
            }
        });
    }

    /**
     * 上传图片到QQ 控件
     * @param count
     * @param imgUrlList
     */
    public void publishToQzone(String count,ArrayList<String> imgUrlList){
        final Bundle params = new Bundle();
        params.putInt(QzonePublish.PUBLISH_TO_QZONE_KEY_TYPE, QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD);
        params.putString(QzonePublish.PUBLISH_TO_QZONE_SUMMARY, count);
        params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                imgUrlList);
        //分享在主线程完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(mContext,params,iUiListener);
            }
        });
    }

    /**
     * QQ分享后的回调监听
     */
    class MyIUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            //分享成功
            qqShareResultListener.onShareResult("分享成功");
        }

        @Override
        public void onError(UiError uiError) {
            //分享失败
            qqShareResultListener.onShareResult("分享失败");
        }

        @Override
        public void onCancel() {
            //取消分享
            qqShareResultListener.onShareResult("取消分享");
        }
    }

    /**
     * qq 分享回调结果
     */
    public interface OnQQShareResultListener{
        void onShareResult(String msg);
    }

    public void setQqShareResultListener(OnQQShareResultListener qqShareResultListener) {
        this.qqShareResultListener = qqShareResultListener;
    }

    /**********************************************************************************************************************/


    /******************************************************微信分享*********************************************************/

    public int mTargetSceneOne = SendMessageToWX.Req.WXSceneSession;
    public int mTargetSceneTwo = SendMessageToWX.Req.WXSceneTimeline;

    /**
     * 纯文字分享
     * @param text
     * @param type
     */
    public void shareWeiChatWord(String text,int type){
        IWXAPI api=WXAPIFactory.createWXAPI(mContext, KeyConstant.WEI_CHAT_APP_KEY);
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        if (type==1){
            req.scene = mTargetSceneOne;
        }else {
            req.scene = mTargetSceneTwo;
        }
        //调用api接口，发送数据到微信
        api.sendReq(req);
    }


    /**
     * 纯图片分享
     * @param imgId
     * @param type
     */
    public void shareWeiChatImg(int imgId,int type){
        IWXAPI api=WXAPIFactory.createWXAPI(mContext, KeyConstant.WEI_CHAT_APP_KEY);
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(),imgId);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        //设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 200, 200, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        if (type==1){
            req.scene = mTargetSceneOne;
        }else {
            req.scene = mTargetSceneTwo;
        }
        //调用api接口，发送数据到微信
        api.sendReq(req);
    }
    /**
     * 分享Url
     * @param type
     */
    public void shareWeiChatUrl(String title,String desc,String url,int type){

        IWXAPI api=WXAPIFactory.createWXAPI(mContext, KeyConstant.WEI_CHAT_APP_KEY);
        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl =url;
        //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title =title;
        msg.description =desc;
        Bitmap thumbBmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        msg.thumbData =Util.bmpToByteArray(thumbBmp, true);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message =msg;
        if (type==1){
            req.scene = mTargetSceneOne;
        }else {
            req.scene = mTargetSceneTwo;
        }
        //调用api接口，发送数据到微信
        api.sendReq(req);
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
