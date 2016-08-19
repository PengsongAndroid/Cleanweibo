package com.peng.weibo.data.myentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.peng.weibo.data.entity.Geo;
import com.peng.weibo.data.entity.Visible;

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

    /**
     * 转发数
     */
    public int reposts_count;
    /**
     * 评论数
     */
    public int comments_count;
    /**
     * 赞
     */
    public int attitudes_count;
    /**
     * 是否是长文
     */
    public boolean isLongText;
    /**
     * 暂未支持
     */
    public int mlevel;
    /**
     * 微博的可见性及指定可见分组信息。该 object 中 type 取值， 0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；
     * list_id为分组的组号
     */
    public Visible visible;
    /**
     * 微博来源是否允许点击，如果允许
     */
    public int source_allowclick;

    /**
     * 热门微博标签
     * */
    public ArrayList<Tag> hot_weibo_tags = new ArrayList<Tag>();

    /**
     * 微博配图 图片地址合集
     * */
    public String pic_infos;

    public Map<String, PicUrlList> pic_infos_map;

    public String page_info;

    public String url_struct;

    public ArrayList<Tag> tag_struct = new ArrayList<Tag>();

    /**
     * key是从pic_ids中拿到的
     * */
    class PicUrlList{
        public PicUrlContent thumbnail;
        public PicUrlContent bmiddle;
        public PicUrlContent middleplus;
        public PicUrlContent large;
        public PicUrlContent original;
        public PicUrlContent largest;
        public String object_id;
        public String pic_id;
        public String photo_tag;
        public String type;
        public String pic_status;
    }

    class PicUrlContent{
        public String url;
        public String width;
        public String height;
        public String cut_type;
        public String type;
    }

    //因为照片的key是动态的 所以需要自己再去做解析
    public static Map<String, PicUrlList> getMapFromJson(String json){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            Iterator<String> it = jsonObject.keys();
            Map<String, PicUrlList> map = new HashMap<String, PicUrlList>();
            while (it.hasNext()){
                map.put(it.next(), gson.fromJson(jsonObject.get(it.next()).toString(), PicUrlList.class));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "WeiboContent{" +
                "created_at='" + created_at + '\'' +
                ", id='" + id + '\'' +
                ", mid='" + mid + '\'' +
                ", idstr='" + idstr + '\'' +
                ", text='" + text + '\'' +
                ", textLength='" + textLength + '\'' +
                ", source='" + source + '\'' +
                ", pic_ids=" + pic_ids +
                ", thumbnail_pic='" + thumbnail_pic + '\'' +
                ", bmiddle_pic='" + bmiddle_pic + '\'' +
                ", original_pic='" + original_pic + '\'' +
                ", geo=" + geo +
                ", user=" + user +
                ", reposts_count=" + reposts_count +
                ", comments_count=" + comments_count +
                ", attitudes_count=" + attitudes_count +
                ", isLongText=" + isLongText +
                ", mlevel=" + mlevel +
                ", visible=" + visible +
                ", source_allowclick=" + source_allowclick +
                ", hot_weibo_tags=" + hot_weibo_tags +
                ", pic_infos='" + pic_infos + '\'' +
                ", pic_infos_map=" + pic_infos_map +
                ", page_info='" + page_info + '\'' +
                ", url_struct='" + url_struct + '\'' +
                ", tag_struct=" + tag_struct +
                '}';
    }
}
