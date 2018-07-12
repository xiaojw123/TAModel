package com.common.baseframe.net;

import com.common.baseframe.util.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaojw on 2017/11/28.
 */

public class HttpManager {
    private static final int TIME_OUT=7000;

    public static  <M> M getApi(Class<M> service,String baseUrl) {
        OkHttpClient.Builder ohcBuilder = new OkHttpClient.Builder();
        ohcBuilder.addInterceptor(logInterceptor);
        ohcBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(ohcBuilder.build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(service);
    }


    private static Interceptor logInterceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response=chain.proceed(chain.request());
            LogUtils.logd("OkHttp",""+response.request());
            return response;
        }
    };
}
