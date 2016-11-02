package com.peng.weibo.net.ps;

import com.peng.weibo.data.myentity.list.StatusList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by PS on 2016/10/31.
 */
public interface PsHttpService {

    /**
     * 获取当前登录用户及其所关注用户的最新微博。
     *
     * 暂时不带参数
     */
    @GET("/getHomeWeibo")
    @Headers("Connection:close")
    Observable<StatusList> getHomeWb();
}
