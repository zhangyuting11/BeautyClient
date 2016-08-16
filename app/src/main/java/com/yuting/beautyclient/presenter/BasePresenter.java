package com.yuting.beautyclient.presenter;

import com.yuting.beautyclient.api.ApiService;
import com.yuting.beautyclient.common.MyApplication;

import javax.inject.Inject;

/**
 * BasePresenter
 * Created by yuting on 2016/8/4.
 */
public abstract class BasePresenter {

    @Inject
    ApiService apiService;

    public BasePresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public abstract void requestData(Object... o);

    public abstract void destroy();


}
