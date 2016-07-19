package com.peng.weibo.ui.main.homePage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;

import com.peng.weibo.R;
import com.peng.weibo.ui.BaseFragment;

/**
 * Created by PS on 2016/7/18.
 */
public class HomePageFragment extends BaseFragment {
	@Bind(R.id.swiperLayout)
	SwipeRefreshLayout swiperLayout;

	 @Bind(R.id.homeRecyclerView)
	 RecyclerView homeRecyclerview;

	private LinearLayoutManager linearLayoutManager;

	public static HomePageFragment newInstance() {
		return new HomePageFragment();
	}


	@Override
	protected int getLayoutResId() {
		return R.layout.main_tab1;
	}

	@Override
	public void initView(View view) {
		//创建manager
		linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		homeRecyclerview.setLayoutManager(linearLayoutManager);
		//设置adapter
		homeRecyclerview.setAdapter(new HomePageAdapter(getContext(), 10));
		swiperLayout.setColorSchemeColors(R.color.colorPrimary);
		swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				swiperLayout.setRefreshing(false);
			}
		});
	}
}
