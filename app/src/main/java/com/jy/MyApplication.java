package com.jy;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.jy.libeaseim.EaseUtil;
import com.jy.umeng.share.ShareUtils;

/*
 * created by Cherry on 2019-11-27
 **/
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        EaseUtil.init(this);
        ShareUtils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
