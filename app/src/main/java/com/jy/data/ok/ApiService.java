package com.jy.data.ok;

import com.jy.data.entity.HttpResult;
import com.jy.data.entity.VideoData;
import com.jy.kaoshi.KaoShiData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/*
 * created by Cherry on 2019-11-25
 **/
public interface ApiService {


    //请求视频列表
    @GET("/app/v_1_1/article/videolist")
    Observable<HttpResult<VideoData>> getVideoData(@QueryMap Map<String, String> map);

    //请求视频列表
    @GET("http://gank.io/api/data/福利/20/1")
    Observable<KaoShiData> getKaoShiData();

}
