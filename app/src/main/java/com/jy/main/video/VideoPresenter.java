package com.jy.main.video;

import com.jy.AppConstant;
import com.jy.base.BasePresenter;
import com.jy.base.IBaseCallBack;
import com.jy.data.entity.HttpResult;
import com.jy.data.entity.VideoData;
import com.jy.data.repository.VideoRepository;

import java.util.HashMap;
import java.util.Map;

/*
 * created by Cherry on 2019-11-25
 **/
public class VideoPresenter extends BasePresenter<VideoContract.IVideoView> implements VideoContract.IVideoPresenter {



    private VideoContract.IVideoRepository mRepository;


    public VideoPresenter(){
        mRepository = new VideoRepository();
    }

    @Override
    public void getVideoNewsList(int start, long pointTime) {
        Map<String,String> params = new HashMap<>();

        params.put(AppConstant.RequestParamsKey.VIDEOSTART, String.valueOf(start));
        params.put(AppConstant.RequestParamsKey.VIDEOTIME, String.valueOf(start));
        params.put(AppConstant.RequestParamsKey.FROM, "android");
        params.put(AppConstant.RequestParamsKey.LANG, "zh");

        mRepository.getVideoNewsList(params, new IBaseCallBack<HttpResult<VideoData>>() {
            @Override
            public void onSuccess(HttpResult<VideoData> data) {
                if(mView != null){
                    if(data.code == 1 && data.data != null){
                        mView.onVideoNewsListSuccess(data.data);
                    }else{
                        mView.onVideoNewsListFail(data.message);
                    }

                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onVideoNewsListFail(msg);
                }
            }
        });
    }
}
