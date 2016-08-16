package com.yuting.beautyclient.view;

/**
 * BaseView
 * Created by yuting on 2016/8/4.
 */
public interface BaseView {
    void showLoading();

    void hideLoading();

    void showEmptyView();

    void showNetError();


    void hasNoMoreData();

    void showToastError(String msg);

}
