package com.peng.weibo.ui.main.homePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peng.weibo.R;

/**
 * Created by PS on 2016/7/19.
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    private int num;

    public HomePageAdapter(Context context, int num){
        this.num = num;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weibo_status_item, parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.titleTextView.setText("");
    }

    @Override
    public int getItemCount() {
        return num;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
