package com.example.walpaper_kotlin.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalpaperManager {


    private static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor getInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
    public static WallpaperService setupRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build().create(WallpaperService.class);
    }
}
