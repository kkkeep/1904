package com.jy.umeng;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.jy.R;
import com.jy.utils.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;
import java.util.Set;

/*
 * created by Cherry on 2019-11-27
 **/
public class ShareActivity extends AppCompatActivity implements UMShareListener {

    private static final String TAG = "ShareActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.jy.R.layout.activity_test_share);

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }


    public void onClick(View view){

        switch (view.getId()){

            case com.jy.R.id.share_btn_wechat:{
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(this)//回调监听器
                        .share();

                // 带面板分享
               /* new ShareAction(this).withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(this).open();*/
                break;
            }
            case com.jy.R.id.share_btn_qq: {


                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(new UMImage(this, com.jy.R.drawable.umeng_socialize_back_icon))
                        .withText("hello")//分享内容
                        .setCallback(this)//回调监听器
                        .share();
                break;
            }

            case com.jy.R.id.share_btn_sina:{
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(this)//回调监听器
                        .share();
                break;
            }

            case com.jy.R.id.login_btn_wechat :{
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            }
            case com.jy.R.id.login_btn_qq :{
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            }
            case R.id.login_btn_sina :{
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            }

        }

    }


    public UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Logger.d("%s login = %s", TAG,share_media.getName());
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            Logger.d("%s login = %s", TAG,share_media.getName());
          Set<Map.Entry<String,String>> entries =  map.entrySet();

          for(Map.Entry<String,String> entry : entries){

              Logger.d("%s %s = %s", TAG,entry.getKey(),entry.getValue());
          }


        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Logger.d("%s login = %s", TAG,share_media.getName());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Logger.d("%s login = %s", TAG,share_media.getName());
        }
    };

    @Override
    public void onStart(SHARE_MEDIA share_media) {

        Logger.d("%s share = %s", TAG,share_media.getName());
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        Logger.d("%s share = %s", TAG,share_media.getName());
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Logger.d("%s share = %s", TAG,share_media.getName());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        Logger.d("%s share = %s", TAG,share_media.getName());
    }
}
