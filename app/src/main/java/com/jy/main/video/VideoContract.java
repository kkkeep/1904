package com.jy.main.video;

import com.jy.base.IBaseCallBack;
import com.jy.base.IBasePresenter;
import com.jy.base.IBaseView;
import com.jy.data.entity.HttpResult;
import com.jy.data.entity.VideoData;

import java.util.Map;

/*
 * created by Cherry on 2019-11-25
 **/
public interface VideoContract {


    public interface IVideoView extends IBaseView<IVideoPresenter>{

        void onVideoNewsListSuccess(VideoData data);
        void onVideoNewsListFail(String msg);

    }

    public interface IVideoPresenter extends IBasePresenter<IVideoView>{

        void getVideoNewsList(int start,long pointTime);
    }


    public interface  IVideoRepository{

        void getVideoNewsList(Map<String,String> params, IBaseCallBack<HttpResult<VideoData>> callBack);
    }
}
