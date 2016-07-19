package com.peng.weibo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.peng.weibo.R;

/**
 * Created by PS on 2016/7/19.
 */
public class TestActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_tab1);
    }
}
