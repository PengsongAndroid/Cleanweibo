package com.peng.weibo.ui.main.homePage;

import android.content.Context;

import com.peng.weibo.data.entity.list.StatusList;
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
        void setData(StatusList data);
    }

    interface Present extends BasePresenter{
        void getHomeWb(long since_id, long max_id, int count, int page, int base_app, int featureType,
                       int trim_user);
    }

}
