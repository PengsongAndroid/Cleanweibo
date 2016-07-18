package com.peng.weibo.ui.main;

import android.support.v4.app.Fragment;

import com.peng.weibo.R;
import com.peng.weibo.ui.BaseFragment;

/**
 * Created by PS on 2016/7/18.
 */
public class Fragment1 extends BaseFragment{

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_tab1;
    }

    @Override
    protected int getNumColumns() {
        return 2;
    }

    @Override
    protected int getNumItems() {
        return 20;
    }
}
