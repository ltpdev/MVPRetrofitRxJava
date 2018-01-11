package com.gdcp.mipushdemo.mvpretrofitrxjava.service.presenter;

import android.content.Context;
import android.content.Intent;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.view.View;


import com.gdcp.mipushdemo.mvpretrofitrxjava.service.entity.Book;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.manager.DataManager;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.view.BookView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus- on 2018/1/11.
 */

public class BookPresenter implements Presenter{
    private DataManager dataManager;
    private CompositeSubscription mCompositeSubscription;
    private Context context;
    private BookView bookView;
    private Book book;
    public BookPresenter (Context context){
        this.context = context;
    }

    @Override
    public void onCreate() {
          dataManager=new DataManager(context);
          mCompositeSubscription=new CompositeSubscription();
    }




    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        bookView = (BookView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
    public void getSearchBooks(String name,String tag,int start,int count){
        //存放RxJava中的订阅关系,注意请求完数据要及时清掉这个订阅关系，不然会发生内存泄漏。可在onStop中通过调用CompositeSubscription的unsubscribe方法来取消这个订阅关系，不过一旦调用这个方法，那么这个CompositeSubscription也就无法再用了，要想再用只能重新new一个。

        mCompositeSubscription.add(dataManager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (book != null){
                            bookView.onSuccess(book);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        bookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book mBook) {
                        book=mBook ;
                    }
                })
        );

    }

}
