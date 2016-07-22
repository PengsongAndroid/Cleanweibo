package com.peng.weibo.ui.main;

import android.content.Context;

import com.peng.weibo.ui.BasePresenter;
import com.peng.weibo.ui.BaseView;

/**
 * Created by PS on 2016/7/22.
 */
public interface MainContract {

    interface View extends BaseView<Present> {
        Context getViewContext();
    }

    interface Present extends BasePresenter {
        void getUser();
    }

}
