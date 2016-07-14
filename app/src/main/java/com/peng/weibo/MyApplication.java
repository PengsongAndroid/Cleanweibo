package com.peng.weibo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by PS on 2016/7/12.
 */
public class MyApplication extends Application{

    public static final String TAG = "MyApplication";

    private static MyApplication instance = null;

    private static Context g_context = null;

    private static List<Activity> activityList = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        g_context = getApplicationContext();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return g_context;
    }

    /**
     * Activity关闭时，删除Activity列表中的Activity对象
     */
    public void removeActivity(Activity a) {
        activityList.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象
     */
    public void addActivity(Activity a) {
        activityList.add(a);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public static void finishActivity() {
        for (Activity activity : activityList) {
            if (null != activity) {
                activity.finish();
            }
        }
        // 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
