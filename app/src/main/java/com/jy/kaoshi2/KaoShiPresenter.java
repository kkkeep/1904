package com.jy.kaoshi2;

import com.jy.base.BasePresenter;
import com.jy.base.IBaseCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * created by Cherry on 2019-12-18
 **/
public class KaoShiPresenter extends BasePresenter<KSContract.KSView> implements KSContract.KSPresenter {


    private KSContract.KSModel mMode;


    public KaoShiPresenter(){
        mMode = new KaoShiModel();
    }
    @Override
    public void getNews(String platform, String lang) {

        Map<String,String> map = new HashMap<>();
        map.put("from", platform);
        map.put("lang", lang);

        mMode.getNews(map, new IBaseCallBack<ArrayList<KaoShiBean.Article>>() {
            @Override
            public void onSuccess(ArrayList<KaoShiBean.Article> data) {
               if(mView != null){
                   mView.onResultBack(data, null);
               }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onResultBack(null , msg);
                }
            }
        });

    }
}
