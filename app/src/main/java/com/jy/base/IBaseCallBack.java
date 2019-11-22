package com.jy.base;

/*
 * created by Cherry on 2019-11-22
 **/
public interface IBaseCallBack<T> {

    void onSuccess(T data);

    void onFail(String msg);
}
