package com.jy.data.repository;

import com.jy.base.IBaseCallBack;
import com.jy.data.entity.HttpResult;
import com.jy.data.entity.VideoData;
import com.jy.data.ok.DataService;
import com.jy.main.video.VideoContract;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * created by Cherry on 2019-11-25
 **/
public class VideoRepository implements VideoContract.IVideoRepository {


    @Override
    public void getVideoNewsList(Map<String, String> params, final IBaseCallBack<HttpResult<VideoData>> callBack) {

        DataService.getService().getVideoData(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<VideoData>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<VideoData> videoDataHttpResult) {
                        callBack.onSuccess(videoDataHttpResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
