package com.peng.weibo.net;

import com.peng.weibo.util.common.Logs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PS on 2016/7/13.
 */
public class MonitorInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        double time = (t2 - t1) / 1e6d;

        if (request.method().equals("GET")) {
            Logs.d(String.format("GET " + request.url(), time, request.headers(), response.code(), response.headers(), response.body().charStream()));
        } else if (request.method().equals("POST")) {
            Logs.d(String.format("POST " + request.url(), time, request.headers(), request.body(), response.code(), response.headers(), response.body().charStream()));
        } else if (request.method().equals("PUT")) {
            Logs.d(String.format("PUT " + request.url(), time, request.headers(), request.body().toString(), response.code(), response.headers(), response.body().charStream()));
        } else if (request.method().equals("DELETE")) {
            Logs.d(String.format("DELETE " +  request.url(), time, request.headers(), response.code(), response.headers()));
        }
        Logs.d("response : " + convertStreamToString(response.body().byteStream()));
        return response;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
