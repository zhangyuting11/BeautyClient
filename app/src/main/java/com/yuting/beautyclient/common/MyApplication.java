package com.yuting.beautyclient.common;

import android.app.Application;
import android.content.Context;

import com.yuting.beautyclient.injector.ApplicationComponent;
import com.yuting.beautyclient.injector.ApplicationModule;
import com.yuting.beautyclient.injector.DaggerApplicationComponent;

/**
 * Application 入口
 * Created by yuting on 2016/8/4.
 */
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static Context getContext() {
        return mContext;
    }

    public static ApplicationComponent getApplicationComponent() {
        return ((MyApplication) mContext.getApplicationContext()).mApplicationComponent;
    }
}
