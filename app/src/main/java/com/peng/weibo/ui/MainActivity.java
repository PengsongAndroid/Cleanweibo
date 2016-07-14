package com.peng.weibo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

import com.peng.weibo.R;
import com.peng.weibo.util.common.Toasts;

/**
 * Created by PS on 2016/7/13.
 */
public class MainActivity extends BaseActivity {

//	@Bind(R.id.topbar_content)
//	TextView topbarContent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View bindView() {
		return null;
	}

	@Override
	public int bindLayout() {
		return R.layout.main_layout;
	}

	@Override
	public void initView(View view) {
//		topbarContent.setText("首页");
	}

	@Override
	public void back(View view) {
		Toasts.showText("返回");
	}
}
