package com.peng.weibo.data.myentity;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.peng.weibo.data.entity.Geo;
import com.peng.weibo.data.entity.Visible;

/**
 * Created by PS on 2016/7/14.
 *
 * 微博结构体。
 *
 */
public class Status implements Parcelable {

    /**
     * 微博创建时间
     */
    public String created_at;
    /**
     * 微博ID
     */
    public String id;
    /**
     * 微博MID
     */
    public String mid;
    /**
     * 字符串型的微博ID
     */
    public String idstr;
    /**
     * 微博文本内容长度
     */
    public int textLength;

    /**
     * 微博发表距离现在的时间
     * */
    public String timestamp_text;
    /**
     * 微博信息内容
     */
    public String text;
    /**
     * 是否是超过140个字的长微博
     */
    public boolean isLongText;
    /**
     * 微博来源类型
     */
    public int source_type;
    /**
     * 微博来源
     */
//    public String source;

    /**
     * 来源是否能点击
     * */
    public int source_allowclick;

    public String scheme;

    /**
     * 是否已收藏，true：是，false：否
     */
    public boolean favorited;

    /**
     * 地理信息字段
     */
//    public Geo geo;
    /**
     * 微博作者的用户信息字段
     */
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
     * 表态数
     */
    public int attitudes_count;
    /**
     * 是否点赞
     */
    public int attitudes_status;
    /**
     * 微博的可见性及指定可见分组信息。该 object 中 type 取值， 0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；
     * list_id为分组的组号
     */
//    public Visible visible;

    /**
     * 微博图片key用于取出pic_infos
     */
    public ArrayList<String> pic_ids;

    /**
     * 微博图片详细
     */
//    public String pic_infos;

    protected Status(Parcel in) {
        created_at = in.readString();
        id = in.readString();
        mid = in.readString();
        idstr = in.readString();
        textLength = in.readInt();
        timestamp_text = in.readString();
        text = in.readString();
        isLongText = in.readByte() != 0;
        source_type = in.readInt();
//        source = in.readString();
        source_allowclick = in.readInt();
        scheme = in.readString();
        favorited = in.readByte() != 0;
//        geo = in.readParcelable(Geo.class.getClassLoader());
        reposts_count = in.readInt();
        comments_count = in.readInt();
        attitudes_count = in.readInt();
        attitudes_status = in.readInt();
//        visible = in.readParcelable(Visible.class.getClassLoader());
        pic_ids = in.createStringArrayList();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    @Override
    public String toString() {
        return "Status{" +
                "created_at='" + created_at + '\'' +
                ", id='" + id + '\'' +
                ", mid='" + mid + '\'' +
                ", idstr='" + idstr + '\'' +
                ", textLength=" + textLength +
                ", timestamp_text='" + timestamp_text + '\'' +
                ", text='" + text + '\'' +
                ", isLongText=" + isLongText +
                ", source_type=" + source_type +
//                ", source='" + source + '\'' +
                ", source_allowclick=" + source_allowclick +
                ", scheme='" + scheme + '\'' +
                ", favorited=" + favorited +
//                ", geo=" + geo +
//                ", user=" + user +
                ", reposts_count=" + reposts_count +
                ", comments_count=" + comments_count +
                ", attitudes_count=" + attitudes_count +
                ", attitudes_status=" + attitudes_status +
//                ", visible=" + visible +
                ", pic_ids=" + pic_ids.toString() +
//                ", pic_infos=" + pic_infos.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_at);
        dest.writeString(id);
        dest.writeString(mid);
        dest.writeString(idstr);
        dest.writeInt(textLength);
        dest.writeString(timestamp_text);
        dest.writeString(text);
        dest.writeByte((byte) (isLongText ? 1 : 0));
        dest.writeInt(source_type);
//        dest.writeString(source);
        dest.writeInt(source_allowclick);
        dest.writeString(scheme);
        dest.writeByte((byte) (favorited ? 1 : 0));
//        dest.writeParcelable(geo, flags);
        dest.writeInt(reposts_count);
        dest.writeInt(comments_count);
        dest.writeInt(attitudes_count);
        dest.writeInt(attitudes_status);
//        dest.writeParcelable(visible, flags);
        dest.writeStringList(pic_ids);
    }
}
