package com.peng.weibo.ui.main;

import android.graphics.Bitmap;

import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PS on 2016/7/22.
 */
public class MainPresenter implements MainContract.Present {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getUser() {
        PengApi.getInstance().wbService.loginRequest(AccessTokenKeeper.readAccessToken(view.getViewContext()).getToken(),
                AccessTokenKeeper.readAccessToken(view.getViewContext()).getUid())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<test>() {
                    @Override
                    public void onCompleted() {
                        Logs.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d("onError : " + e.toString());
                    }

                    @Override
                    public void onNext(test s) {
                        Logs.d("onNext : " + s.toString());
                        view.setDrawerData(s);
                    }
                });
    }

    @Override
    public void loadHeadPic(final String url) {
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(view.getViewContext()).load(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).unsubscribeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                view.setHeadPic(bitmap);
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onStart() {

    }
}
