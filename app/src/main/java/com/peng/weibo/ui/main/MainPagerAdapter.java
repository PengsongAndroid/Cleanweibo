package com.peng.weibo.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.peng.weibo.R;

/**
 * Created by PS on 2016/7/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int NUM_ITEMS = 3;
    public static final int ALL_POS = 0;
    public static final int SHARED_POS = 1;
    public static final int FAVORITES_POS = 2;

    private Context context;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ALL_POS:
                return null;
            case SHARED_POS:
                return null;
            case FAVORITES_POS:
                return null;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ALL_POS:
                return context.getString(R.string.tab1);
            case SHARED_POS:
                return context.getString(R.string.tab2);
            case FAVORITES_POS:
                return context.getString(R.string.tab3);
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}