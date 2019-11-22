package com.jy.base;

/*
 * created by Cherry on 2019-11-22
 **/
public interface IBaseView<P extends IBasePresenter> {

    P createPresenter();

}
