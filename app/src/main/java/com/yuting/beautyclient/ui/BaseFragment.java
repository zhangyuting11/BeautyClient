package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuting.beautyclient.R;
import com.yuting.beautyclient.view.BaseView;

/**
 * 基类BaseFragment
 * Created by yuting on 2016/8/4.
 */
public abstract class BaseFragment extends BaseLoadingFragment implements BaseView {
    private TextView tvError, tvEmpty, tvLoading;
    private Button btnReload;


    public abstract int initContentView();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getBundle(getArguments());
        initUI(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater) {
        return inflater.inflate(initContentView(), null);
    }

    @Override
    public View onCreateContentErrorView(LayoutInflater inflater) {
        View error = inflater.inflate(R.layout.error_view_layout, null);
        tvError = (TextView) error.findViewById(R.id.tvError);
        error.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReloadClicked();
            }
        });
        return error;
    }

    @Override
    public View onCreateContentEmptyView(LayoutInflater inflater) {
        View empty = inflater.inflate(R.layout.empty_view_layout, null);
        tvEmpty = (TextView) empty.findViewById(R.id.tvEmpty);
        btnReload = (Button) empty.findViewById(R.id.btnReload);
        empty.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReloadClicked();
            }
        });
        return empty;
    }

    @Override
    public View onCreateProgressView(LayoutInflater inflater) {
        View loading = inflater.inflate(R.layout.loading_view_layout, null);
        tvLoading = (TextView) loading.findViewById(R.id.tvLoading);
        return loading;
    }

    public void setErrorText(String text) {
        tvError.setText(text);
    }

    public void setErrorText(int textResId) {
        setErrorText(getString(textResId));
    }

    public void setEmptyText(String text) {
        tvEmpty.setText(text);
    }

    public void setEmptyButtonVisible(int visible) {
        btnReload.setVisibility(visible);
    }

    public void setEmptyText(int textResId) {
        setEmptyText(getString(textResId));
    }

    public void setLoadingText(String text) {
        tvLoading.setText(text);
    }

    public void setLoadingText(int textResId) {
        setLoadingText(getString(textResId));
    }

    //Override this to reload
    public void onReloadClicked() {

    }


    @Override
    public void showLoading() {
        showViewById(R.id.llProgress, true);
    }


    @Override
    public void hideLoading() {
        showViewById(R.id.tvContent, true);
        dismissViewById(R.id.llProgress, true);
    }

    @Override
    public void showEmptyView() {
        showViewById(R.id.tvEmpty, true);
    }

    @Override
    public void showNetError() {
        showViewById(R.id.tvError, true);
    }

    @Override
    public void hasNoMoreData() {

    }

    @Override
    public void showToastError(String msg) {
        showShortToast(msg);
    }

}
