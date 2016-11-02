package com.peng.weibo.data.myentity;

/**
 * Created by PS on 2016/10/31.
 */
public class PicUrlDetail {

    public int cut_type;

    /**
     * 图片尺寸
     * */
    public int height;
    public int width;

    /**
     * 图片url
     * */
    public String url;

    /**
     * 图片类型 一般为WEBP JPEG
     * */
    public String type;

    @Override
    public String toString() {
        return "PicUrlDetail{" +
                "cut_type=" + cut_type +
                ", height=" + height +
                ", width=" + width +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
