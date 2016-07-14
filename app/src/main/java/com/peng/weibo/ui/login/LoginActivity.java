package com.peng.weibo.ui.login;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.peng.weibo.R;
import com.peng.weibo.data.test;
import com.peng.weibo.net.PengApi;
import com.peng.weibo.util.common.Constants;
import com.peng.weibo.util.common.Logs;
import com.peng.weibo.util.weibo.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PS on 2016/7/12.
 */
public class LoginActivity extends AppCompatActivity {

    private TextView content;

    /** 微博 Web 授权类，提供登陆等功能  */
    private WeiboAuth mWeiboAuth;

    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        content = (TextView) findViewById(R.id.content);
        // 创建授权认证信息
        mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
    }

    public void LoginWeibo(View view){
        mWeiboAuth.anthorize(new AuthListener());
    }

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
                Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
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
        Logs.d("msg : " + message + " UID :" + AccessTokenKeeper.readAccessToken(this).getUid());
        content.setText("msg : " + message + " UID :" + AccessTokenKeeper.readAccessToken(this).getUid());
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
}
