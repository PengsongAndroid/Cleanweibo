package com.peng.weibo.ui.main.homePage;

import android.view.View;

import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePagePresenter implements HomePageContract.Present{

    private HomePageContract.View view;

    public HomePagePresenter(HomePageContract.View view){
        this.view = view;
    }

    @Override
    public void getUser() {
        view.startRefresh();
        PengApi.getInstance().wbService.loginRequest(AccessTokenKeeper.readAccessToken(view.getViewContext()).getToken(),
                AccessTokenKeeper.readAccessToken(view.getViewContext()).getUid())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<test>() {
                    @Override
                    public void onCompleted() {
                        Logs.d("onCompleted");
                        view.stopRefresh();
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
        view = null;
    }

    @Override
    public void onStart() {

    }
}
