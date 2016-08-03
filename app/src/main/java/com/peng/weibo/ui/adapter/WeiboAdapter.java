package com.peng.weibo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PS on 2016/8/1.
 */
public class WeiboAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected final List<T> mData;
    protected final Context mContext;
    protected LayoutInflater mInflater;

    public WeiboAdapter(Context context, List<T> list){
        mData = (list == null)? list:new ArrayList<T>();
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
