package com.app.jetp2.base;

import android.text.TextUtils;
import android.util.Log;

import com.app.jetp2.app.JetpApplication;
import com.app.jetp2.utlis.NetworkUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络对象层
 */
public class BaseApi {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;
    /**
     * 使用OkHttp配置了超时及缓存策略的Retrofit
     *
     * @param baseUrl
     * @return retrofit
     */
    public Retrofit getRetrofit(String baseUrl) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(JetpApplication.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor = chain -> {
            Request build = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")//设置允许请求json数据
                    .build();
            return chain.proceed(build);
        };

        //创建一个OkHttpClient并设置超时时间
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)//没网的情况下
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)//有网的情况下
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();

//        if (BuildConfig.DEBUG) {//发布版本不再打印
            // 日志显示级别
            HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BASIC;
            //新建log拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.e("test","OkHttp====Message:"+message));
            loggingInterceptor.setLevel(level);
            //OkHttp进行添加拦截器loggingInterceptor
            client.newBuilder().addInterceptor(loggingInterceptor);
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;

    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();//拦截器获取请求
        String cacheControl = request.cacheControl().toString();//服务器的缓存策略
        if (!NetworkUtil.isNetConnected()) {//断网时配置缓存策略
            request = request.newBuilder()
                    .cacheControl(TextUtils.isEmpty(cacheControl) ?
                            CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtil.isNetConnected()) {//在线缓存
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", cacheControl)//应用服务端配置的缓存策略
                    .build();
        } else {//离线缓存
            //需要服务端配合处理缓存请求头，不然会抛出： HTTP 504 Unsatisfiable Request (only-if-cached)
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)//CACHE_STALE_SEC
                    .build();
        }
    };
}