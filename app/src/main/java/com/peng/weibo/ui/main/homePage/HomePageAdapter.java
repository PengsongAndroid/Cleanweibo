package com.peng.weibo.ui.main.homePage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peng.weibo.R;
import com.peng.weibo.util.tools.Logs;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

	private static final String TAG = HomePageAdapter.class.getSimpleName();

	private int num;

	private Context mContext;

	public HomePageAdapter(Context context, int num) {
		this.num = num;
		mContext = context;
		Logs.e("adapter init");
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Logs.e("ViewHolder");
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weibo_status_item, parent, false);
		ViewHolder holder = new ViewHolder(v);
		holder.imageRecyclerView = (RecyclerView) v.findViewById(R.id.weibo_status_image);
		final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		holder.imageRecyclerView.setLayoutManager(linearLayoutManager);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Logs.e("onBindViewHolder");
		// holder.titleTextView.setText("");
	}

	@Override
	public int getItemCount() {
		return num;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView titleTextView;

		public RecyclerView imageRecyclerView;

		public ViewHolder(View itemView) {
			super(itemView);
		}
	}
}
