package com.yuting.beautyclient.injector;

import com.yuting.beautyclient.presenter.BasePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 * Created by yuting on 2016/8/4.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiServiceModule.class})
public interface ApplicationComponent {
    void inject(BasePresenter basePresenter);
}
