package com.yuting.beautyclient.injector;

import android.annotation.SuppressLint;

import com.yuting.beautyclient.api.ApiService;
import com.yuting.beautyclient.common.Constant;
import com.yuting.beautyclient.common.Logger;
import com.yuting.beautyclient.common.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiServiceModule
 * Created by yuting on 2016/8/4.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    @Named("api")
    public OkHttpClient provideApiOkHttpClient() {
        //配置OkHttp
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        File cacheFile = new File(MyApplication.getContext().getCacheDir(), Constant.FILE_OKHTTP_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        okBuilder.cache(cache);
        okBuilder.addInterceptor(new LoggingInterceptor());
        okBuilder.addNetworkInterceptor(new LoggingInterceptor());
        return okBuilder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient okHttpClient) {
        //配置OkHttp
        OkHttpClient.Builder okBuilder = okHttpClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        okBuilder.interceptors().clear();
        return okBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        //配置Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(provideOkHttpClient(provideApiOkHttpClient()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    protected ApiService provideApiService() {
        return provideRetrofit().create(ApiService.class);
    }


    class LoggingInterceptor implements Interceptor {
        @SuppressLint("DefaultLocale")
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger.w(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Logger.w(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }


}
