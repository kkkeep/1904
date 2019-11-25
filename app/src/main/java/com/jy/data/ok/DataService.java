package com.jy.data.ok;

import com.jy.AppConstant;
import com.jy.BuildConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * created by Cherry on 2019-11-25
 **/
public class DataService {

    private static final long DEFAULT_TIMEOUT = 20000; // 默认超时20s

    private static volatile ApiService mApiService;




    public static synchronized ApiService getService(){

        if(mApiService == null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            OkHttpClient.Builder builder  = new OkHttpClient.Builder();


            OkHttpClient httpClient  = builder.addInterceptor(logging)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory( GsonConverterFactory.create()) // 帮我们把json 窜转为 entity 对象
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 结合 rxjava 使用
                    .baseUrl(AppConstant.BASE_URL)
                    .build();

            mApiService = retrofit.create(ApiService.class);

        }


        return mApiService;
    }
}
