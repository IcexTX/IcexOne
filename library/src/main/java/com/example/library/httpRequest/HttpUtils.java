package com.example.library.httpRequest;

import android.content.Context;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * ProjectName：IcexOne
 * Describe：网络请求工具类型
 * Author：Icex
 * CreationTime：2017/2/8
 */

public class HttpUtils {

    private Context context;
    private final String ReadingUrl = "";
    private final String GangHuoUrl = "http://gank.io/api/";
    private final String MoveUrl = "";
    private final String MusicUrl = "";
    private final String Douyu = "http://capi.douyucdn.cn/api/v1/slide/";

    private Retrofit ReadindBook;
    private Retrofit GangHuoRetrofit;
    private Retrofit MoveRetrofit;
    private Retrofit MusicRetrofit;
    private Retrofit DouyuRetrofit;

    private static HttpUtils httpUtils;
    private static HttpRequest ReadingRequest;
    private static HttpRequest GangHuoRequest;
    private static HttpRequest MoveRequest;
    private static HttpRequest MusicRequest;
    private static HttpRequest DouyuRequest;


    //缓存文件名
    private final String CACHE_NAME = "HttpCache";
    private final String TAG = "HttpUtils";

    //获取请求的个数
    public static final int number = 20;

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 获取网络工具类单例
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    public HttpRequest getReadingRequest() {
        if (ReadingRequest == null) {
            ReadingRequest = getReadingBook().create(HttpRequest.class);
        }
        return ReadingRequest;
    }

    public HttpRequest getGangHuoRequest() {
        if (GangHuoRequest == null) {
            GangHuoRequest = getGangHuoRetrofit().create(HttpRequest.class);
        }
        return GangHuoRequest;
    }

    public HttpRequest getMoveRequest() {
        if (MoveRequest == null) {
            MoveRequest = getMoveRetrofit().create(HttpRequest.class);
        }
        return MoveRequest;
    }

    public HttpRequest getMusicRequest() {
        if (MusicRequest == null) {
            MusicRequest = getMusicRetrofit().create(HttpRequest.class);
        }
        return MusicRequest;
    }

    public HttpRequest getDouyuRequest() {
        if (DouyuRequest == null) {
            DouyuRequest = getDouyuRetrofit().create(HttpRequest.class);
        }
        return DouyuRequest;
    }


    private Retrofit getReadingBook() {
        if (ReadindBook == null) {
            ReadindBook = getRetrofit(ReadingUrl);
        }
        return ReadindBook;
    }

    private Retrofit getGangHuoRetrofit() {
        if (GangHuoRetrofit == null) {
            GangHuoRetrofit = getRetrofit(GangHuoUrl);
        }
        return GangHuoRetrofit;
    }

    private Retrofit getMoveRetrofit() {
        if (MoveRetrofit == null) {
            MoveRetrofit = getRetrofit(MoveUrl);
        }
        return MoveRetrofit;
    }

    private Retrofit getMusicRetrofit() {
        if (MusicRetrofit == null) {
            MusicRetrofit = getRetrofit(MusicUrl);
        }
        return MusicRetrofit;
    }

    private Retrofit getDouyuRetrofit() {
        if (DouyuRetrofit == null) {
            DouyuRetrofit = getRetrofit(Douyu);
        }
        return DouyuRetrofit;
    }


    /**
     * 设置访问网址
     *
     * @return
     */
    private Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getHttpClient())
                //使用自定义解析的gson
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


    /**
     * 创建一个gson，解析自定义泛型
     *
     * @return
     */
    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingStrategy(new AnnotateNaming());
        builder.serializeNulls();
        builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        return builder.create();
    }


    /**
     * ParamNames注解
     * 如果包含注解，则解析注解字段，否则解析字段
     */
    private static class AnnotateNaming implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            ParamNames a = field.getAnnotation(ParamNames.class);
            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
        }
    }

    /**
     * 设置OkHttpClient
     *
     * @return
     */
    private OkHttpClient getHttpClient() {
        File cacheFile = new File(context.getApplicationContext().getCacheDir().getAbsolutePath(), CACHE_NAME);
        int CacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, CacheSize);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置缓存
        builder.cache(cache);
        //设置读取超时时间20秒
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        /**
         *拦截器，打印请求信息
         */
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                Log.d(TAG, "请求的url：" + request.url());
                Log.d(TAG, "响应码：" + response.code());
                //Okhttp获取返回信息只能调用一次
                //MyLogUtil.d("相应信息：" + response.body().string());
                return response;
            }
        }).build();
        OkHttpClient httpClient = builder.build();
        return httpClient;
    }


}
