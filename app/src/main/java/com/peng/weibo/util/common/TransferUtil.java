package com.peng.weibo.util.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.peng.weibo.R;
import com.peng.weibo.ui.main.homePage.HomePageFragment;
import com.peng.weibo.util.tools.DensityUtil;
import com.peng.weibo.widget.emojitextview.ClickableImageSpan;
import com.peng.weibo.widget.emojitextview.ClickableMovementMethod;
import com.peng.weibo.widget.emojitextview.Emoticons;
import com.peng.weibo.widget.emojitextview.WeiBoContentClickableSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PS on 2016/8/17.
 */
public class TransferUtil {

    private static final String weibo_from_regx = "(?<=\\<)\\S*(?=\\<)";

    private static final String AT = "@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}";// @人
    private static final String TOPIC = "#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#";// ##话题
    private static final String URL = "http://[a-zA-Z0-9+&@#/%?=~_\\-|!:,\\.;]*[a-zA-Z0-9+&@#/%=~_|]";// url
    private static final String EMOJI = "\\[(\\S+?)\\]";//emoji 表情

    private static final String ALL = "(" + AT + ")" + "|" + "(" + TOPIC + ")" + "|" + "(" + URL + ")" + "|" + "(" + EMOJI + ")";

    public static SpannableStringBuilder getWeiBoContent(String source, final Context context, TextView textView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(source);
        //设置正则
        Pattern pattern = Pattern.compile(ALL);
        Matcher matcher = pattern.matcher(spannableStringBuilder);

        if (matcher.find()) {
            if (!(textView instanceof EditText)) {
                textView.setMovementMethod(ClickableMovementMethod.getInstance());
                textView.setFocusable(false);
                textView.setClickable(false);
                textView.setLongClickable(false);
            }
            matcher.reset();
        }

        while (matcher.find()) {
            final String at = matcher.group(1);
            final String topic = matcher.group(2);
            final String url = matcher.group(3);
            final String emoji = matcher.group(4);

            //处理@用户
            if (at != null) {
                int start = matcher.start(1);
                int end = start + at.length();
                WeiBoContentClickableSpan myClickableSpan = new WeiBoContentClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        Intent intent = new Intent(context, HomePageFragment.class);
                        String screen_name = at.substring(1);
                        intent.putExtra("screenName", screen_name);
                        context.startActivity(intent);
                    }
                };
                spannableStringBuilder.setSpan(myClickableSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //处理##话题
            if (topic != null) {
                int start = matcher.start(2);
                int end = start + topic.length();
                WeiBoContentClickableSpan clickableSpan = new WeiBoContentClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, "点击了话题：" + topic, Toast.LENGTH_LONG).show();
                    }
                };
                spannableStringBuilder.setSpan(clickableSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }

            // 处理url地址
            if (url != null) {
                int start = matcher.start(3);
                int end = start + url.length();
                Drawable websiteDrawable = context.getResources().getDrawable(R.mipmap.button_web);
                websiteDrawable.setBounds(0, 0, websiteDrawable.getIntrinsicWidth(), websiteDrawable.getIntrinsicHeight());
                ClickableImageSpan imageSpan = new ClickableImageSpan(websiteDrawable, ImageSpan.ALIGN_BOTTOM) {
                    @Override
                    public void onClick(View widget) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(browserIntent);
                    }

                    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
                        Drawable b = getDrawable();
                        canvas.save();
                        int transY = bottom - b.getBounds().bottom;
                        transY -= paint.getFontMetricsInt().descent / 2;
                        canvas.translate(x, transY);
                        b.draw(canvas);
                        canvas.restore();
                    }

                };

                spannableStringBuilder.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //emoji
            if (emoji != null) {
                int start = matcher.start(4);
                int end = start + emoji.length();
                String imgName = Emoticons.getImgName(emoji);
                if (!TextUtils.isEmpty(imgName)) {
                    int resId = context.getResources().getIdentifier(imgName, "mipmap", context.getPackageName());
                    Drawable emojiDrawable = context.getResources().getDrawable(resId);
                    if (emojiDrawable != null) {
                        emojiDrawable.setBounds(0, 0, DensityUtil.sp2px(context, 17), DensityUtil.sp2px(context, 17));
                        ImageSpan imageSpan = new ImageSpan(emojiDrawable, ImageSpan.ALIGN_BOTTOM) {
                            public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
                                Drawable b = getDrawable();
                                canvas.save();
                                int transY = bottom - b.getBounds().bottom;
                                transY -= paint.getFontMetricsInt().descent / 2;
                                canvas.translate(x, transY);
                                b.draw(canvas);
                                canvas.restore();
                            }
                        };
                        spannableStringBuilder.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }

        }
        return spannableStringBuilder;
    }

    /**
     * 匹配微博来源中间的内容
     *
     * @param patternContent 内容 patternCoder 正则表达式
     * @return
     */
    public static String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile(weibo_from_regx);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static boolean match(String s, String s1) {
        while (s1 == null || s == null)
            return false;
        return Pattern.compile(s).matcher(s1).matches();
    }


}
