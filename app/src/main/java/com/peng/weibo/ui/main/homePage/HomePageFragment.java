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
import com.peng.weibo.util.common.TransferUtil;
import com.peng.weibo.util.task.CommonEvent;
import com.peng.weibo.util.task.RxBus;

import java.util.List;

/**
 * Created by PS on 2016/7/18.
 */
public class HomePageFragment extends BaseFragment implements HomePageContract.View {

	@Bind(R.id.swiperLayout)
	SwipeRefreshLayout swiperLayout;

	@Bind(R.id.homeRecyclerView)
	RecyclerView homeRecyclerview;

	//不用final会导致滑动时崩溃 原因 http://stackoverflow.com/questions/27416834/app-crashing-when-trying-to-use-recyclerview-on-android-5-0
	private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

	private HomePageContract.Present presenter;

	private List<com.peng.weibo.data.myentity.Status> weiboData;

	private Context context;

	private static final int HIDE_FAB = 0x001;
	private static final int SHWO_FAB = 0x002;

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
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		homeRecyclerview.setLayoutManager(linearLayoutManager);
		homeRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (dy > 0){//up
					RxBus.$().post("MainActivity", new CommonEvent(HIDE_FAB));
				} else {//down
					RxBus.$().post("MainActivity", new CommonEvent(SHWO_FAB));
				}
			}
		});
		// 设置adapter
		swiperLayout.setColorSchemeResources(R.color.refresh_progress_2, R.color.refresh_progress_3);
		swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				presenter.getHomeWb();
			}
		});
	}

	@Override
	public void setData(com.peng.weibo.data.myentity.list.StatusList data) {
		weiboData = data.statuses;
		homeRecyclerview.setAdapter(new BaseQuickAdapter<com.peng.weibo.data.myentity.Status>(R.layout.weibo_status_item, weiboData) {
			@Override
			protected void convert(BaseViewHolder helper, com.peng.weibo.data.myentity.Status item) {
				helper.setImageBitmap(R.id.weibo_status_head_image, "test", context)
//				.setWeiboImageList(R.id.weibo_status_image, item.pic_infos, context)
				.setText(R.id.weibo_status_profile_name, "test")
				.setText(R.id.weibo_status_profile_time, item.created_at)
				.setText(R.id.weibo_status_weiboComeFrom, TransferUtil.patternCode(""))
				.setEmojiText(R.id.weibo_status_content, item.text, context)
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
