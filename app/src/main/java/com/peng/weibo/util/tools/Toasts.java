package com.peng.weibo.util.tools;

import android.widget.Toast;

import com.peng.weibo.MyApplication;

/**
 * Created by PS on 2016/7/14.
 */
public class Toasts {
    private volatile static Toasts instance;
    private static Toast mToast;

    private Toasts() {
        instance = getInstance();
    }

    public static Toasts getInstance() {
        if (instance == null) {
            synchronized (Toasts.class) {
                if (instance == null) {
                    instance = new Toasts();
                }
            }
        }
        return instance;
    }

    public static void showText(String msg) {
        if(mToast == null) {
            mToast = Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
