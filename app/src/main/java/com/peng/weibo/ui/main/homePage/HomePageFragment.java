package com.peng.weibo.ui.main.homePage;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;

import com.peng.weibo.R;
import com.peng.weibo.data.entity.Status;
import com.peng.weibo.data.entity.list.StatusList;
import com.peng.weibo.ui.BaseFragment;
import com.peng.weibo.ui.adapter.BaseQuickAdapter;
import com.peng.weibo.ui.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by PS on 2016/7/18.
 */
public class HomePageFragment extends BaseFragment implements HomePageContract.View {

	@Bind(R.id.swiperLayout)
	SwipeRefreshLayout swiperLayout;

	@Bind(R.id.homeRecyclerView)
	RecyclerView homeRecyclerview;

	private LinearLayoutManager linearLayoutManager;

	private HomePageContract.Present presenter;

	private List<Status> weiboData;

	private Context context;

	public static HomePageFragment newInstance() {
		return new HomePageFragment();
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.main_tab1;
	}

	@Override
	public void initView(View view) {
		setPresenter(null);
		context = getActivity();
		// 创建manager
		linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		homeRecyclerview.setLayoutManager(linearLayoutManager);
		// 设置adapter
		swiperLayout.setColorSchemeResources(R.color.refresh_progress_2,
				R.color.refresh_progress_3);
		swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				presenter.getHomeWb(0, 0, 5, 1, 0, 0, 0);
			}
		});
	}

	@Override
	public void setData(StatusList data) {
		weiboData = data.statuses;
		homeRecyclerview.setAdapter(new BaseQuickAdapter<Status>(R.layout.weibo_status_item, weiboData) {
			@Override
			protected void convert(BaseViewHolder helper, Status item) {
//				try {
//					helper.setImageBitmap(R.id.weibo_status_head_image, Picasso.with(context).load(item.user.avatar_large).get());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				helper.setText(R.id.weibo_status_profile_name, item.user.name)
				.setText(R.id.weibo_status_profile_time, item.created_at)
				.setText(R.id.weibo_status_weiboComeFrom, item.source)
				.setEmojiText(R.id.weibo_status_content, item.text)
				.setText(R.id.weibo_status_redirect, item.reposts_count + "")
				.setText(R.id.weibo_status_comment, item.comments_count + "")
				.setText(R.id.weibo_status_feedlike, item.attitudes_count + "");
			}
		});
	}

	public void setRefreshing(boolean isRefresh){
		if (isRefresh){
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
	public void setPresenter(HomePageContract.Present presenter) {
		this.presenter = new HomePagePresenter(this);
	}

	@Override
	public void startRefresh() {
		swiperLayout.setRefreshing(true);
		swiperLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				swiperLayout.setRefreshing(false);
			}
		}, 2000);
	}

	@Override
	public void stopRefresh() {
		swiperLayout.setRefreshing(false);
		swiperLayout.setEnabled(true);
	}

	@Override
	public Context getViewContext() {
		return getContext();
	}

}
