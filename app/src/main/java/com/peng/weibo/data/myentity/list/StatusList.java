package com.peng.weibo.data.myentity.list;

import com.peng.weibo.data.myentity.Status;

import java.util.ArrayList;

/**
 * Created by PS on 2016/10/31.
 */
public class StatusList {

    /**
     * 是一个标识
     * */
    public String gsid;

    /**
     * 更新的微博数
     * */
    public int num;

    /**
     * 提示信息
     * */
    public String remind_text;

    /**
     * 微博列表
     * */
    public ArrayList<Status> statuses;

    @Override
    public String toString() {
        return "StatusList{" +
                "gsid='" + gsid + '\'' +
                ", num=" + num +
                ", remind_text='" + remind_text + '\'' +
                ", statuses=" + statuses.toString() +
                '}';
    }
}
