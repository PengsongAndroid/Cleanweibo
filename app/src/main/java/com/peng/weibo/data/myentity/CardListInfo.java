package com.peng.weibo.data.myentity;

/**
 * Created by PS on 2016/8/17.
 */
public class CardListInfo {

    /**
     *
     "v_p":"27",
     "statistics_from":"hotweibo",
     "containerid":"102803",
     "title_top":"热门微博",
     "show_style":1,
     "total":300,
     "can_shared":1,
     "since_id":1,
     "cardlist_title":"",
     "desc":"",
     "cardlist_head_cards":[],
     "page_type":"03",
     "background":"",
     "cardlist_menus":[]
     *
     * */
    public String v_p;
    public String statistics_from;
    public String containerid;
    public String title_top;
    public String show_style;
    public String can_shared;
    public String since_id;

    @Override
    public String toString() {
        return "CardListInfo{" +
                "v_p='" + v_p + '\'' +
                ", statistics_from='" + statistics_from + '\'' +
                ", containerid='" + containerid + '\'' +
                ", title_top='" + title_top + '\'' +
                ", show_style='" + show_style + '\'' +
                ", can_shared='" + can_shared + '\'' +
                ", since_id='" + since_id + '\'' +
                ", cardlist_title='" + cardlist_title + '\'' +
                ", desc='" + desc + '\'' +
                ", cardlist_head_cards='" + cardlist_head_cards + '\'' +
                ", page_type='" + page_type + '\'' +
                ", background='" + background + '\'' +
                ", cardlist_menus='" + cardlist_menus + '\'' +
                '}';
    }

    public String cardlist_title;
    public String desc;
    public String cardlist_head_cards;
    public String page_type;
    public String background;
    public String cardlist_menus;

}
