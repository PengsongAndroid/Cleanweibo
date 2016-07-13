package com.peng.weibo.net;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PS on 2016/7/12.
 */
public interface WbHttpService {

    @GET("/2/users/show.json")
    Observable<String> loginRequest(@Query("access_token") String token, @Query("uid") String uid);

}
