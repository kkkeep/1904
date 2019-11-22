package com.jy.main.home;

import com.jy.base.IBaseCallBack;
import com.jy.base.IBasePresenter;
import com.jy.base.IBaseView;
import com.jy.data.entity.ArticleData;

/*
 * created by Cherry on 2019-11-22
 **/
public interface HomeContract {


    public interface IHomeView extends IBaseView<IHomePresenter>{

        void onArticleSuccess(ArticleData data);
        void onArticleFail(String msg);
    }

    public interface IHomePresenter extends IBasePresenter<IHomeView>{

        void getArticleList(int pageNum);
    }

    public interface IHomeRepository {


        void getArticleList(int pageNum, IBaseCallBack<ArticleData> callBack);
    }

}
