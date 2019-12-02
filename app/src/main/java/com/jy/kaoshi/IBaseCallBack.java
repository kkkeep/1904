package com.jy.kaoshi;

import com.jy.kaoshi.entiry.Data;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public interface IBaseCallBack<T> {

    void onSuccess(T list);
    void onError(String msg);
}
