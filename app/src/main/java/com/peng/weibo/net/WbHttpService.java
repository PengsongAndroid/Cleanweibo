package com.peng.weibo.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import com.peng.weibo.data.entity.list.StatusList;
import com.peng.weibo.data.test;

/**
 * Created by PS on 2016/7/12.
 */
public interface WbHttpService {

	/**
	 * 根据用户ID获取用户信息
	 *
	 * @param access_token
	 *            采用OAuth授权方式为必填参数，OAuth授权后获得。
	 * @param uid
	 *            需要查询的用户ID
	 *
	 */
	@GET("/2/users/show.json")
	Observable<test> loginRequest(@Query("access_token") String token, @Query("uid") String uid);

	/**
	 * 返回最新的公共微博。
	 *
	 * @param count
	 *            单页返回的记录条数，默认为50
	 * @param page
	 *            返回结果的页码，默认为1
	 * @param base_app
	 *            是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 *
	 */
	@GET("/2/statuses/public_timeline.json")
	Observable<StatusList> getPublicWb(@Query("access_token") String token, @Query("count") int count, @Query("page") int page,
			@Query("base_app") int base_app);

	/**
	 * 获取当前登录用户及其所关注用户的最新微博。
	 *
	 * @param since_id
	 *            若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0
	 * @param max_id
	 *            若指定此参数，则返回ID小于或等于max_id的微博，默认为0
	 * @param count
	 *            单页返回的记录条数，默认为50
	 * @param page
	 *            返回结果的页码，默认为1
	 * @param base_app
	 *            是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 * @param featureType
	 *            过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0 <li> {@link #FEATURE_ALL}
	 *            <li> {@link #FEATURE_ORIGINAL} <li> {@link #FEATURE_PICTURE} <li>
	 *            {@link #FEATURE_VIDEO} <li> {@link #FEATURE_MUSICE}
	 * @param trim_user
	 *            返回值中user字段开关，false：返回完整user字段、true：user字段仅返回user_id，默认为false
	 */
	@GET("/2/statuses/home_timeline.json")
	Observable<StatusList> getHomeWb(@Query("access_token") String token, @Query("since_id") long since_id, @Query("max_id") long max_id,
									 @Query("count") int count, @Query("page") int page, @Query("base_app") boolean base_app,
									 @Query("featureType") int featureType, @Query("trim_user") boolean trim_user);

	/**
	 * 按天返回热门微博转发榜的微博列表。
	 *
	 * @param count    返回的记录条数，最大不超过50，默认为20
	 * @param base_app 是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 *
	 */
	@GET("/2/statuses/hot/repost_daily.json")
	Observable<StatusList> getHotWbByDayAndRepost(@Query("count") int count, @Query("base_app") int base_app);

	/**
	 * 按周返回热门微博转发榜的微博列表。
	 *
	 * @param count    返回的记录条数，最大不超过50，默认为20
	 * @param base_app 是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 *
	 */
	@GET("/2/statuses/hot/repost_weekly.json.json")
	Observable<StatusList> getHotWbByWeekAndRepost(@Query("count") int count, @Query("base_app") int base_app);

	/**
	 * 按天返回热门微博评论榜的微博列表。
	 *
	 * @param count    返回的记录条数，最大不超过50，默认为20
	 * @param base_app 是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 *
	 */
	@GET("/2/statuses/hot/comments_daily.json.json")
	Observable<StatusList> getHotWbByDayAndComment(@Query("count") int count, @Query("base_app") int base_app);

	/**
	 * 按周返回热门微博评论榜的微博列表。
	 *
	 * @param count    返回的记录条数，最大不超过50，默认为20
	 * @param base_app 是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false
	 *
	 */
	@GET("/2/statuses/hot/comments_weekly.json")
	Observable<StatusList> getHotWbByWeekAndComment(@Query("count") int count, @Query("base_app") int base_app);

}
