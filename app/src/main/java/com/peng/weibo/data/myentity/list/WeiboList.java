package com.peng.weibo.data.myentity.list;

import com.peng.weibo.data.myentity.CardListInfo;
import com.peng.weibo.data.myentity.Weibo;

import java.util.ArrayList;

/**
 * Created by PS on 2016/8/17.
 */
public class WeiboList {

    public CardListInfo cardlistInfo;

    public ArrayList<Weibo> cards = new ArrayList<Weibo>();

    @Override
    public String toString() {
        return "WeiboList{" +
                "cardlistInfo=" + cardlistInfo.toString() +
                ", cards=" + cards.toString() +
                '}';
    }
}
