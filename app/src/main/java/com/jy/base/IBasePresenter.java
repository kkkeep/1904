package com.jy.base;

/*
 * created by Cherry on 2019-11-22
 **/
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();
}
