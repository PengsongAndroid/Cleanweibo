package com.peng.weibo.ui.main.fuli;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;

import com.peng.weibo.R;
import com.peng.weibo.ui.BaseFragment;
import com.peng.weibo.ui.main.homePage.HomePageAdapter;

/**
 * Created by PS on 2016/7/18.
 */
public class FuliFragment extends BaseFragment implements FuliContract.View {

	@Bind(R.id.swiperLayout)
	SwipeRefreshLayout swiperLayout;

	@Bind(R.id.homeRecyclerView)
	RecyclerView homeRecyclerview;

	private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

	public static FuliFragment newInstance() {
		return new FuliFragment();
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.main_tab1;
	}

	@Override
	public void initView(View view) {
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		homeRecyclerview.setLayoutManager(linearLayoutManager);
		// 设置adapter
		homeRecyclerview.setAdapter(new HomePageAdapter(getContext(), 10));
		swiperLayout.setColorSchemeResources(R.color.refresh_progress_2, R.color.refresh_progress_3);
		swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				setRefreshing(swiperLayout.isRefreshing());
			}
		});
	}

	public void requestDataRefresh() {

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
			// swiperLayout.setRefreshing(false);
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
		swiperLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				swiperLayout.setRefreshing(false);
			}
		}, 1000);
	}

	@Override
	public void setPresenter(FuliContract.Present presenter) {

	}
}
