package com.peng.weibo.ui.main.homePage;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePagePresenter implements HomePageContract.Present{

    private HomePageContract.View view;

    public HomePagePresenter(HomePageContract.View view){
        this.view = view;
    }

    @Override
    public void getHomeWb(long since_id, long max_id, int count, int page, boolean base_app, int featureType,
                          boolean trim_user) {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onStart() {

    }

}
