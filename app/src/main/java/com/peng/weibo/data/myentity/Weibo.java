package com.peng.weibo.data.myentity;

/**
 * Created by PS on 2016/8/17.
 */
public class Weibo {

    public int card_type;

    public String itemid;

    public String scheme;

    public String weibo_need;

    public WeiboContent mblog;

    public String show_type;

    /**
     * 底部的三个按钮 暂时没用到
     * */
    public String mblog_buttons;


    @Override
    public String toString() {
        return "Weibo{" +
                "card_type=" + card_type +
                ", itemid='" + itemid + '\'' +
                ", scheme='" + scheme + '\'' +
                ", weibo_need='" + weibo_need + '\'' +
                ", mblog=" + mblog.toString() +
                ", show_type='" + show_type + '\'' +
                ", mblog_buttons='" + mblog_buttons + '\'' +
                '}';
    }

}
