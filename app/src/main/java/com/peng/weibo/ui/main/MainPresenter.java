package com.peng.weibo.ui.main;

import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PS on 2016/7/22.
 */
public class MainPresenter implements MainContract.Present{

    private MainContract.View view;

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
                    }
                });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
