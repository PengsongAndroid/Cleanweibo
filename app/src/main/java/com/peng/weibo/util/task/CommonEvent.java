package com.peng.weibo.util.task;

/**
 * Created by PS on 2016/7/20.
 */
public class CommonEvent {

    /**
     * 消息的值 类似what
     */
    private int what = 0;

    /**
     * 消息的内容 类似 msg
     */
    private Object obj;

    public CommonEvent(int what) {
        this.what = what;
    }

    public CommonEvent(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public int getWhat() {
        return what;
    }

    public Object getObject() {
        return obj;
    }

}
