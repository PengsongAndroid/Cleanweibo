package com.peng.weibo.util.common;

import android.text.TextUtils;

/**
 * Created by PS on 2016/7/12.
 */
public interface Constants {

    /**  登录后获取到的token值 后面的请求都需要用到   */
    public static String APP_Token = "";

    /**  登录后获取到的token值的过期时间   */
    public static String APP_Token_Date = "";

    /** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
    public static final String APP_KEY      = "2844976289";

    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     *
     * <p>
     * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
     * 但是没有定义将无法使用 SDK 认证登录。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * </p>
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    /**
     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
     * 选择赋予应用的功能。
     *
     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
     * 使用权限，高级权限需要进行申请。
     *
     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     *
     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    public static final String AppSecret = "a7827395503969d48bbaf3b799844877";
    public static final String PackageName = "com.peng.weibo";


    public static final String authurl = "https://open.weibo.cn/oauth2/authorize" + "?" + "client_id=" + Constants.APP_KEY
            + "&response_type=token&redirect_uri=" + Constants.REDIRECT_URL
            + "&key_hash=" + Constants.AppSecret + (TextUtils.isEmpty(Constants.PackageName) ? "" : "&packagename=" + Constants.PackageName)
            + "&display=mobile" + "&scope=" + Constants.SCOPE;

    //图片形状
    public static final String IMGSIZE_HORIZONTAL = "1"; //水平方向的长方形尺寸
    public static final String IMGSIZE_VERTICAL = "2";//竖直方向的长方形尺寸
    public static final String IMGSIZE_SQUARE = "3";//正方形尺寸

    //首页分组
    public static final long GROUP_TYPE_ALL = 0;//全部微博
    public static final long GROUP_TYPE_FRIENDS_CIRCLE = 1;//好友圈


    //评论页分组
    public static final int GROUP_COMMENT_TYPE_ALL = 0x12;//全部评论
    public static final int GROUP_COMMENT_TYPE_FRIENDS = 0x13;//关注的人
    public static final int GROUP_COMMENT_TYPE_BYME = 0x14;//我发出的

    //@我的分组
    public static final int GROUP_RETWEET_TYPE_ALL = 0x15;//所有微博
    public static final int GROUP_RETWEET_TYPE_FRIENDS = 0x16;//关注人的微博
    public static final int GROUP_RETWEET_TYPE_ORIGINWEIBO = 0x17;//原创微博
    public static final int GROUP_RETWEET_TYPE_ALLCOMMENT = 0x18;//所有评论
    public static final int GROUP_RETWEET_TYPE_FRIEDNSCOMMENT = 0x19;//关注人的评论

    //我的微博
    public static final int GROUP_MYWEIBO_TYPE_ALL = 0;
    public static final int GROUP_MYWEIBO_TYPE_ORIGIN = 1;
    public static final int GROUP_MYWEIBO_TYPE_PICWEIBO = 2;

    //删除微博的类型
    public static final String DELETE_WEIBO_TYPE1 = "全部微博";

    public static final String DELETE_WEIBO_TYPE2 = "我的全部微博";
    public static final String DELETE_WEIBO_TYPE3 = "我的原创微博";
    public static final String DELETE_WEIBO_TYPE4 = "我的图片微博";
    public static final String DELETE_WEIBO_TYPE5 = "好友圈";
    public static final String DELETE_WEIBO_TYPE6 = "我的收藏";




}
