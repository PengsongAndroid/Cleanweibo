package com.peng.weibo.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peng.weibo.R;

import butterknife.ButterKnife;

/**
 * Created by PS on 2016/7/18.
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        // Setup list
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tab1);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getNumColumns(),
//                StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setAdapter(new NotesAdapter(getActivity(), getNumItems()));
        initView(view);
        return view;
    }

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

}
