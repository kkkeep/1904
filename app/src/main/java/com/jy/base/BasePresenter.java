package com.jy.base;

/*
 * created by Cherry on 2019-11-22
 **/
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
