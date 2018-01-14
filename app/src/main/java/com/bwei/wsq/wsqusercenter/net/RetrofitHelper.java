package com.bwei.wsq.wsqusercenter.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wsq on 2017/12/12.
 * Retrofit+OkHttpClient网络请求框架
 */

public class RetrofitHelper {
    private static OkHttpClient client;
    private static ServiceApi serverApi;
    static {
        getClient();
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            synchronized (OkHttpClient.class){
                if (client == null){
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    client = new OkHttpClient.Builder()
                            .addInterceptor(new MyInterceptor())
                            .addInterceptor(logging)
                            .build();
                }
            }
        }
        return client;
    }

    public static ServiceApi getServerApi() {
        if (serverApi == null) {
            synchronized (ServiceApi.class){
                if (serverApi == null){
                    serverApi = onCreate(ServiceApi.class,URL_API.BASE_URL);
                }
            }
        }
        return serverApi;
    }

    public static <T> T onCreate(Class<T> tClass, String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }
    /**
     * 添加公共参数拦截器
     */
    static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("source", "android")
                    .build();
            Request requestNew = request
                    .newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(requestNew);
        }
    }
}
