package com.peng.weibo.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.peng.weibo.MyApplication;
import com.peng.weibo.R;
import com.peng.weibo.util.common.HardWareUtils;

/**
 * Created by PS on 2016/7/12.
 */
public class BaseActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        // 状态栏变色 4.4 之后才支持变色
        if (HardWareUtils.getSDKVersion() >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            // 创建状态栏的管理实例
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            // 激活状态栏设置
//            tintManager.setStatusBarTintEnabled(true);
//            // 激活导航栏设置
//            // tintManager.setNavigationBarTintEnabled(true);
//            // 设置一个颜色给系统栏
//            tintManager.setTintColor(getResources().getColor(R.color.background));
        }
    }
}
