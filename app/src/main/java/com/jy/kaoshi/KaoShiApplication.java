package com.jy.kaoshi;

import android.app.Application;

import com.jy.kaoshi.entiry.DaoMaster;
import com.jy.kaoshi.entiry.DaoSession;

import org.greenrobot.greendao.database.Database;

/*
 * created by Cherry on 2019-12-02
 **/
public class KaoShiApplication extends Application {

    private static DaoSession mDaoSession;
    private static Application application;


    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
    }


    public static  synchronized DaoSession getDaoSession(){

        if(mDaoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "kaoshi.db");
            Database db = helper.getWritableDb();
            mDaoSession = new DaoMaster(db).newSession();
        }
        return mDaoSession;
    }
}
