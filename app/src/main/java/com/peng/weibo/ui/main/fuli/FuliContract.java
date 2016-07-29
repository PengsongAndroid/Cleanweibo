package com.peng.weibo.ui.main.fuli;

import com.peng.weibo.ui.BasePresenter;
import com.peng.weibo.ui.BaseView;

/**
 * Created by PS on 2016/7/27.
 */
public interface FuliContract {

    interface View extends BaseView<Present>{

    }

    interface Present extends BasePresenter{
        void getFuli();
    }

}
