package com.peng.weibo.util.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.peng.weibo.MyApplication;


public class HardWareUtils {

	/** 网络是否可用*/
	public static boolean networkIsAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						connectivity = null;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean networkIsAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) MyApplication.getAppContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						connectivity=null;
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
     * 获取手机系统SDK版本
     *
     * @return 如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }
    
    /**
     * 获取设备的可用内存大小
     *
     * @param context 应用上下文对象context
     * @return 当前内存大小
     */
    public static int getDeviceUsableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // 返回当前系统的可用内存
        return (int) (mi.availMem / (1024 * 1024));
    }
    
    public static void setSystemBarColor(Activity context) {
    	if (HardWareUtils.getSDKVersion() >= Build.VERSION_CODES.KITKAT) {
			Window win = context.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		   // 创建状态栏的管理实例
//		   SystemBarTintManager tintManager = new SystemBarTintManager(context);
//		   // 激活状态栏设置
//		   tintManager.setStatusBarTintEnabled(true);
//		   // 激活导航栏设置
//		   // tintManager.setNavigationBarTintEnabled(true);
//		   // 设置一个颜色给系统栏
//		   tintManager.setTintColor(context.getResources().getColor(R.color.background));
		}
    }
    
}
