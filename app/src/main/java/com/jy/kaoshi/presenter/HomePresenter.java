package com.jy.kaoshi.presenter;

import com.jy.kaoshi.entiry.Data;
import com.jy.kaoshi.model.HomeMode;
import com.jy.kaoshi.IBaseCallBack;
import com.jy.kaoshi.KaoShiContract;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class HomePresenter implements KaoShiContract.IHomePresenter {

    private KaoShiContract.IHomeView mIHomeView;

    private KaoShiContract.IHomeMode mIHomeMode;

    public HomePresenter(){
        mIHomeMode = new HomeMode();
    }

    @Override
    public void getHomeData() {
        mIHomeMode.getHomeData(new IBaseCallBack<List<Data>>() {
            @Override
            public void onSuccess(List<Data> list) {
                if(mIHomeView != null){
                    mIHomeView.onDataResult(list, null);
                }
            }

            @Override
            public void onError(String msg) {
                if(mIHomeView != null){
                    mIHomeView.onDataResult(null, msg);
                }
            }
        });
    }
    @Override
    public void collect(Data data) {

        mIHomeMode.collect(data, new IBaseCallBack<Data>() {
            @Override
            public void onSuccess(Data data) {
                if(mIHomeView != null){
                    mIHomeView.onCollectResult(data,null);
                }
            }

            @Override
            public void onError(String msg) {
                if(mIHomeView != null){
                    mIHomeView.onCollectResult(null,msg);
                }
            }
        });
    }

    public void attachView(KaoShiContract.IHomeView homeView){
        mIHomeView = homeView;
    }

    public void detachView(){
        mIHomeView = null;
    }
}
