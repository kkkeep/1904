package com.jy.kaoshi.model;

import com.jy.kaoshi.IBaseCallBack;
import com.jy.kaoshi.KaoShiApplication;
import com.jy.kaoshi.KaoShiContract;
import com.jy.kaoshi.entiry.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * created by Cherry on 2019-12-02
 **/
public class CollectModel implements KaoShiContract.ICollectMode {


    @Override
    public void getCollectData(final IBaseCallBack iBaseCallBack) {

        Observable.create(new ObservableOnSubscribe<List<Data>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Data>> emitter) throws Exception {
                List<Data> datas = KaoShiApplication.getDaoSession().getDataDao().loadAll();
                if (datas != null && datas.size() > 0) {
                    emitter.onNext(datas);
                } else {
                    emitter.onError(new NullPointerException("数据库没有数据"));
                }

                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Data>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Data> data) {
                        iBaseCallBack.onSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseCallBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    @Override
    public void deleteDataList(final List<Data> list, final IBaseCallBack<List<Data>> iBaseCallBack) {
        Observable.create(new ObservableOnSubscribe<List<Data>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Data>> emitter) throws Exception {
                KaoShiApplication.getDaoSession().getDataDao().deleteInTx(list);
                emitter.onNext(list);
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Data>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Data> data) {
                        iBaseCallBack.onSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseCallBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
