package com.jy.kaoshi.presenter;

import com.jy.kaoshi.IBaseCallBack;
import com.jy.kaoshi.KaoShiContract;
import com.jy.kaoshi.entiry.Data;
import com.jy.kaoshi.model.CollectModel;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class CollectPresenter implements KaoShiContract.ICollectPresenter {

    private KaoShiContract.ICollectView mICollectView;
    private KaoShiContract.ICollectMode mICollectMode;

    public CollectPresenter (){
        mICollectMode = new CollectModel();
    }

    @Override
    public void getCollectData() {
        mICollectMode.getCollectData(new IBaseCallBack<List<Data>>() {
            @Override
            public void onSuccess(List<Data> list) {
                if(mICollectView != null){
                    mICollectView.onDataResult(list, null);
                }
            }

            @Override
            public void onError(String msg) {
                if(mICollectView != null){
                    mICollectView.onDataResult(null, msg);
                }
            }
        });
    }



    @Override
    public void deleteDataList(List<Data> list) {

        mICollectMode.deleteDataList(list, new IBaseCallBack<List<Data>>() {
            @Override
            public void onSuccess(List<Data> list) {
                if(mICollectView != null){
                    mICollectView.onDeleteDataResult(list, null);
                }
            }

            @Override
            public void onError(String msg) {
                if(mICollectView != null){
                    mICollectView.onDeleteDataResult(null, msg);
                }
            }
        });

    }
    public void attachView(KaoShiContract.ICollectView homeView){
        mICollectView = homeView;
    }

    public void detachView(){
        mICollectView = null;
    }


}
