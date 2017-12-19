package com.jshc.waveprogressbar.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JinT on 2017/12/19 0019.
 */

public class RetrofitUtils {
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            synchronized (RetrofitUtils.class){
                if(retrofit == null){
                   retrofit = new Retrofit.Builder()
                           .baseUrl(HttpUrlConstant.BASE_URL)
                           .addConverterFactory(GsonConverterFactory.create())
                           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                           .build();
                }
            }
        }
        return retrofit;
    }
}
