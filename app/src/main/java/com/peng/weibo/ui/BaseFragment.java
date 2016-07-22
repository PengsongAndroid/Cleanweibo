package com.peng.weibo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

import com.peng.weibo.R;
import com.peng.weibo.util.tools.Logs;

/**
 * Created by PS on 2016/7/18.
 */
public abstract class BaseFragment extends Fragment{

    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    /** 真正要显示的View是否已经被初始化 **/
    private boolean isInit = false;

    private Context mContext;

    private View contentView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logs.d(TAG, "onAttach context");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        Logs.d(TAG, "onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //是否可见
        if (isVisibleToUser){
            if (contentView == null){
                isInit = true;
                return;
            }
            if (isInit){
                Logs.d(TAG, "setUserVisibleHint init");
            } else {
                Logs.d(TAG, "setUserVisibleHint no init");
                startRefresh();
                isInit = true;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logs.d(TAG, "onCreateView");
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        contentView = view;
        return view;
    }

    public abstract void startRefresh();

    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logs.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logs.d(TAG, "onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Logs.d(TAG, "onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logs.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logs.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logs.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logs.d(TAG, "onDetach");
    }
}
