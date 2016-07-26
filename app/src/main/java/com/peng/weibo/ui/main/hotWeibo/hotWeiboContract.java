package com.peng.weibo.ui.main.hotWeibo;

import android.content.Context;

import com.peng.weibo.ui.BasePresenter;
import com.peng.weibo.ui.BaseView;

/**
 * Created by PS on 2016/7/26.
 */
public interface hotWeiboContract {

    interface View extends BaseView<Present>{
        Context getViewContext();
        void setHotWeiboData();
        void startRefresh();
        void stopRefresh();
    }

    interface Present extends BasePresenter{
        void getHotWeibo(int count, int page, boolean base_app);
    }

}
