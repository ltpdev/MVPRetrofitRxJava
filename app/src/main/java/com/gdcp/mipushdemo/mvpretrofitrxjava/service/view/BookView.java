package com.gdcp.mipushdemo.mvpretrofitrxjava.service.view;

import com.gdcp.mipushdemo.mvpretrofitrxjava.service.entity.Book;

/**
 * Created by asus- on 2018/1/11.
 */

public interface BookView extends View{
    void onSuccess(Book book);
    void onError(String result);
}
