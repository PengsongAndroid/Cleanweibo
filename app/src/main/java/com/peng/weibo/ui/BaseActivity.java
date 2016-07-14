package com.peng.weibo.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.peng.weibo.MyApplication;
import com.peng.weibo.util.common.Logs;
import com.peng.weibo.util.common.Toasts;

import butterknife.ButterKnife;

/**
 * Created by PS on 2016/7/12.
 */
public abstract class BaseActivity extends Activity {

    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    /** 是否沉浸状态栏 **/
    private boolean isSetStatusBar = true;

    /** 是否允许全屏 **/
    private boolean mAllowFullScreen = false;

    /** 是否禁止旋转屏幕 **/
    private boolean isAllowScreenRoate = false;

    /** 返回键退出 **/
    private boolean isBackExit = true;

    /**  返回间隔时间   */
    private long exitTime;

    /** 当前Activity渲染的视图View **/
    private View mContextView = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        } else {
            mContextView = mView;
        }
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(mContextView);
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        ButterKnife.bind(this);
        initView(mContextView);
        MyApplication.getInstance().addActivity(this);
    }

    /** 绑定视图 */
    public abstract View bindView();

    /** 绑定布局 */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /** 沉浸状态栏 */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void back(View view){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isBackExit) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toasts.showText("再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                } else {
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logs.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logs.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logs.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logs.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logs.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logs.d(TAG, "onDestroy()");
    }

}
