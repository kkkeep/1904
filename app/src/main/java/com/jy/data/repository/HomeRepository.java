package com.jy.data.repository;

import com.jy.base.IBaseCallBack;
import com.jy.data.entity.ArticleData;
import com.jy.main.home.HomeContract;

/*
 * created by Cherry on 2019-11-22
 **/
public class HomeRepository implements HomeContract.IHomeRepository {
    @Override
    public void getArticleList(int pageNum, IBaseCallBack<ArticleData> callBack) {

    }
}
