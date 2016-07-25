package com.peng.weibo.ui.main;

import android.content.Context;
import android.graphics.Bitmap;

import com.peng.weibo.data.entity.User;
import com.peng.weibo.data.test;
import com.peng.weibo.ui.BasePresenter;
import com.peng.weibo.ui.BaseView;

/**
 * Created by PS on 2016/7/22.
 */
public interface MainContract {

    interface View extends BaseView<Present> {
        Context getViewContext();
        void setDrawerData(test user);
        void setHeadPic(Bitmap bitmap);
    }

    interface Present extends BasePresenter {
        void getUser();
        void loadHeadPic(String url);
    }

}
