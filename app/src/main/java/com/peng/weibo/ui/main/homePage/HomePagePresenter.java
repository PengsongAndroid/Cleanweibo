package com.peng.weibo.ui.main.homePage;

import android.graphics.Bitmap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.peng.weibo.data.entity.list.StatusList;
import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePagePresenter implements HomePageContract.Present{

    private HomePageContract.View view;

    public HomePagePresenter(HomePageContract.View view){
        this.view = view;
    }

    @Override
    public void getHomeWb(long since_id, long max_id, int count, int page, int base_app, int featureType,
                          int trim_user) {
        view.startRefresh();
        PengApi.getInstance().wbService.getHomeWb(AccessTokenKeeper.readAccessToken(view.getViewContext()).getToken(),
                0, 0, 5, 1, 0, 0, 0)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StatusList>() {
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
                    public void onNext(StatusList s) {
                        Logs.d("onNext : " + s.statuses.toString());
                        view.setData(s);
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
