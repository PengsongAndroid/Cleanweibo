package com.peng.weibo.widget.emojitextview;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.peng.weibo.R;

/**
 * Created by PS on 2016/8/2.
 */
public class WeiBoContentClickableSpan  extends ClickableSpan {

    private Context mContext;

    public WeiBoContentClickableSpan(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View widget) {
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(mContext.getResources().getColor(R.color.weibo_content_keyword));
        ds.setUnderlineText(false);
    }


}