package com.jy.kaoshi.model;

import com.jy.data.ok.DataService;
import com.jy.kaoshi.IBaseCallBack;
import com.jy.kaoshi.KaoShiApplication;
import com.jy.kaoshi.KaoShiContract;
import com.jy.kaoshi.KaoShiData;
import com.jy.kaoshi.entiry.Data;

import java.io.IOException;

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
public class HomeMode implements KaoShiContract.IHomeMode {


    @Override
    public void getHomeData(final IBaseCallBack iBaseCallBack) {

        DataService.getService().getKaoShiData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<KaoShiData>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KaoShiData kaoShiData) {
                        if(!kaoShiData.error) {
                            if(kaoShiData.results != null && kaoShiData.results.size() > 0){
                                iBaseCallBack.onSuccess(kaoShiData.results);
                            }else{
                                iBaseCallBack.onError("服务器返回数据为空");
                            }

                        }else{
                            iBaseCallBack.onError("服务器错误");
                        }
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
    public void collect(final Data data, final IBaseCallBack<Data> iBaseCallBack) {
        Observable.create(new ObservableOnSubscribe<Data>() {
            @Override
            public void subscribe(ObservableEmitter<Data> emitter) throws Exception {

                long id = KaoShiApplication.getDaoSession().getDataDao().insert(data);
                if (id != -1) {
                    emitter.onNext(data);
                } else {
                    emitter.onError(new IOException("插入数据库失败"));
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data data) {
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
