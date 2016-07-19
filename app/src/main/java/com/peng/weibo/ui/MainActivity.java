package com.peng.weibo.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;

import com.gordonwong.materialsheetfab.DimOverlayFrameLayout;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.peng.weibo.R;
import com.peng.weibo.ui.main.MainPagerAdapter;
import com.peng.weibo.util.common.Toasts;
import com.peng.weibo.widget.fabsheet.Fab;

/**
 * Created by PS on 2016/7/13.
 */
public class MainActivity extends BaseActivity {

	@Bind(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@Bind(R.id.fab_sheet)
	CardView fabSheet;
	@Bind(R.id.fab)
	Fab fab;
	@Bind(R.id.overlay)
	DimOverlayFrameLayout overlay;
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.viewpager)
	ViewPager viewpager;

	private int statusBarColor;
	private MaterialSheetFab materialSheetFab;
	private ActionBarDrawerToggle drawerToggle;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
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
		setTitle(R.string.app_name);
		setupActionBar();
		setDrawer();
		setFab();
		setTab();
	}

	private void setupActionBar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setTab() {
		// Setup view pager
		viewpager.setAdapter(new MainPagerAdapter(this, getSupportFragmentManager()));
		viewpager.setOffscreenPageLimit(MainPagerAdapter.NUM_ITEMS);
		updateFab(viewpager.getCurrentItem());

		// Setup tab layout
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewpager);
		viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i1) {
			}

			@Override
			public void onPageSelected(int i) {
				updateFab(i);
			}

			@Override
			public void onPageScrollStateChanged(int i) {
			}
		});
	}

	private void setFab() {
		// 初始化fab展开内容
		int sheetColor = getResources().getColor(R.color.white);
		int fabColor = getResources().getColor(R.color.themeAccent);
		materialSheetFab = new MaterialSheetFab<>(fab, fabSheet, overlay, sheetColor, fabColor);
		materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
			@Override
			public void onShowSheet() {
				// Save current status bar color
				statusBarColor = getStatusBarColor();
				// Set darker status bar color to match the dim overlay
				setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
			}

			@Override
			public void onHideSheet() {
				// Restore status bar color
				setStatusBarColor(statusBarColor);
			}
		});
	}

	private void setDrawer() {
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
		// 侧边栏
		drawerLayout.setDrawerListener(drawerToggle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggleDrawer();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void toggleDrawer() {
		if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
			drawerLayout.closeDrawer(GravityCompat.START);
		} else {
			drawerLayout.openDrawer(GravityCompat.START);
		}
	}

	private void updateFab(int selectedPage) {
		switch (selectedPage) {
		case MainPagerAdapter.ALL_POS:
			materialSheetFab.showFab();
			break;
		case MainPagerAdapter.SHARED_POS:
			materialSheetFab.showFab();
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

	private int getStatusBarColor() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			return getWindow().getStatusBarColor();
		}
		return 0;
	}

	private void setStatusBarColor(int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(color);
		}
	}

}
