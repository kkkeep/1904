package com.jy;

import android.app.Application;

import com.jy.umeng.share.ShareUtils;

/*
 * created by Cherry on 2019-11-27
 **/
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ShareUtils.init(this);
    }
}
