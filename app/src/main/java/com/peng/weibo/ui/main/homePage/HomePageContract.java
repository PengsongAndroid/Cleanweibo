package com.peng.weibo.ui.main.homePage;

import android.content.Context;

import com.peng.weibo.ui.BasePresenter;
import com.peng.weibo.ui.BaseView;

/**
 * Created by PS on 2016/7/19.
 */
public interface HomePageContract {

    interface View extends BaseView<Present>{
        void startRefresh();
        void stopRefresh();
        Context getViewContext();
    }

    interface Present extends BasePresenter{
        void getUser();
    }

}
