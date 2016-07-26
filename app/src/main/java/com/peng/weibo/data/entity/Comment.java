package com.peng.weibo.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 评论结构体
 *
 * Created by PS on 2016/7/26.
 */
public class Comment implements Parcelable {

    private static Pattern mpattern;
    private static Matcher mmatcher;

    /**
     * 评论创建时间
     */
    public String created_at;
    /**
     * 评论的 ID
     */
    public String id;
    /**
     * 评论的内容
     */
    public String text;
    /**
     * 评论的来源
     */
    public String source;
    /**
     * 评论作者的用户信息字段
     */
    public User user;
    /**
     * 评论的 MID
     */
    public String mid;
    /**
     * 字符串型的评论 ID
     */
    public String idstr;
    /**
     * 评论的微博信息字段
     */
    public Status status;
    /**
     * 评论来源评论，当本评论属于对另一评论的回复时返回此字段
     */
    public Comment reply_comment;

    public Comment() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.created_at);
        dest.writeString(this.id);
        dest.writeString(this.text);
        dest.writeString(this.source);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.mid);
        dest.writeString(this.idstr);
        dest.writeParcelable(this.status, flags);
        dest.writeParcelable(this.reply_comment, flags);
    }

    protected Comment(Parcel in) {
        this.created_at = in.readString();
        this.id = in.readString();
        this.text = in.readString();
        this.source = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.mid = in.readString();
        this.idstr = in.readString();
        this.status = in.readParcelable(Status.class.getClassLoader());
        this.reply_comment = in.readParcelable(Comment.class.getClassLoader());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}

