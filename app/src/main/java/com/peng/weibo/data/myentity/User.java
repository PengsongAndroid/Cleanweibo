package com.peng.weibo.data.myentity;

import com.peng.weibo.data.entity.Status;

/**
 * Created by PS on 2016/8/18.
 */
public class User {

    /**
     * 用户UID（int64）
     */
    public String id;
    /**
     * 字符串型的用户 UID
     */
    public String idstr;
    /**
     * 用户昵称
     */
    public String screen_name;
    /**
     * 友好显示名称
     */
    public String name;
    /**
     * 用户所在省级ID
     */
    public int province;
    /**
     * 用户所在城市ID
     */
    public int city;
    /**
     * 用户所在地
     */
    public String location;
    /**
     * 用户个人描述
     */
    public String description;
    /**
     * 用户博客地址
     */
    public String url;
    /**
     * 用户头像地址，50×50像素
     */
    public String profile_image_url;

    /**
     * 用户的个性化背景
     */
    public String cover_image;

    /**
     * 用户的个性化背景(手机)
     */
    public String cover_image_phone;

    /**
     * 用户的微博统一URL地址
     */
    public String profile_url;

    /**
     * 用户的个性化域名
     */
    public String domain;
    /**
     * 用户的微号
     */
    public String weihao;
    /**
     * 性别，m：男、f：女、n：未知
     */
    public String gender;
    /**
     * 粉丝数
     */
    public int followers_count;
    /**
     * 关注数
     */
    public int friends_count;

    /**
     * 关注数
     */
    public int pagefriends_count;

    /**
     * 微博数
     */
    public int statuses_count;
    /**
     * 收藏数
     */
    public int favourites_count;
    /**
     * 用户创建（注册）时间
     */
    public String created_at;
    /**
     * 暂未支持
     */
    public boolean following;
    /**
     * 是否允许所有人给我发私信，true：是，false：否
     */
    public boolean allow_all_act_msg;
    /**
     * 是否允许标识用户的地理位置，true：是，false：否
     */
    public boolean geo_enabled;
    /**
     * 是否是微博认证用户，即加V用户，true：是，false：否
     */
    public boolean verified;
    /**
     * 暂未支持
     */
    public int verified_type;
    /**
     * 用户备注信息，只有在查询用户关系时才返回此字段
     */
    public String remark;
    /**
     * 用户的最近一条微博信息字段
     */
    public Status status;
    /**
     * 是否允许所有人对我的微博进行评论，true：是，false：否
     */
    public boolean allow_all_comment;
    /**
     * 用户大头像地址
     */
    public String avatar_large;
    /**
     * 用户高清大头像地址
     */
    public String avatar_hd;
    /**
     * 认证原因
     */
    public String verified_reason;
    /**
     * 该用户是否关注当前登录用户，true：是，false：否
     */
    public boolean follow_me;
    /**
     * 用户的在线状态，0：不在线、1：在线
     */
    public int online_status;
    /**
     * 用户的互粉数
     */
    public int bi_followers_count;
    /**
     * 用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
     */
    public String lang;

    /**
     *
     *  注： 还有很多字段暂时没有用到 所以没有取出来 需要的时候再进行修改
     *
     * "user":{
     "id":1856404484,
     "idstr":"1856404484",
     "class":1,
     "screen_name":"凤凰科技",
     "name":"凤凰科技",
     "province":"11",
     "city":"5",
     "location":"北京 朝阳区",
     "description":"凤凰科技，聚焦最新科技资讯和产品，提供深入独到趋势分析，欢迎关注微信账号：凤凰科技（ifeng_tech）",
     "url":"http://tech.ifeng.com/",
     "profile_image_url":"http://tva3.sinaimg.cn/crop.0.0.180.180.50/6ea67c04jw1e8qgp5bmzyj2050050aa8.jpg",
     "cover_image":"http://ww2.sinaimg.cn/crop.0.0.980.300/6ea67c04jw1e8ys87vos3j20r808cq63.jpg",
     "cover_image_phone":"http://ww3.sinaimg.cn/crop.0.0.640.640.640/6ce2240djw1e9oafaeu2vj20hs0hsn0f.jpg",
     "profile_url":"ifengtechdigi",
     "domain":"ifengtechdigi",
     "weihao":"",
     "gender":"m",
     "followers_count":395122,
     "friends_count":411,
     "pagefriends_count":3,
     "statuses_count":26199,
     "favourites_count":165,
     "created_at":"Sat Oct 16 16:50:26 +0800 2010",
     "following":false,
     "allow_all_act_msg":false,
     "geo_enabled":false,
     "verified":true,
     "verified_type":3,
     "remark":"",
     "ptype":0,
     "allow_all_comment":true,
     "avatar_large":"http://tva3.sinaimg.cn/crop.0.0.180.180.180/6ea67c04jw1e8qgp5bmzyj2050050aa8.jpg",
     "avatar_hd":"http://tva3.sinaimg.cn/crop.0.0.180.180.1024/6ea67c04jw1e8qgp5bmzyj2050050aa8.jpg",
     "verified_reason":"凤凰网科技频道官方微博",
     "verified_trade":"",
     "verified_reason_url":"",
     "verified_source":"",
     "verified_source_url":"",
     "verified_state":0,
     "verified_level":3,
     "verified_type_ext":0,
     "verified_reason_modified":"",
     "verified_contact_name":"",
     "verified_contact_email":"",
     "verified_contact_mobile":"",
     "follow_me":false,
     "online_status":0,
     "bi_followers_count":280,
     "lang":"zh-cn",
     "star":0,
     "mbtype":12,
     "mbrank":4,
     "block_word":0,
     "block_app":1,
     "level":2,
     "type":1,
     "ulevel":0,
     "badge":{
     "uc_domain":0,
     "enterprise":1,
     "anniversary":0,
     "taobao":0,
     "travel2013":1,
     "gongyi":0,
     "gongyi_level":0,
     "bind_taobao":0,
     "hongbao_2014":0,
     "suishoupai_2014":0,
     "dailv":0,
     "zongyiji":0,
     "vip_activity1":0,
     "unread_pool":0,
     "daiyan":0,
     "ali_1688":0,
     "vip_activity2":0,
     "suishoupai_2016":0,
     "fools_day_2016":0,
     "uefa_euro_2016":0,
     "super_star_2016":0,
     "unread_pool_ext":0,
     "self_media":0,
     "olympic_2016":0,
     "dzwbqlx_2016":1
     },
     "badge_top":"",
     "has_ability_tag":1,
     "extend":{
     "privacy":{
     "mobile":0
     },
     "mbprivilege":"0000000000000000000000000000000000000000000000000000000000c00208"
     },
     "dianping":"stock",
     "credit_score":80,
     "user_ability":0,
     "urank":36,
     "icons":[
     {
     "url":"http://u1.sinaimg.cn/upload/2014/11/04/common_icon_membership_level4.png"
     },
     {
     "url":"https://h5.sinaimg.cn/upload/2016/07/21/172/feed_icon_travel.png",
     "scheme":""
     }
     ]
     }
     *
     * */
}
