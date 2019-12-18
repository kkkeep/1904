package com.jy.kaoshi2;

import com.jy.base.IBaseCallBack;
import com.jy.base.IBasePresenter;
import com.jy.base.IBaseView;

import java.util.ArrayList;
import java.util.Map;

/*
 * created by Cherry on 2019-12-18
 **/
public interface KSContract {



    public interface KSView extends IBaseView<KSPresenter> {

        void onResultBack(ArrayList<KaoShiBean.Article> articles,String msg);
    }


    public interface KSPresenter extends IBasePresenter<KSView>{

        void getNews(String platform,String lang);

    }


    public interface KSModel {

        void getNews(Map<String,String> params, IBaseCallBack<ArrayList<KaoShiBean.Article>> callBack);
    }

}
