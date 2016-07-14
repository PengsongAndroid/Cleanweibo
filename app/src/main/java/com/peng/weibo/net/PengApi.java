package com.peng.weibo.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PS on 2016/7/13.
 */
public class PengApi {
	public Retrofit retrofit;
	public WbHttpService wbService;

	public static final long HTTP_CONNECT_TIMEOUT = 15 * 1000;
	public static final long HTTP_READ_TIMEOUT = 15 * 1000;

	public static final String BASE_URL = "https://" + HttpParam.HOST;

	// 构造方法私有
	private PengApi() {

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
				.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
				.addInterceptor(new MonitorInterceptor())
				.build();

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.create();

		retrofit = new Retrofit.Builder().client(okHttpClient)
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		wbService = retrofit.create(WbHttpService.class);
	}

	// 在访问HttpMethods时创建单例
	private static class SingletonHolder {
		private static final PengApi INSTANCE = new PengApi();
	}

	// 获取单例
	public static PengApi getInstance() {
		return SingletonHolder.INSTANCE;
	}
}