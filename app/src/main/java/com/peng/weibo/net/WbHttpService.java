package com.peng.weibo.net;

import com.peng.weibo.data.entity.User;
import com.peng.weibo.data.test;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PS on 2016/7/12.
 */
public interface WbHttpService {

    @GET("/2/users/show.json")
    Observable<test> loginRequest(@Query("access_token") String token, @Query("uid") String uid);

}
