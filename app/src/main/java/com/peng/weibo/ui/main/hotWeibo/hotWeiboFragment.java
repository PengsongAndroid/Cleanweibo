package com.peng.weibo.ui.main.hotWeibo;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peng.weibo.R;
import com.peng.weibo.ui.BaseFragment;
import com.peng.weibo.ui.main.homePage.HomePageAdapter;

import butterknife.Bind;

/**
 * Created by PS on 2016/7/18.
 */
public class hotWeiboFragment extends BaseFragment implements hotWeiboContract.View {

	@Bind(R.id.publicWbSwiperLayout)
	SwipeRefreshLayout swiperLayout;

	@Bind(R.id.publicWbRecyclerView)
	RecyclerView recyclerview;

	private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

	private hotWeiboContract.Present present;

	public static hotWeiboFragment newInstance() {
		return new hotWeiboFragment();
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.main_public_wb_layout;
	}

	@Override
	public void initView(View view) {
		setPresenter(null);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(linearLayoutManager);
		// 设置adapter
		recyclerview.setAdapter(new HomePageAdapter(getContext(), 10));
		swiperLayout.setColorSchemeResources(R.color.refresh_progress_2, R.color.refresh_progress_3);
//		swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//			@Override
//			public void onRefresh() {
//				present.getHotWeibo(10, 1, false);
//			}
//		});
	}

	@Override
	public void setHotWeiboData() {

	}

	public void setRefreshing(boolean isRefresh) {
		if (isRefresh) {
			swiperLayout.setEnabled(false);
			swiperLayout.postDelayed(new Runnable() {
				@Override
				public void run() {
					swiperLayout.setEnabled(true);
					swiperLayout.setRefreshing(false);
				}
			}, 1000);
		} else {
			swiperLayout.postDelayed(new Runnable() {
				@Override
				public void run() {
					swiperLayout.setRefreshing(false);
				}
			}, 1000);
		}
	}

	@Override
	public void startRefresh() {
		swiperLayout.setRefreshing(true);
		swiperLayout.setEnabled(false);
	}

	@Override
	public void stopRefresh() {
		swiperLayout.setRefreshing(false);
		swiperLayout.setEnabled(true);
	}

	@Override
	public Context getViewContext() {
		return getActivity().getApplicationContext();
	}

	@Override
	public void setPresenter(hotWeiboContract.Present presenter) {
		this.present = new hotWeiboPresent(this);
	}
}
