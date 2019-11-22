package com.jy.main.home;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jy.base.BaseFragment;
import com.jy.data.entity.ArticleData;

/*
 * created by Cherry on 2019-11-22
 **/
public class HomeFragment extends BaseFragment<HomeContract.IHomePresenter> implements HomeContract.IHomeView {



;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onArticleSuccess(ArticleData data) {

    }

    @Override
    public void onArticleFail(String msg) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getArticleList(0);
    }

    @Override
    public HomeContract.IHomePresenter createPresenter() {
        return new HomePresenter();
    }


}
