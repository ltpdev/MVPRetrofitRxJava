package com.gdcp.mipushdemo.mvpretrofitrxjava.service;

import com.gdcp.mipushdemo.mvpretrofitrxjava.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by asus- on 2018/1/11.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book>getSearchBooks(@Query("q")String name,@Query("tag")String tag,@Query("start")int start,@Query("count")int count);
}
