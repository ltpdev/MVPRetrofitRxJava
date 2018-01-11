package com.gdcp.mipushdemo.mvpretrofitrxjava.service.presenter;

import android.content.Intent;

import com.gdcp.mipushdemo.mvpretrofitrxjava.service.view.View;

/**
 * Created by asus- on 2018/1/11.
 */

public interface Presenter{
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}
