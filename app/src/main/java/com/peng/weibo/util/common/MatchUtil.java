package com.peng.weibo.util.common;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PS on 2016/8/17.
 */
public class MatchUtil {

    private static final String weibo_from_regx = "(?<=\\<)\\S*(?=\\<)";

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
