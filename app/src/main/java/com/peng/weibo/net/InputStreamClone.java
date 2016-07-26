package com.peng.weibo.net;

/**
 * Created by PS on 2016/7/26.
 */
public class InputStreamClone implements Cloneable{

    protected Object clone() throws CloneNotSupportedException {
        return (InputStreamClone)super.clone();
    }
}
