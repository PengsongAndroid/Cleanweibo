package com.peng.weibo.data.myentity;

import com.peng.weibo.data.entity.Geo;

import java.util.ArrayList;

/**
 * Created by PS on 2016/8/18.
 */
public class WeiboContent {

    /**
     * 创建时间
     * */
    public String created_at;

    public String id;
    public String mid;
    public String idstr;

    /**
     * 微博内容
     * */
    public String text;

    /**
     * 内容长度
     * */
    public String textLength;

    /**
     * 来源  <a href="http://app.weibo.com/t/feed/6vtZb0" rel="nofollow">微博 weibo.com</a>
     * */
    public String source;

    /**
     * 微博内容图片
     * */
    public ArrayList<String> pic_ids = new ArrayList<String>();

    /**
     * 缩略图
     * */
    public String thumbnail_pic;

    public String bmiddle_pic;

    public String original_pic;

    /**
     * 地理位置信息
     * */
    public Geo geo;

    /**
     * 用户信息
     * */
    public User user;



}
