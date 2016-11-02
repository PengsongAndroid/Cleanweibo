package com.peng.weibo.ui.main.homePage;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.peng.weibo.data.myentity.list.StatusList;
import com.peng.weibo.net.ps.PsServerApi;
import com.peng.weibo.util.tools.Logs;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePagePresenter implements HomePageContract.Present {

	private HomePageContract.View view;

	public HomePagePresenter(HomePageContract.View view) {
		this.view = view;
	}

	@Override
	public void getHomeWb(long since_id, long max_id, int count, int page, int base_app, int featureType, int trim_user) {

//		PengApi.getInstance().wbService.getHomeWb(AccessTokenKeeper.readAccessToken(view.getViewContext()).getToken(), 0, 0, 5, 1, 0, 0, 0)
//				.unsubscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//				.subscribe(new Subscriber<StatusList>() {
//					@Override
//					public void onStart() {
//						super.onStart();
//						view.startRefresh();
//					}
//
//					@Override
//					public void onCompleted() {
//						Logs.d("onCompleted");
//						view.stopRefresh();
//					}
//
//					@Override
//					public void onError(Throwable e) {
//						Logs.d("onError : " + e.toString());
//					}
//
//					@Override
//					public void onNext(StatusList s) {
//						Logs.d("onNext : " + s.statuses.toString());
//						view.setData(s);
//					}
//				});

	}

	@Override
	public void getHomeWb() {
		PsServerApi.getInstance().service.getHomeWb().unsubscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<StatusList>() {
					@Override
					public void onStart() {
						super.onStart();
						view.startRefresh();
					}

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
