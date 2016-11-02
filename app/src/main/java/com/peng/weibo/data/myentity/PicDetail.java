package com.peng.weibo.data.myentity;

/**
 * Created by PS on 2016/10/31.
 *
 * 微博图片的详细信息
 *
 */
public class PicDetail {

    /**
     * bmiddle 显示在首页的缩略图360 webp
     * */
    public PicUrlDetail bmiddle;

    /**
     * large 点开的大图720    jpeg
     * */
    public PicUrlDetail large;

    /**
     * largest 应该是原图    jpeg
     * */
    public PicUrlDetail largest;

    /**
     * middleplus webp 480缩略图
     * */
    public PicUrlDetail middleplus;

    /**
     * original jpeg 原图
     * */
    public PicUrlDetail original;

    /**
     * thumbnail webp 缩略图180
     * */
    public PicUrlDetail thumbnail;

    /**
     * 这些字段我也不知道是干嘛的
     * */
    public int object_id;
    public int photo_tag;
    public int photo_status;
    public String pic_id;
    public String type;

    @Override
    public String toString() {
        return "PicDetail{" +
                "bmiddle=" + bmiddle +
                ", large=" + large +
                ", largest=" + largest +
                ", middleplus=" + middleplus +
                ", original=" + original +
                ", thumbnail=" + thumbnail +
                ", object_id=" + object_id +
                ", photo_tag=" + photo_tag +
                ", photo_status=" + photo_status +
                ", pic_id='" + pic_id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
