package com.cloundwisdom.im.modules.network.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.common.api.ApiRetrofit;
import com.cloundwisdom.im.common.base.BaseObserver;
import com.cloundwisdom.im.common.base.BasePresenter;
import com.cloundwisdom.im.common.base.BaseResponse;
import com.cloundwisdom.im.common.base.MyActivity;
import com.cloundwisdom.im.common.util.SignUtils;
import com.cloundwisdom.im.modules.adapter.ArticleAdapter;
import com.cloundwisdom.im.modules.entry.request.HelpReq;
import com.cloundwisdom.im.modules.entry.response.CarouselMessageEntity;
import com.cloundwisdom.im.modules.entry.response.FindArticleEntity;
import com.cloundwisdom.im.modules.entry.response.QueryBannerEntity;
import com.cloundwisdom.im.modules.network.view.IHomeView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {

    private List<FindArticleEntity> list=new ArrayList<>();
    private ArticleAdapter adapter;

    public HomePresenter(MyActivity context) {
        super(context);
    }

    //初始化控件
    public void initView(){
        adapter=new ArticleAdapter(prContext,list);
        getView().getSwipeRecycleView().setAdapter(adapter);
        //上拉刷新，下拉加载
        getView().getRefreshLayout().setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                },300);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                },300);
            }
        });
    }

    //获取首页轮播图
    public void getBanner(){
        prContext.showWaitingDialog("加载中");
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,null));
        ApiRetrofit.getInstance().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<QueryBannerEntity>>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<List<QueryBannerEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();

                    }

                    @Override
                    protected void onError(BaseResponse<List<QueryBannerEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (isNetWorkError){

                        }
                    }
                });
    }

    //获取首页推荐文章
    public void getArticle(HelpReq helpReq){
        prContext.showWaitingDialog("加载中");
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,helpReq));
        ApiRetrofit.getInstance().getArticle(helpReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FindArticleEntity>>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<List<FindArticleEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();
                        list.addAll(t.getData());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(BaseResponse<List<FindArticleEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }

    //获取轮播通知
    public void getCarouse(){
        prContext.showWaitingDialog("加载中");
        long timeStamp= SignUtils.getInstance().getTimeStamp();
        //生成保存签名
        SignUtils.getInstance().setSign(timeStamp,SignUtils.getInstance().sign(timeStamp,null));
        ApiRetrofit.getInstance().getCarouse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<CarouselMessageEntity>>(prContext) {
                    @Override
                    protected void onSuccess(BaseResponse<List<CarouselMessageEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();
                        StringBuffer buffer=new StringBuffer();
                        for (CarouselMessageEntity messageEntity:t.getData()){
                            buffer.append(messageEntity.getMessage()+"\t\t");
                        }
                        getView().getNotice().setText(buffer.toString());
                    }

                    @Override
                    protected void onError(BaseResponse<List<CarouselMessageEntity>> t) throws Exception {
                        prContext.hideWaitingDialog();
                        toast(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }
}
