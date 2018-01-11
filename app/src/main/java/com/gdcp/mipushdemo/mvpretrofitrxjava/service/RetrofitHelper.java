package com.gdcp.mipushdemo.mvpretrofitrxjava.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus- on 2018/1/11.
 */

public class RetrofitHelper{
    private Context context;
    //OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());
    private static  RetrofitHelper retrofitHelper;
    private Retrofit retrofit;

    public static RetrofitHelper getRetrofitHelper(Context context){
        if (retrofitHelper==null){
            retrofitHelper=new RetrofitHelper(context);
        }
        return retrofitHelper;
    }

    private RetrofitHelper(Context mContext){
        this.context = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.douban.com/v2/")
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
    }

    public RetrofitService getServer(){
        return retrofit.create(RetrofitService.class);
    }
}
