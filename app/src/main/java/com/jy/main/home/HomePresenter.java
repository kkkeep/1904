package com.jy.main.home;

import com.jy.base.BasePresenter;
import com.jy.base.IBaseCallBack;
import com.jy.data.entity.ArticleData;
import com.jy.data.repository.HomeRepository;

/*
 * created by Cherry on 2019-11-22
 **/
public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {

    private HomeContract.IHomeRepository mRepository;


    public HomePresenter() {
        this.mRepository = new HomeRepository();
    }

    @Override
    public void getArticleList(int pageNum) {
        mRepository.getArticleList(pageNum, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                if(mView != null){
                    mView.onArticleSuccess(data);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onArticleFail(msg);
                }
            }
        });
    }
}
