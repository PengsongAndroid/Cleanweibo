package com.peng.weibo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.gordonwong.materialsheetfab.DimOverlayFrameLayout;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.peng.weibo.R;
import com.peng.weibo.ui.main.MainPagerAdapter;
import com.peng.weibo.util.common.Toasts;
import com.peng.weibo.widget.fabsheet.Fab;

import butterknife.Bind;

/**
 * Created by PS on 2016/7/13.
 */
public class MainActivity extends BaseActivity {

//	@Bind(R.id.topbar_content)
//	TextView topbarContent;
	@Bind(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@Bind(R.id.material_menu_button)
	MaterialMenuView materialMenuButton;
	@Bind(R.id.fab_sheet)
	CardView fabSheet;
	@Bind(R.id.fab)
	Fab fab;
	@Bind(R.id.overlay)
	DimOverlayFrameLayout overlay;

	private boolean direction;
	private MaterialSheetFab materialSheetFab;

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
		//左上角按钮
		materialMenuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		setTitle(R.string.app_name);
		setDrawer();
		setFab();
		setTab();
	}


	private void setTab(){
		// Setup view pager
		ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
		viewpager.setAdapter(new MainPagerAdapter(this, getSupportFragmentManager()));
//		viewpager.setOffscreenPageLimit(MainPagerAdapter.NUM_ITEMS);
//		updateFab(viewpager.getCurrentItem());

		// Setup tab layout
//		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//		tabLayout.setupWithViewPager(viewpager);
//		viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//			@Override
//			public void onPageScrolled(int i, float v, int i1) {
//			}
//
//			@Override
//			public void onPageSelected(int i) {
//				updateFab(i);
//			}
//
//			@Override
//			public void onPageScrollStateChanged(int i) {
//			}
//		});
	}

	private void setFab(){
		//初始化fab展开内容
		int sheetColor = getResources().getColor(R.color.white);
		int fabColor = getResources().getColor(R.color.themeAccent);
		materialSheetFab = new MaterialSheetFab<>(fab, fabSheet, overlay, sheetColor, fabColor);
	}

	private void setDrawer(){
		//侧边栏
		drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				materialMenuButton.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_ARROW, direction ? 2 - slideOffset
						: slideOffset);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				direction = true;
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				direction = false;
			}
		});
	}

	private void updateFab(int selectedPage) {
		switch (selectedPage) {
			case MainPagerAdapter.ALL_POS:

				break;
			case MainPagerAdapter.SHARED_POS:
				materialSheetFab.showFab(0, -getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));
				break;
			case MainPagerAdapter.FAVORITES_POS:
			default:
				materialSheetFab.hideSheetThenFab();
				break;
		}
	}

	@Override
	public void back(View view) {
		Toasts.showText("返回");
	}

	@Override
	public void onBackPressed() {
		if (materialSheetFab.isSheetVisible()) {
			materialSheetFab.hideSheet();
		} else {
			super.onBackPressed();
		}
	}
}
