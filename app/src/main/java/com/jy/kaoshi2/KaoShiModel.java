package com.jy.kaoshi2;

import android.text.TextUtils;

import com.jy.base.IBaseCallBack;
import com.jy.data.ok.DataService;
import com.jy.kaoshi.KaoShiData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * created by Cherry on 2019-12-18
 **/
public class KaoShiModel implements KSContract.KSModel {

    @Override
    public void getNews(Map<String,String> params,IBaseCallBack<ArrayList<KaoShiBean.Article>> callBack) {

        DataService.getService().getNews(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<KaoShiBean>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KaoShiBean kaoShiData) {
                        if(kaoShiData.code == 1 && kaoShiData.data != null && kaoShiData.data.getArticleList() != null){
                            callBack.onSuccess(kaoShiData.data.getArticleList());
                        }else{
                            callBack.onFail(TextUtils.isDigitsOnly(kaoShiData.message) ? "服务器错误" :  kaoShiData.message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if(e instanceof IOException){
                            callBack.onFail("网络异常，稍后重试");
                        }else{
                            callBack.onFail("未知异常");
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
