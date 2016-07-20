package com.peng.weibo.ui.login;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.peng.weibo.R;
import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.ui.BaseActivity;
import com.peng.weibo.ui.MainActivity;
import com.peng.weibo.util.common.Constants;
import com.peng.weibo.util.task.CommonEvent;
import com.peng.weibo.util.task.RxBus;
import com.peng.weibo.util.tools.GetNetTime;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.util.tools.Toasts;
import com.peng.weibo.util.weibo.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by PS on 2016/7/12.
 */
public class LoginActivity extends BaseActivity {

    /** 微博 Web 授权类，提供登陆等功能  */
    private WeiboAuth mWeiboAuth;

    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;

    private Date netDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<Object> observable = RxBus.$().register(TAG);
        thread.start();
        RxBus.$().OnEvent(observable, new Action1<Object>() {
            @Override
            public void call(Object o) {
                Logs.i("OnEvent");
                CommonEvent event = (CommonEvent)o;
                if (event.getWhat() == 0){
                    Toasts.showText("获取时间失败");
                } else {
                    isTokenExpire();
                }
            }
        });
    }

    private void isTokenExpire(){
        // 创建授权认证信息
        mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        //先判断token是否存在 然后判断token是否过期 满足条件则重新登录 获取token
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken != null && mAccessToken.getToken() != null){
            if (mAccessToken.getExpiresTime() != 0L){
                Date weiboDate = new java.util.Date(mAccessToken.getExpiresTime());
                Logs.i("netTime : " + netDate.toString() + " weiboDate " + weiboDate);
                if (weiboDate.after(netDate)){
                    startActivity(new Intent(this, MainActivity.class));
                    return;
                } else {
                    mWeiboAuth.anthorize(new AuthListener());
                    return;
                }
            }
        }
        mWeiboAuth.anthorize(new AuthListener());
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            netDate = GetNetTime.getWebsiteDatetime();
            Logs.i("getNetTime");
            if (netDate == null){
                RxBus.$().post(TAG, new CommonEvent(0));
            } else {
                RxBus.$().post(TAG, new CommonEvent(1));
            }
        }
    });


    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用  SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
                updateTokenView(false);
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                    Logs.e(message);
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LoginActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示当前 Token 信息。
     *
     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
     */
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(mAccessToken.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
        String message = String.format(format, mAccessToken.getToken(), date);
        Logs.d("msg : " + message + " data : " + date + " UID :" + AccessTokenKeeper.readAccessToken(this).getUid());
        if (hasExisted) {
            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
    }

    public void GetUser(View view){

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                Logs.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logs.d("onError : " + e.toString());
            }

            @Override
            public void onNext(String s) {
                Logs.d("onNext : " + s);
            }
        };

        PengApi.getInstance().wbService.loginRequest("2.00s1n5VD2SOXGDa7d263aebeEZ2kJD",
                "3212374434")
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<test>() {
                    @Override
                    public void onCompleted() {
                        Logs.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d("onError : " + e.toString());
                    }

                    @Override
                    public void onNext(test s) {
                        Logs.d("onNext : " + s.toString());
                    }
                });

//        PengApi.getInstance().wbService.loginRequest(AccessTokenKeeper.readAccessToken(this).getToken(),
//                AccessTokenKeeper.readAccessToken(this).getUid())
//                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.login_layout;
    }

    @Override
    public void initView(View view) {

    }
}
