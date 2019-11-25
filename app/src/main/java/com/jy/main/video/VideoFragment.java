package com.jy.main.video;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jy.R;
import com.jy.base.BaseFragment;
import com.jy.data.entity.VideoData;

/*
 * created by Cherry on 2019-11-25
 **/
public class VideoFragment extends BaseFragment<VideoContract.IVideoPresenter> implements VideoContract.IVideoView {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getVideoNewsList(0, 0);
    }

    @Override
    public void onVideoNewsListSuccess(VideoData data) {
        Log.d("Test", data.toString());
    }

    @Override
    public void onVideoNewsListFail(String msg) {

    }

    @Override
    public VideoContract.IVideoPresenter createPresenter() {
        return new VideoPresenter();
    }
}
