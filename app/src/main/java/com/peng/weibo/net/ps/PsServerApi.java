package com.peng.weibo.net.ps;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by PS on 2016/10/31.
 */
public class PsServerApi {
    public Retrofit retrofit;
    public PsHttpService service;

    public static final long HTTP_CONNECT_TIMEOUT = 15 * 1000;
    public static final long HTTP_READ_TIMEOUT = 15 * 1000;

    public static final String BASE_URL = "http://192.168.1.98:8000/";

    // 构造方法私有
    private PsServerApi() {
        //打印log body
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(PsHttpService.class);
    }

    // 在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final PsServerApi INSTANCE = new PsServerApi();
    }

    // 获取单例
    public static PsServerApi getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
