package com.peng.weibo.ui.main.hotWeibo;

import com.peng.weibo.data.entity.list.StatusList;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PS on 2016/7/26.
 */
public class hotWeiboPresent implements hotWeiboContract.Present{

    private hotWeiboContract.View view;

    public hotWeiboPresent(hotWeiboContract.View view){
        this.view = view;
    }

    @Override
    public void getHotWeibo(int count, int page, boolean base_app) {
        view.startRefresh();
        PengApi.getInstance().wbService.getHotWbByDayAndRepost(count, base_app ? 1:0)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StatusList>() {
                    @Override
                    public void onCompleted() {
                        view.stopRefresh();
                        Logs.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.stopRefresh();
                        Logs.d("onError : " + e.toString());
                    }

                    @Override
                    public void onNext(StatusList s) {
                        Logs.d("onNext : " + s.toString());
                        view.setHotWeiboData();
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
