package com.gdcp.mipushdemo.mvpretrofitrxjava.service.manager;

import android.content.Context;

import com.gdcp.mipushdemo.mvpretrofitrxjava.service.RetrofitHelper;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.RetrofitService;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.entity.Book;

import rx.Observable;


/**
 * Created by asus- on 2018/1/11.
 */

public class DataManager {
    private RetrofitService retrofitService;
    public DataManager(Context context){
        this.retrofitService= RetrofitHelper.getRetrofitHelper(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return retrofitService.getSearchBooks(name,tag,start,count);
    }

}
